/**
 * Copyright (c) 2013, Redsolution LTD. All rights reserved.
 *
 * This file is part of Qtunr project; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.
 *
 * Qtunr is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * along with this program. If not, see http://www.gnu.org/licenses/.
 */
package com.xabber.android.data.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.xabber.android.data.Application;
import com.xabber.android.data.OnClearListener;
import com.xabber.android.data.OnLoadListener;
import com.xabber.android.data.OnMigrationListener;
import com.xabber.android.data.database.sqlite.AbstractAccountTable;
import com.xabber.android.data.database.sqlite.AccountTable;
import com.xabber.android.data.database.sqlite.AvatarTable;
import com.xabber.android.data.database.sqlite.DatabaseTable;
import com.xabber.android.data.database.sqlite.GroupTable;
import com.xabber.android.data.database.sqlite.MessageTable;
import com.xabber.android.data.database.sqlite.NotificationTable;
import com.xabber.android.data.database.sqlite.NotifyVisibleTable;
import com.xabber.android.data.database.sqlite.OTRTable;
import com.xabber.android.data.database.sqlite.PhraseTable;
import com.xabber.android.data.database.sqlite.PrivateChatTable;
import com.xabber.android.data.database.sqlite.RoomTable;
import com.xabber.android.data.database.sqlite.ShowTextTable;
import com.xabber.android.data.database.sqlite.SoundTable;
import com.xabber.android.data.database.sqlite.StatusTable;
import com.xabber.android.data.database.sqlite.Suppress100Table;
import com.xabber.android.data.database.sqlite.VCardTable;
import com.xabber.android.data.database.sqlite.VibroTable;
import com.xabber.android.data.entity.AccountJid;
import com.xabber.android.data.log.LogManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Helps to open, create, and upgrade the database file.
 * <p/>
 * All requests to database / file system MUST be called from background thread.
 *
 * @author alexander.ivanov
 */
public class DatabaseManager extends SQLiteOpenHelper implements
        OnLoadListener, OnClearListener {

    private static final String DATABASE_NAME = "xabber.db";
    private static final int DATABASE_VERSION = 70;

    private static final SQLiteException DOWNGRADE_EXCEPTION = new SQLiteException(
            "Database file was deleted");
    private static DatabaseManager instance;

    private final ArrayList<DatabaseTable> registeredTables;


    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    private DatabaseManager() {
        super(Application.getInstance(), DATABASE_NAME, null, DATABASE_VERSION);
        registeredTables = new ArrayList<>();

        MessageDatabaseManager.getInstance();
        RealmManager.getInstance();
    }

    public void addTables() {
        addTable(AccountTable.getInstance());
        addTable(RoomTable.getInstance());
        addTable(MessageTable.getInstance());
        addTable(GroupTable.getInstance());
        addTable(PrivateChatTable.getInstance());
        addTable(NotifyVisibleTable.getInstance());
        addTable(ShowTextTable.getInstance());
        addTable(SoundTable.getInstance());
        addTable(VCardTable.getInstance());
        addTable(AvatarTable.getInstance());
        addTable(StatusTable.getInstance());
        addTable(OTRTable.getInstance());
        addTable(VibroTable.getInstance());
        addTable(NotificationTable.getInstance());
        addTable(Suppress100Table.getInstance());
        addTable(PhraseTable.getInstance());
    }



    /**
     * Builds IN statement for specified collection of values.
     *
     * @param <T>
     * @param column
     * @param values
     * @return "column IN (value1, ... valueN)" or
     * "(column IS NULL AND column IS NOT NULL)" if ids is empty.
     */
    public static <T> String in(String column, Collection<T> values) {
        if (values.isEmpty())
            return new StringBuilder("(").append(column)
                    .append(" IS NULL AND ").append(column)
                    .append(" IS NOT NULL)").toString();
        StringBuilder builder = new StringBuilder(column);
        builder.append(" IN (");
        Iterator<T> iterator = values.iterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value instanceof String)
                builder.append(DatabaseUtils.sqlEscapeString((String) value));
            else
                builder.append(value.toString());
            if (iterator.hasNext())
                builder.append(",");
        }
        builder.append(")");
        return builder.toString();
    }

    public static void execSQL(SQLiteDatabase db, String sql) {
        LogManager.iString(DatabaseManager.class.getName(), sql);
        db.execSQL(sql);
    }

    public static void dropTable(SQLiteDatabase db, String table) {
        execSQL(db, "DROP TABLE IF EXISTS " + table + ";");
    }

    public static void renameTable(SQLiteDatabase db, String table,
                                   String newTable) {
        execSQL(db, "ALTER TABLE " + table + " RENAME TO " + newTable + ";");
    }

    public static String commaSeparatedFromCollection(Collection<String> strings) {
        StringBuilder builder = new StringBuilder();
        for (String value : strings) {
            if (builder.length() > 0)
                builder.append(",");
            builder.append(value.replace("\\", "\\\\").replace(",", "\\,"));
        }
        return builder.toString();
    }

    public static Collection<String> collectionFromCommaSeparated(String value) {
        Collection<String> collection = new ArrayList<String>();
        boolean escape = false;
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < value.length(); index++) {
            char chr = value.charAt(index);
            if (!escape) {
                if (chr == '\\') {
                    escape = true;
                    continue;
                } else if (chr == ',') {
                    collection.add(builder.toString());
                    builder = new StringBuilder();
                    continue;
                }
            }
            escape = false;
            builder.append(chr);
        }
        collection.add(builder.toString());
        return Collections.unmodifiableCollection(collection);
    }

    /**
     * Register new table.
     *
     * @param table
     */
    private void addTable(DatabaseTable table) {
        registeredTables.add(table);
    }

    @Override
    public void onLoad() {
        try {
            getWritableDatabase(); // Force onCreate or onUpgrade

            RealmManager.getInstance().copyDataFromSqliteToRealm();
            MessageDatabaseManager.getInstance().copyDataFromSqliteToRealm();
        } catch (SQLiteException e) {
            if (e == DOWNGRADE_EXCEPTION) {
                // Downgrade occured
            } else {
                throw e;
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DatabaseTable table : registeredTables)
            table.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion > newVersion) {
            LogManager.i(this, "Downgrading database from version "
                    + oldVersion + " to " + newVersion);
            File file = new File(db.getPath());
            file.delete();
            LogManager.i(this, "Database file was deleted");
            throw DOWNGRADE_EXCEPTION;
            // This will interrupt getWritableDatabase() call from
            // DatabaseManager's constructor.
        } else {
            LogManager.i(this, "Upgrading database from version " + oldVersion
                    + " to " + newVersion);
            while (oldVersion < newVersion) {
                oldVersion += 1;
                LogManager.i(this, "Migrate to version " + oldVersion);
                migrate(db, oldVersion);
                for (DatabaseTable table : registeredTables)
                    table.migrate(db, oldVersion);
                for (OnMigrationListener listener : Application.getInstance()
                        .getManagers(OnMigrationListener.class))
                    listener.onMigrate(oldVersion);
            }
        }
    }

    /**
     * Called on database migration.
     *
     * @param db
     * @param toVersion
     */
    private void migrate(SQLiteDatabase db, int toVersion) {
        switch (toVersion) {
            case 42:
                dropTable(db, "geolocs");
                dropTable(db, "locations");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClear() {
        for (DatabaseTable table : registeredTables) {
            table.clear();
        }

        MessageDatabaseManager.getInstance().deleteRealm();
        RealmManager.getInstance().deleteRealm();
    }

    public void removeAccount(final AccountJid account) {
        // TODO: replace with constraint.
        for (DatabaseTable table : registeredTables) {
            if (table instanceof AbstractAccountTable) {
                ((AbstractAccountTable) table).removeAccount(account);
            }
        }

        MessageDatabaseManager.getInstance().removeAccountMessages(account);
    }

}

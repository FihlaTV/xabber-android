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
package com.xabber.android.data.account.listeners;

import com.xabber.android.data.BaseManagerInterface;
import com.xabber.android.data.account.AccountItem;
import com.xabber.android.data.connection.ConnectionItem;
import com.xabber.android.data.connection.listeners.OnDisconnectListener;

public interface OnAccountDisabledListener extends BaseManagerInterface {

    /**
     * Account was disabled.
     * <p/>
     * {@link OnAccountOfflineListener#onAccountOffline(AccountItem)} and
     * {@link OnDisconnectListener#onDisconnect(ConnectionItem)} will be call
     * first.
     */
    void onAccountDisabled(AccountItem accountItem);

}

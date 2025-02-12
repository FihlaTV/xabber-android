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

import com.xabber.android.data.BaseUIListener;
import com.xabber.android.data.entity.AccountJid;

import java.util.Collection;

/**
 * Listener for any account state change.
 *
 * @author alexander.ivanov
 */
public interface OnAccountChangedListener extends BaseUIListener {

    /**
     * State changed on connection, disconnection, authorization, etc.
     */
    void onAccountsChanged(Collection<AccountJid> accounts);
}

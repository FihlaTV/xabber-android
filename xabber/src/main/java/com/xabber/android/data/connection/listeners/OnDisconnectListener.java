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
package com.xabber.android.data.connection.listeners;

import com.xabber.android.data.BaseManagerInterface;
import com.xabber.android.data.connection.ConnectionItem;

/**
 * Listener for connection state change.
 *
 * @author alexander.ivanov
 */
public interface OnDisconnectListener extends BaseManagerInterface {

    /**
     * Disconnection occur on some reason.
     */
    void onDisconnect(ConnectionItem connection);

}

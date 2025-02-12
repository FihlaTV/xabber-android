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
package com.xabber.android.data.message;

import com.xabber.android.data.account.AccountItem;
import com.xabber.android.data.account.AccountManager;
import com.xabber.android.data.entity.AccountJid;
import com.xabber.android.data.entity.UserJid;
import com.xabber.android.data.roster.AbstractContact;

/**
 * Represent contact outside of roster with opened chat.
 *
 * @author alexander.ivanov
 */
public class ChatContact extends AbstractContact {

    public ChatContact(AccountJid account, UserJid user) {
        super(account, user);
    }

    public ChatContact(AbstractChat abstractChat) {
        super(abstractChat.getAccount(), abstractChat.getUser());
    }

    @Override
    public boolean isConnected() {
        AccountItem accountItem = AccountManager.getInstance().getAccount(account);
        return accountItem != null && accountItem.getState().isConnected();
    }

}

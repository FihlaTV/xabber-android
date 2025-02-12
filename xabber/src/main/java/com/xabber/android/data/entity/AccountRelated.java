/**
 * Copyright (c) 2013, Redsolution LTD. All rights reserved.
 *
 * This file is part of Qtune project; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.
 *
 * Qtune is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * along with this program. If not, see http://www.gnu.org/licenses/.
 */
package com.xabber.android.data.entity;

import androidx.annotation.NonNull;

/**
 * Object with relation to the account.
 *
 * @author alexander.ivanov
 */
public abstract class AccountRelated {

    protected final @NonNull AccountJid account;

    protected AccountRelated(@NonNull AccountJid account) {
        this.account = account;
    }

    @NonNull
    public AccountJid getAccount() {
        return account;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (account.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountRelated && this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccountRelated)) {
            return false;
        }
        AccountRelated other = (AccountRelated) obj;
        if (!account.equals(other.account))
            return false;
        return true;
    }

}

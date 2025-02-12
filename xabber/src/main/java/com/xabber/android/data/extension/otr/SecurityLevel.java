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
package com.xabber.android.data.extension.otr;

/**
 * Level of the chat security.
 *
 * @author alexander.ivanov
 */
public enum SecurityLevel {

    /**
     * Chat without encryption.
     */
    plain,

    /**
     * Encrypted chat but not verified certificate.
     */
    encrypted,

    /**
     * Encrypted chat with verified certificate.
     */
    verified,

    /**
     * Session was finished by another entity.
     */
    finished;

    public int getImageLevel() {
        return ordinal();
    }

}

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
package com.xabber.xmpp.ssn;

import org.jivesoftware.smackx.xdata.FormField;

import java.util.NoSuchElementException;


/**
 * Logging parameter values.
 * <p/>
 * http://xmpp.org/extensions/xep-0155.html#parameters
 *
 * @author alexander.ivanov
 */
public enum LoggingValue {

    may("Allow message logging"),

    mustnot("Disallow all message logging");

    private final String label;

    LoggingValue(String label) {
        this.label = label;
    }

    public FormField.Option createOption() {
        return new FormField.Option(label, name());
    }

    public static LoggingValue fromString(String value)
            throws NoSuchElementException {
        if (value == null)
            throw new NoSuchElementException();
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException();
        }
    }

}

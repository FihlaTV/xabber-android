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
package com.xabber.android.ui.adapter;

import com.xabber.android.data.roster.AbstractContact;

import java.util.Comparator;

public class ComparatorByName implements Comparator<AbstractContact> {

    public static final ComparatorByName COMPARATOR_BY_NAME = new ComparatorByName();

    @Override
    public int compare(AbstractContact object1, AbstractContact object2) {
        int result;
        result = object1.getName().compareToIgnoreCase(object2.getName());
        if (result != 0)
            return result;
        return object1.getAccount().compareTo(object2.getAccount());
    }

}

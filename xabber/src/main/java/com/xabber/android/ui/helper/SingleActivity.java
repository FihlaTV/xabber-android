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
package com.xabber.android.ui.helper;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

import com.xabber.android.ui.activity.ManagedActivity;

/**
 * Single activity instance.
 * <p/>
 * Finish any previous instances.
 *
 * @author alexander.ivanov
 */
public abstract class SingleActivity extends ManagedActivity {

    private static Map<Class<? extends Activity>, Activity> launched = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = launched.get(this.getClass());
        if (activity != null) {
            activity.finish();
        }
        launched.put(this.getClass(), this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Activity activity = launched.get(this.getClass());
        if (activity == this) {
            launched.remove(this.getClass());
        }
        super.onDestroy();
    }

}

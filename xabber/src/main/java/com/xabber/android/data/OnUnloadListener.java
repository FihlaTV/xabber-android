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
package com.xabber.android.data;

/**
 * Listen for application to being closed.
 *
 * @author alexander.ivanov
 */
public interface OnUnloadListener extends BaseManagerInterface {

    /**
     * Called before application to be killed after
     * {@link OnCloseListener#onClose()} has been called.
     * <p/>
     * WILL BE CALLED FROM BACKGROUND THREAD. DON'T CHANGE OR ACCESS
     * APPLICATION'S DATA HERE!
     */
    void onUnload();

}

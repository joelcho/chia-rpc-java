// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.action;

import com.github.joelcho.chia.types.harvester.GetPlotsRsp;

/**
 * Harvester action constants
 *
 * @author Joel
 */
public class HarvesterAction {
    private HarvesterAction() {
    }

    // region harvester actions
    public static final Action GET_PLOTS
            = new Action("get_plots", null, GetPlotsRsp.class);

    public static final Action REFRESH_PLOTS
            = new Action("refresh_plots", null, Void.TYPE);

    public static final Action DELETE_PLOT
            = new Action("delete_plot", null, Void.TYPE);

    public static final Action ADD_PLOT_DIRECTORY
            = new Action("add_plot_directory", null, Void.TYPE);

    public static final Action GET_PLOT_DIRECTORIES
            = new Action("get_plot_directories", "directories", String.class, Action.SEC_TYPE_COLLECTION);

    public static final Action REMOVE_PLOT_DIRECTORY
            = new Action("remove_plot_directory", null, Void.TYPE);
    // endregion
}

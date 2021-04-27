// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.types.harvester.GetPlotsRsp;

import java.util.List;

/**
 * Harvester api interface
 *
 * @author Joel
 */
public interface Harvester {

    /**
     * Gets a list of plots being farmed on this harvester.
     */
    GetPlotsRsp getPlots() throws Exception;

    /**
     * Refreshes the plots, forces the harvester to search for and load new plots.
     */
    void refreshPlots() throws Exception;

    /**
     * Deletes a plot file and removes it from the harvester.
     *
     * @param filename name of the file to delete
     */
    void deletePlot(String filename) throws Exception;

    /**
     * Adds a plot directory (not including sub-directories) to the harvester and configuration.
     * Plots will be loaded and farmed eventually.
     *
     * @param dirname absolute path of the directory to add
     */
    void addPlotDirectory(String dirname) throws Exception;

    /**
     * Returns all of the plot directoried being farmed.
     */
    List<String> getPlotDirectories() throws Exception;

    /**
     * Removes a plot directory from the config, does not actually delete the directory.
     *
     * @param dirname absolute path of the directory to remove
     */
    void removePlotDirectory(String dirname) throws Exception;
}

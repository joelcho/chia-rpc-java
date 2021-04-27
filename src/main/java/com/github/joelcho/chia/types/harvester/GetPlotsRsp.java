// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.harvester;

import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class GetPlotsRsp {
    private List<PlotInfo> plots;
    private List<String> failedToOpenFilenames;
    private List<String> notFoundFilenames;
}

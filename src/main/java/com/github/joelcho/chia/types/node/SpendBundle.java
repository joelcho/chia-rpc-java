// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class SpendBundle {
    private List<CoinSolution> coinSolutions;
    private G2Element aggregatedSignature;
}

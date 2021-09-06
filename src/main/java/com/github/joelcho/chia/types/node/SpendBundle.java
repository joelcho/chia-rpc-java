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
    private List<CoinSolution> coinSpends;
    private G2Element aggregatedSignature;

    /**
     * 适配1.2.4版本（大约）之前
     * @param coinSolutions
     */
    public void setCoinSolutions(List<CoinSolution> coinSolutions) {
        this.coinSolutions = coinSolutions;
        this.coinSpends = coinSolutions;
    }

    /**
     * 适配1.2.4版本（大约）之后且包含1.2.4版本
     * @param coinSpends
     */
    public void setCoinSpends(List<CoinSolution> coinSpends) {
        this.coinSpends = coinSpends;
        this.coinSolutions = coinSpends;
    }
}

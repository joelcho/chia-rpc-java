// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class MempoolItem {
    private SpendBundle spendBundle;
    private Uint64 fee;
    private CostResult costResult;
    private Bytes32 spendBundleName;
    private List<Coin> additions;
    private List<Coin> removals;
}

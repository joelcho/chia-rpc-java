// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class Foliage {
    private Bytes32 prevBlockHash;
    private Bytes32 rewardBlockHash;
    private FoliageBlockData foliageBlockData;
    private G2Element foliageBlockDataSignature;
    private Bytes32 foliageTransactionBlockHash;
    private G2Element foliageTransactionBlockSignature;
}

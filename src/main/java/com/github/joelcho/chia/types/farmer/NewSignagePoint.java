// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.farmer;

import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class NewSignagePoint {
    private Bytes32 challengeHash;
    private Bytes32 challengeChainSp;
    private Bytes32 rewardChainSp;
    private Uint64 difficulty;
    private Uint64 subSlotIters;
    private int signagePointIndex;
}

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
public class RewardChainSubSlot {
    private VDFInfo endOfSlotVdf;
    private Bytes32 challengeChainSubSlotHash;
    private Bytes32 infusedChallengeChainSubSlotHash;
    private int deficit;
}

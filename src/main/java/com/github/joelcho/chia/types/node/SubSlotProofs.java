// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import lombok.Data;

/**
 * @author Joel
 */
@Data
public class SubSlotProofs {
    private VDFProof challengeChainSlotProof;
    private VDFProof infusedChallengeChainSlotProof;
    private VDFProof rewardChainSlotProof;
}

// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint128;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class RewardChainBlockUnfinished {
    private Uint128 totalIters;
    private int signagePointIndex;
    private Bytes32 posSsCcChallengeHash;
    private ProofOfSpace proofOfSpace;
    private VDFInfo challengeChainSpVdf;
    private G2Element challengeChainSpSignature;
    private VDFInfo rewardChainSpVdf;
    private G2Element rewardChainSpSignature;
}

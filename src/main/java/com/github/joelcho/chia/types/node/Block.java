// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class Block {
    private List<EndOfSubSlotBundle> finishedSubSlots;
    private RewardChainBlock rewardChainBlock;
    private VDFProof challengeChainSpProof;
    private VDFProof challengeChainIpProof;
    private VDFProof rewardChainSpProof;
    private VDFProof rewardChainIpProof;
    private VDFProof infusedChallengeChainIpProof;
    private Foliage foliage;
    private FoliageTransactionBlock foliageTransactionBlock;
    private TransactionsInfo transactionsInfo;
    private Object transactionsGenerator; // SerializedProgram
    private List<Long> transactionsGeneratorRefList;
    private Bytes32 headerHash;
}

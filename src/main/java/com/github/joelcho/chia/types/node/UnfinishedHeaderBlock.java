// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.HexToByteArrayConverter;
import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class UnfinishedHeaderBlock {
    private List<EndOfSubSlotBundle> finishedSubSlots;
    private RewardChainBlockUnfinished rewardChainBlock;
    private VDFProof challengeChainSpProof;
    private VDFProof rewardChainSpProof;
    private Foliage foliage;
    private FoliageTransactionBlock foliageTransactionBlock;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] transactionsFilter;
}

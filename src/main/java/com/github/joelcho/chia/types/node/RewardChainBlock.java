// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.joelcho.chia.types.primitive.Uint128;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Joel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RewardChainBlock extends RewardChainBlockUnfinished {
    private Uint128 weight;
    private long height;
    private VDFInfo challengeChainIpVdf;
    private VDFInfo rewardChainIpVdf;
    private VDFInfo infusedChallengeChainIpVdf;
    @JsonProperty(value = "is_transaction_block")
    private boolean isTransactionBlock;
}

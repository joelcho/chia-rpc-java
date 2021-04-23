// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class SubEpochSummary {
    private Bytes32 prev_subepoch_summary_hash;
    private Bytes32 reward_chain_hash;
    private int num_blocks_overflow;
    private Uint64 new_difficulty;
    private Uint64 new_sub_slot_iters;
}

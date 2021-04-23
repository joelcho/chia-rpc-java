// File created at: Friday, April 23, 2021
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
public class FoliageTransactionBlock {
    private Bytes32 prevTransactionBlockHash;
    private Uint64 timestamp;
    private Bytes32 filterHash;
    private Bytes32 additionsRoot;
    private Bytes32 removalsRoot;
    private Bytes32 transactionsInfoHash;
}

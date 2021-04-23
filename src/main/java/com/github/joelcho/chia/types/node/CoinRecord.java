// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class CoinRecord {
    private Coin coin;
    private long confirmedBlockIndex;
    private long spentBlockIndex;
    private boolean spent;
    private boolean coinbase;
    private Uint64 timestamp;
}

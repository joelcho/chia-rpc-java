// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import com.github.joelcho.chia.types.node.Coin;
import com.github.joelcho.chia.types.node.SpendBundle;
import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class TransactionRecord {
    private long confirmedAtHeight;
    private Uint64 createdAtTime;
    private Bytes32 toPuzzleHash;
    //
    // present in get_transactions
    private String toAddress;
    //
    private Uint64 amount;
    private Uint64 feeAmount;
    private boolean confirmed;
    private long sent;
    private SpendBundle spendBundle;
    private Coin additions;
    private Coin removals;
    private long walletId;

    private Object sent_to;
    private Bytes32 trade_id;
    private TransactionType type;
    private Bytes32 name;

    public String getToAddress() {
        if (toAddress == null) {
            toAddress = ""; // TODO encode_puzzle_hash(this.to_puzzle_hash, prefix)
        }
        return toAddress;
    }
}

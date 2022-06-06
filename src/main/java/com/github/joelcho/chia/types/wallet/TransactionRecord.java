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
import com.github.joelcho.chia.util.AddressUtil;
import lombok.Data;

import java.util.List;

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
    private List<Coin> additions;
    private List<Coin> removals;
    private long walletId;

    private Object sent_to;
    private Bytes32 trade_id;
    private TransactionType type;
    private Bytes32 name;

    /**
     * 此处偷懒，没有指定对象信息
     */
    private Object memos;

    public String getToAddress() {
        if (toAddress == null) {
            String prefix = "xch"; // TODO ambiguous prefix
            toAddress = AddressUtil.encode(toPuzzleHash, prefix);
        }
        return toAddress;
    }
}

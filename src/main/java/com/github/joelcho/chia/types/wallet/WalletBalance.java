// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import com.github.joelcho.chia.types.primitive.Uint128;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class WalletBalance {
    private long walletId;
    private Uint128 confirmedWalletBalance;
    private Uint128 unconfirmedWalletBalance;
    private Uint128 spendableBalance;
    private Uint128 pendingChange;
    private Uint64 maxSendAmount;
}

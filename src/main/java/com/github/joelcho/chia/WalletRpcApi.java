// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.wallet.KeyManagementModule;
import com.github.joelcho.chia.wallet.WalletManagementModule;
import com.github.joelcho.chia.wallet.WalletModule;
import com.github.joelcho.chia.wallet.WalletNodeModule;

/**
 * @author Joel
 */
public interface WalletRpcApi {
    /**
     * Key management module
     */
    KeyManagementModule keyManagement();

    /**
     * Wallet node module
     */
    WalletNodeModule walletNode();

    /**
     * Wallet management module
     */
    WalletManagementModule walletManagement();

    /**
     * Wallet module
     */
    WalletModule wallet();
}

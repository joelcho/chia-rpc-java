// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.wallet;

import com.github.joelcho.chia.types.wallet.WalletInfo;

import java.util.List;

/**
 * @author Joel
 */
public interface WalletManagementModule {
    /**
     * Gets a list of wallets for this key.
     */
    List<WalletInfo> getWallets() throws Exception;

    /**
     * Creates a new wallet for this key.
     */
    // TODO
    void createNewWallet() throws Exception;
}

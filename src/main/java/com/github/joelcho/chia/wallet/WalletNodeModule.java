// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.wallet;

import com.github.joelcho.chia.types.node.NetworkInfo;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.WalletSyncStatus;

/**
 * @author Joel
 */
public interface WalletNodeModule {
    /**
     * Gets the sync status of the wallet.
     */
    WalletSyncStatus getSyncStatus() throws Exception;

    /**
     * Gets information about the current height of the wallet.
     */
    long getHeightInfo() throws Exception;

    /**
     * Farms a block, only available with the simulator.
     */
    void farmBlock(String address) throws Exception;

    /**
     * Retrieves the initial freeze period for the blockchain (no transactions allowed).
     */
    Uint64 getInitialFreezePeriod() throws Exception;

    /**
     * Retrieves some information about the current network.
     */
    NetworkInfo getNetworkInfo() throws Exception;
}

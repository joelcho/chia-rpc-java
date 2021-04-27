// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import lombok.Data;

/**
 * @author Joel
 */
@Data
public class WalletSyncStatus {
    private boolean synced;
    private boolean syncing;
    private boolean genesisInitialized;
}

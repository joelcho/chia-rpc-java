// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.action;

import com.github.joelcho.chia.types.node.NetworkInfo;
import com.github.joelcho.chia.types.wallet.*;
import com.github.joelcho.chia.types.wallet.login.LoginRsp;

/**
 * Wallet action constants
 *
 * @author Joel
 */
public class WalletAction {
    private WalletAction() {
    }

    // region Key management
    public static final Action LOG_IN
            = new Action("log_in", null, LoginRsp.class);
    public static final Action GET_PUBLIC_KEYS
            = new Action("get_public_keys", "public_key_fingerprints", Long.class, Action.SEC_TYPE_COLLECTION);
    public static final Action GET_PRIVATE_KEY
            = new Action("get_private_key", "private_key", PrivateKey.class);
    public static final Action GENERATE_MNEMONIC
            = new Action("generate_mnemonic", "mnemonic", String.class, Action.SEC_TYPE_COLLECTION);
    public static final Action ADD_KEY
            = new Action("add_key", "fingerprint", Long.class);
    public static final Action DELETE_KEY
            = new Action("delete_key", null, Void.class);
    public static final Action DELETE_ALL_KEYS
            = new Action("delete_all_keys", null, Void.class);
    // endregion

    // reginon Wallet node
    public static final Action GET_SYNC_STATUS
            = new Action("get_sync_status", null, WalletSyncStatus.class);
    public static final Action GET_HEIGHT_INFO
            = new Action("get_height_info", "height", Long.class);
    public static final Action FARM_BLOCK
            = new Action("farm_block", null, Void.class);
    public static final Action GET_INITIAL_FREEZE_PERIOD
            = new Action("get_initial_freeze_period", "INITIAL_FREEZE_END_TIMESTAMP", String.class);
    public static final Action GET_NETWORK_INFO
            = new Action("get_network_info", null, NetworkInfo.class);
    // endregion

    // reginon Wallet node
    public static final Action GET_WALLETS
            = new Action("get_wallets", "wallets", WalletInfo.class, Action.SEC_TYPE_COLLECTION);
    public static final Action CREATE_NEW_WALLET
            = new Action("create_new_wallet", "height", Void.class);
    // endregion

    // reginon Wallet
    public static final Action GET_WALLET_BALANCE
            = new Action("get_wallet_balance", "wallet_balance", WalletBalance.class);
    public static final Action GET_TRANSACTION
            = new Action("get_transaction", null, TransactionRecord.class);
    public static final Action GET_TRANSACTIONS
            = new Action("get_transactions", "transactions", TransactionRecord.class, Action.SEC_TYPE_COLLECTION);
    public static final Action GET_NEXT_ADDRESS
            = new Action("get_next_address", "address", String.class);
    public static final Action SEND_TRANSACTION
            = new Action("send_transaction", "transaction", TransactionRecord.class);
    public static final Action CREATE_BACKUP
            = new Action("create_backup", null, Void.class);
    public static final Action GET_TRANSACTION_COUNT
            = new Action("get_transaction_count", "count", Long.class);
    public static final Action GET_FARMED_AMOUNT
            = new Action("get_farmed_amount", null, FarmedAmountRsp.class);
    public static final Action CREATE_SIGNED_TRANSACTION
            = new Action("create_signed_transaction", "signed_tx", TransactionRecord.class);
    // endregion
}

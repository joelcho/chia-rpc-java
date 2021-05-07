// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.wallet;

import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.FarmedAmountRsp;
import com.github.joelcho.chia.types.wallet.TransactionRecord;
import com.github.joelcho.chia.types.wallet.WalletBalance;

import java.util.List;

/**
 * Wallet api interface
 *
 * @author Joel
 */
public interface WalletModule {
    /**
     * Retrieves balances for a wallet
     */
    WalletBalance getWalletBalance(long walletID) throws Exception;

    /**
     * Gets a transaction record by transaction id
     */
    TransactionRecord getTransaction(String transactionId) throws Exception;

    /**
     * Gets transaction records
     *
     * @param walletID wallet id
     * @param start    default 0
     * @param end      default 50
     */
    List<TransactionRecord> getTransactions(long walletID, Long start, Long end) throws Exception;

    /**
     * Gets a new (or not new) address
     */
    String getNextAddress(long WalletId, boolean newAddress) throws Exception;

    /**
     * Sends a standard transaction to a target puzzle_hash.
     */
    TransactionRecord sendTransaction(long walletId, Uint64 amount, String address, Uint64 fee) throws Exception;

    /**
     * Creates a backup for this wallet.
     */
    void createBackup(String filePath) throws Exception;

    /**
     * Gets the number of transactions in this wallet.
     */
    long getTransactionCount(long walletID) throws Exception;

    /**
     * Gets information about farming rewards for this wallet.
     */
    FarmedAmountRsp getFarmedAmount() throws Exception;

    // TODO create signed tx
}

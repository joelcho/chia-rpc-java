// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.types.node.Coin;
import com.github.joelcho.chia.types.node.NetworkInfo;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.*;
import com.github.joelcho.chia.types.wallet.login.LoginParam;
import com.github.joelcho.chia.types.wallet.login.LoginRsp;

import java.util.List;

/**
 * Wallet api interface
 *
 * @author Joel
 */
public interface Wallet {
    /**
     * Sets a key to active.
     *
     * @param param param
     */
    LoginRsp login(LoginParam param) throws Exception;

    /**
     * Get all root public keys accessible by the wallet.
     */
    void getPublicKeys() throws Exception;

    /**
     * Get all root private keys accessible by the wallet.
     *
     * @param fingerprint fingerprint
     */
    PrivateKey getPrivateKey(long fingerprint) throws Exception;

    /**
     * Generate a 24 word mnemonic phrase, used to derive a private key.
     */
    List<String> generateMnemonic() throws Exception;

    /**
     * Add a private key to the keychain
     *
     * @param mnemonic mnemonic, 24 words
     * @param type     add key type
     * @param filePath spec a filepath if type == RESTORE_BACKUP
     */
    long addKey(List<String> mnemonic, AddKeyType type, String filePath) throws Exception;

    /**
     * Delete a private key from the keychain
     *
     * @param fingerprint fingerprint
     */
    void deleteKey(long fingerprint) throws Exception;

    /**
     * Delete all private keys from the keychain
     */
    void deleteAllKeys() throws Exception;

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
    long getInitialFreezePeriod() throws Exception;

    /**
     * Retrieves some information about the current network.
     */
    NetworkInfo getNetworkInfo() throws Exception;

    /**
     * Gets a list of wallets for this key.
     */
    List<WalletInfo> getWallets() throws Exception;

    /**
     * Creates a new wallet for this key.
     */
    // TODO
    void createNewWallet() throws Exception;

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
    TransactionRecord sendTransaction(long walletId, Uint64 amount, Uint64 fee) throws Exception;

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

    void ccSetName(long walletId, String name) throws Exception;

    String ccGetName(long walletId) throws Exception;

    TransactionRecord ccSpend(long walletId, Uint64 amount, Uint64 fee) throws Exception;

    String ccGetColour(long walletId) throws Exception;

    // TODO
    void createOfferForIds() throws Exception;

    // TODO
    void getDiscrepanciesForOffer() throws Exception;

    void respondToOffer(String filename) throws Exception;

    TransactionRecord getTrade(String tradeId) throws Exception;

    List<TransactionRecord> getAllTrades() throws Exception;

    void cancelTrade(boolean secure, String tradeId) throws Exception;

    void rlSetUserInfo(long walletId, Coin origin, String admin_pubkey) throws Exception;

    TransactionRecord sendClawbackTransaction(long walletId, Uint64 fee) throws Exception;

    boolean addRateLimitedFunds(long walletId, Uint64 amount, Uint64 fee) throws Exception;
}

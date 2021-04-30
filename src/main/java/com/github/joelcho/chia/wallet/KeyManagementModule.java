// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.wallet;

import com.github.joelcho.chia.types.wallet.AddKeyType;
import com.github.joelcho.chia.types.wallet.LoginRsp;
import com.github.joelcho.chia.types.wallet.PrivateKey;

import java.util.List;

/**
 * @author Joel
 */
public interface KeyManagementModule {
    /**
     * Sets a key to active.
     *
     * @param fingerprint fingerprint
     */
    long login(long fingerprint) throws Exception;

    /**
     * Sets a key to active.
     *
     * @param fingerprint fingerprint
     * @param type        skip, restore_backup, otherwise
     * @param host        host to download backup
     * @param filePath    backup file path, required if `type` is `restore_backup`
     */
    LoginRsp login(long fingerprint, String type, String host, String filePath) throws Exception;

    /**
     * Get all root public keys accessible by the wallet.
     */
    List<Long> getPublicKeys() throws Exception;

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
}

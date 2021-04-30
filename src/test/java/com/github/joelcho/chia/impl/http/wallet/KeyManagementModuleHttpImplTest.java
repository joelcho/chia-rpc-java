package com.github.joelcho.chia.impl.http.wallet;

import com.github.joelcho.chia.types.wallet.AddKeyType;
import com.github.joelcho.chia.types.wallet.PrivateKey;
import com.github.joelcho.chia.wallet.KeyManagementModule;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.List;

/**
 * @author Joel
 */
public class KeyManagementModuleHttpImplTest extends TestCase {
    private KeyManagementModule km;

    public void setUp() throws Exception {
        super.setUp();
        km = InitWalletRpcApi.init().keyManagement();
    }

    public void testLogin() throws Exception {
        final long fingerprint = km.login(0);
        Assert.assertEquals(0, fingerprint);
    }

    public void testGetPublicKeys() {
        // see testCrud
    }

    public void testGetPrivateKey() throws Exception {
        final PrivateKey privateKey = km.getPrivateKey(0);
    }

    public void testGenerateMnemonic() {
        // see testCrud
    }

    public void testAddKey() {
        // see testCrud
    }

    public void testDeleteKey() {
        // see testCrud
    }

    public void testCrud() throws Exception {
        final List<Long> existKeys = km.getPublicKeys();
        final List<String> mnemonic = km.generateMnemonic();
        final long fingerprint = km.addKey(mnemonic, AddKeyType.NEW_WALLET, null);
        final List<Long> keysAfterAdded = km.getPublicKeys();
        Assert.assertEquals(existKeys.size() + 1, keysAfterAdded.size());
        Assert.assertTrue(keysAfterAdded.contains(fingerprint));
        km.deleteKey(fingerprint);
        final List<Long> keysAfterDeleted = km.getPublicKeys();
        Assert.assertEquals(existKeys.size(), keysAfterDeleted.size());
        Assert.assertFalse(keysAfterDeleted.contains(fingerprint));
    }

    public void testDeleteAllKeys() {
    }
}
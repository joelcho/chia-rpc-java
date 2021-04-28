package com.github.joelcho.chia.impl.http.wallet;

import com.github.joelcho.chia.types.node.NetworkInfo;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.WalletSyncStatus;
import com.github.joelcho.chia.wallet.WalletNodeModule;
import junit.framework.TestCase;

/**
 * @author Joel
 */
public class WalletNodeModuleHttpImplTest extends TestCase {
    private WalletNodeModule wm;

    public void setUp() throws Exception {
        super.setUp();
        wm = InitWalletRpcApi.init().walletNode();
    }

    public void testGetSyncStatus() throws Exception {
        final WalletSyncStatus status = wm.getSyncStatus();
    }

    public void testGetHeightInfo() throws Exception {
        final long info = wm.getHeightInfo();
    }

    public void testFarmBlock() throws Exception {
        wm.farmBlock("");
    }

    public void testGetInitialFreezePeriod() throws Exception {
        final Uint64 period = wm.getInitialFreezePeriod();
    }

    public void testGetNetworkInfo() throws Exception {
        final NetworkInfo info = wm.getNetworkInfo();
    }
}
package com.github.joelcho.chia.impl.http.wallet;

import com.github.joelcho.chia.types.wallet.WalletInfo;
import com.github.joelcho.chia.wallet.WalletManagementModule;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author Joel
 */
public class WalletManagementModuleHttpImplTest extends TestCase {
    private WalletManagementModule wm;
    public void setUp() throws Exception {
        super.setUp();
        wm = InitWalletRpcApi.init().walletManagement();
    }

    public void testGetWallets() throws Exception {
        final List<WalletInfo> wallets = wm.getWallets();
        System.out.println(wallets);
    }

    public void testCreateNewWallet() {

    }
}
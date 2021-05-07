package com.github.joelcho.chia.impl.http.wallet;

import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.FarmedAmountRsp;
import com.github.joelcho.chia.types.wallet.TransactionRecord;
import com.github.joelcho.chia.types.wallet.WalletBalance;
import com.github.joelcho.chia.wallet.WalletModule;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Joel
 */
public class WalletModuleHttpImplTest extends TestCase {
    private WalletModule wm;

    public void setUp() throws Exception {
        super.setUp();
        wm = InitWalletRpcApi.init().wallet();
    }

    public void testGetWalletBalance() throws Exception {
        final WalletBalance balance = wm.getWalletBalance(0);
    }

    public void testGetTransaction() throws Exception {
        final TransactionRecord transaction = wm.getTransaction("");
    }

    public void testGetTransactions() throws Exception {
        final List<TransactionRecord> transactions = wm.getTransactions(0, null, null);
    }

    public void testGetNextAddress() throws Exception {
        final String address = wm.getNextAddress(0, false);
    }

    public void testSendTransaction() throws Exception {
        BigInteger amount = BigInteger.valueOf(123);
        BigInteger fee = BigInteger.valueOf(123);
        final TransactionRecord record = wm.sendTransaction(0, Uint64.fromInteger(amount), "", Uint64.fromInteger(fee));
    }

    public void testCreateBackup() {

    }

    public void testGetTransactionCount() throws Exception {
        final long count = wm.getTransactionCount(0);
    }

    public void testGetFarmedAmount() throws Exception {
        final FarmedAmountRsp amount = wm.getFarmedAmount();
    }
}
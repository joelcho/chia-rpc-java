package com.github.joelcho.chia.impl.http;

import com.github.joelcho.chia.FullNode;
import com.github.joelcho.chia.types.node.*;
import com.github.joelcho.chia.types.primitive.Uint128;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.util.ClientCertAuthHttpClientBuilder;
import com.github.joelcho.chia.util.KeyStoreLoader;
import junit.framework.TestCase;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;

/**
 * @author Joel
 */
public class FullNodeHttpImplTest extends TestCase {
    private FullNode client;

    public void setUp() throws Exception {
        super.setUp();

        final String endpoint = "https://127.0.0.1:8555";
        final String keyStorePass = "keyStorePass";

        KeyStore keyStore;
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("private_full_node.p12")) {
            keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
        }
        CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
        client = new FullNodeHttpImpl(hc, new URL(endpoint).toURI());
    }

    public void testGetBlockchainState() throws Exception {
        final BlockchainState state = client.getBlockchainState();
    }

    public void testGetBlock() throws Exception {
        final Block block = client.getBlock("");
    }

    public void testGetBlocks() throws Exception {
        final List<Block> blocks = client.getBlocks(0, 1, false);
    }

    public void testGetBlockRecordByHeight() throws Exception {
        final BlockRecord record = client.getBlockRecordByHeight(1);
    }

    public void testGetBlockRecord() throws Exception {
        final BlockRecord record = client.getBlockRecord("");
    }

    public void testGetBlockRecords() throws Exception {
        final List<BlockRecord> records = client.getBlockRecords(0, 1);
    }

    public void testGetNetworkSpace() throws Exception {
        final Uint128 networkSpace = client.getNetworkSpace("", "");
    }

    public void testGetAdditionsAndRemovals() throws Exception {
        final AdditionsAndRemovals additionsAndRemovals = client.getAdditionsAndRemovals("", "");
    }

    public void testGetInitialFreezePeriod() throws Exception {
        final Uint64 initialFreezePeriod = client.getInitialFreezePeriod();
    }

    public void testGetNetworkInfo() throws Exception {
        final NetworkInfo networkInfo = client.getNetworkInfo();
    }

    public void testGetCoinRecordsByPuzzleHash() throws Exception {
        final List<CoinRecord> records = client.getCoinRecordsByPuzzleHash("", "", 0L, 1L);
    }

    public void testGetCoinRecordByName() throws Exception {
        final CoinRecord record = client.getCoinRecordByName("");
    }

    public void testPushTx() throws Exception {
        final MempoolInclusionStatus status = client.pushTx(null);
    }

    public void testGetAllMempoolTxIds() throws Exception {
        final List<String> txIds = client.getAllMempoolTxIds();
    }

    public void testGetAllMempoolItems() throws Exception {
        final Map<String, MempoolItem> items = client.getAllMempoolItems();
    }

    public void testGetMempoolItemByTxId() throws Exception {
        final MempoolItem item = client.getMempoolItemByTxId("");
    }
}
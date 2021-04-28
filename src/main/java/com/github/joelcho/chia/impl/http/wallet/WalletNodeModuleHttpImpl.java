// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.action.WalletAction;
import com.github.joelcho.chia.converter.Uint64Converter;
import com.github.joelcho.chia.impl.http.Caller;
import com.github.joelcho.chia.types.node.NetworkInfo;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.WalletSyncStatus;
import com.github.joelcho.chia.wallet.WalletNodeModule;
import org.apache.http.impl.client.CloseableHttpClient;

import java.math.BigInteger;
import java.net.URI;

/**
 * @author Joel
 */
public class WalletNodeModuleHttpImpl implements WalletNodeModule {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public WalletNodeModuleHttpImpl(ObjectMapper objectMapper, CloseableHttpClient httpClient, URI uri, ObjectNode emptyNode) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.uri = uri;
        this.emptyNode = emptyNode;
    }

    @Override
    public WalletSyncStatus getSyncStatus() throws Exception {
        return WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_SYNC_STATUS);
    }

    @Override
    public long getHeightInfo() throws Exception {
        Long h = WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_HEIGHT_INFO);
        assert h != null;
        return h;
    }

    @Override
    public void farmBlock(String address) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("address", address);
        WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.FARM_BLOCK);
    }

    @Override
    public Uint64 getInitialFreezePeriod() throws Exception {
        String ts = WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_INITIAL_FREEZE_PERIOD);
        assert ts != null;
        BigInteger i = Uint64Converter.parseInteger(ts);
        return Uint64.fromInteger(i);
    }

    @Override
    public NetworkInfo getNetworkInfo() throws Exception {
        return WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_NETWORK_INFO);
    }
}

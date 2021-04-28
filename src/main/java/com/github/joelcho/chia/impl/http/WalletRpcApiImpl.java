// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.WalletRpcApi;
import com.github.joelcho.chia.impl.http.wallet.KeyManagementModuleHttpImpl;
import com.github.joelcho.chia.impl.http.wallet.WalletManagementModuleHttpImpl;
import com.github.joelcho.chia.impl.http.wallet.WalletModuleHttpImpl;
import com.github.joelcho.chia.impl.http.wallet.WalletNodeModuleHttpImpl;
import com.github.joelcho.chia.wallet.KeyManagementModule;
import com.github.joelcho.chia.wallet.WalletManagementModule;
import com.github.joelcho.chia.wallet.WalletModule;
import com.github.joelcho.chia.wallet.WalletNodeModule;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;

/**
 * @author Joel
 */
public class WalletRpcApiImpl implements WalletRpcApi {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public WalletRpcApiImpl(CloseableHttpClient httpClient, URI endpoint) {
        this.httpClient = httpClient;
        this.uri = endpoint;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.emptyNode = objectMapper.createObjectNode();
    }

    @Override
    public KeyManagementModule keyManagement() {
        return new KeyManagementModuleHttpImpl(objectMapper, httpClient, uri, emptyNode);
    }

    @Override
    public WalletNodeModule walletNode() {
        return new WalletNodeModuleHttpImpl(objectMapper, httpClient, uri, emptyNode);
    }

    @Override
    public WalletManagementModule walletManagement() {
        return new WalletManagementModuleHttpImpl(objectMapper, httpClient, uri, emptyNode);
    }

    @Override
    public WalletModule wallet() {
        return new WalletModuleHttpImpl(objectMapper, httpClient, uri, emptyNode);
    }
}

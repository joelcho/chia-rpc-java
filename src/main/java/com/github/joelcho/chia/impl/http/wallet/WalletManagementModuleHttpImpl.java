// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.action.WalletAction;
import com.github.joelcho.chia.impl.http.Caller;
import com.github.joelcho.chia.types.wallet.WalletInfo;
import com.github.joelcho.chia.wallet.WalletManagementModule;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;
import java.util.List;

/**
 * @author Joel
 */
public class WalletManagementModuleHttpImpl implements WalletManagementModule {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public WalletManagementModuleHttpImpl(ObjectMapper objectMapper, CloseableHttpClient httpClient, URI uri, ObjectNode emptyNode) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.uri = uri;
        this.emptyNode = emptyNode;
    }

    @Override
    public List<WalletInfo> getWallets() throws Exception {
        return WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_WALLETS);
    }

    @Override
    public void createNewWallet() throws Exception {

    }
}

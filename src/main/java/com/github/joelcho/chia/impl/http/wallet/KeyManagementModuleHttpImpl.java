// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.action.WalletAction;
import com.github.joelcho.chia.impl.http.Caller;
import com.github.joelcho.chia.types.wallet.AddKeyType;
import com.github.joelcho.chia.types.wallet.PrivateKey;
import com.github.joelcho.chia.types.wallet.login.LoginParam;
import com.github.joelcho.chia.types.wallet.login.LoginRsp;
import com.github.joelcho.chia.wallet.KeyManagementModule;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;
import java.util.List;

/**
 * @author Joel
 */
public class KeyManagementModuleHttpImpl implements KeyManagementModule {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public KeyManagementModuleHttpImpl(ObjectMapper objectMapper, CloseableHttpClient httpClient, URI uri, ObjectNode emptyNode) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.uri = uri;
        this.emptyNode = emptyNode;
    }

    @Override
    public LoginRsp login(LoginParam param) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("fingerprint", param.getFingerprint());
        params.put("type", param.getType().toString());
        params.put("host", "");
        return Caller.call(httpClient, uri, objectMapper, params, WalletAction.LOG_IN);
    }

    @Override
    public List<Long> getPublicKeys() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_PUBLIC_KEYS);
    }

    @Override
    public PrivateKey getPrivateKey(long fingerprint) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("fingerprint", fingerprint);
        return Caller.call(httpClient, uri, objectMapper, params, WalletAction.GET_PRIVATE_KEY);
    }

    @Override
    public List<String> generateMnemonic() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GENERATE_MNEMONIC);
    }

    @Override
    public long addKey(List<String> mnemonic, AddKeyType type, String filePath) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        final ArrayNode node = objectMapper.createArrayNode();
        for (String s : mnemonic) {
            node.add(s);
        }
        params.set("mnemonic", node);
        params.put("type", type.toString());
        if (type == AddKeyType.RESTORE_BACKUP) {
            params.put("file_path", filePath);
        }
        Long fingerprint = Caller.call(httpClient, uri, objectMapper, params, WalletAction.ADD_KEY);
        assert fingerprint != null;
        return fingerprint;
    }

    @Override
    public void deleteKey(long fingerprint) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("fingerprint", fingerprint);
        Caller.call(httpClient, uri, objectMapper, params, WalletAction.DELETE_KEY);
    }

    @Override
    public void deleteAllKeys() throws Exception {
        Caller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.DELETE_ALL_KEYS);
    }
}

// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.github.joelcho.chia.FullNode;
import com.github.joelcho.chia.types.node.BlockchainState;
import com.github.joelcho.chia.util.ClientCertAuthHttpClientBuilder;
import com.github.joelcho.chia.util.KeyStoreLoader;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

/**
 * @author Joel
 */
public class Example {
    public void a() throws Exception {
        final String endpoint = "http://127.0.0.1:8555";
        final String keyStorePass = "keyStorePass";

        KeyStore keyStore;
        try (InputStream in = this.getClass().getResourceAsStream("/path/to/cert.p12")) {
            keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
        }
        CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
        FullNode client = new FullNodeHttpImpl(hc, new URL(endpoint).toURI());

        final BlockchainState state = client.getBlockchainState();
        System.out.println(state.getSync().isSynced());
    }
}

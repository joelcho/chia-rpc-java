// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http.wallet;

import com.github.joelcho.chia.WalletRpcApi;
import com.github.joelcho.chia.impl.http.WalletRpcApiImpl;
import com.github.joelcho.chia.util.ClientCertAuthHttpClientBuilder;
import com.github.joelcho.chia.util.KeyStoreLoader;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

/**
 * @author Joel
 */
public class InitWalletRpcApi {
    static WalletRpcApi init() throws Exception {
        final String endpoint = "https://127.0.0.1:9256";
        final String keyStorePass = "keyStorePass";

        KeyStore keyStore;
        try (InputStream in = InitWalletRpcApi.class.getClassLoader().getResourceAsStream("private_wallet.p12")) {
            keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
        }
        CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
        return new WalletRpcApiImpl(hc, new URL(endpoint).toURI());
    }
}

// File created at: Wednesday, April 21, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.*;

/**
 * @author Joel
 */
public class ClientCertAuthHttpClientBuilder {
    public static CloseableHttpClient build(KeyStore keyStore, String keyPass, boolean test)
            throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        SSLContextBuilder sslContextBuilder = SSLContexts.custom();
        if (test) {
            sslContextBuilder.loadTrustMaterial(TrustAllStrategy.INSTANCE);
        }
        sslContextBuilder.loadKeyMaterial(keyStore, keyPass == null ? null : keyPass.toCharArray());
        SSLContext sslContext = sslContextBuilder.build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLContext(sslContext);
        if (test) {
            httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        }
        return httpClientBuilder.build();
    }
}

package com.github.joelcho.chia.impl.http;

import com.github.joelcho.chia.Farmer;
import com.github.joelcho.chia.types.farmer.RewardTargetsRsp;
import com.github.joelcho.chia.types.farmer.SignagePointRsp;
import com.github.joelcho.chia.util.ClientCertAuthHttpClientBuilder;
import com.github.joelcho.chia.util.KeyStoreLoader;
import junit.framework.TestCase;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.List;

/**
 * @author Joel
 */
public class FarmerHttpImplTest extends TestCase {
    private Farmer client;

    public void setUp() throws Exception {
        super.setUp();

        final String endpoint = "https://127.0.0.1:8559";
        final String keyStorePass = "keyStorePass";

        KeyStore keyStore;
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("private_farmer.p12")) {
            keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
        }
        CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
        client = new FarmerHttpImpl(hc, new URL(endpoint).toURI());
    }

    public void testGetSignagePoint() {
    }

    public void testGetSignagePoints() throws Exception {
        final List<SignagePointRsp> points = client.getSignagePoints();
        System.out.println(points);
    }

    public void testGetRewardTargets() throws Exception {
        final RewardTargetsRsp targets = client.getRewardTargets(true);
        System.out.println(targets);
    }

    public void testSetRewardTargets() {
    }
}
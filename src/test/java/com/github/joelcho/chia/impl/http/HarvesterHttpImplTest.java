package com.github.joelcho.chia.impl.http;

import com.github.joelcho.chia.Harvester;
import com.github.joelcho.chia.types.harvester.GetPlotsRsp;
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
public class HarvesterHttpImplTest extends TestCase {
    private Harvester harvester;

    public void setUp() throws Exception {
        super.setUp();
        final String endpoint = "https://127.0.0.1:8560";
        final String keyStorePass = "keyStorePass";

        KeyStore keyStore;
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("private_harvester.p12")) {
            keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
        }
        CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
        harvester = new HarvesterHttpImpl(hc, new URL(endpoint).toURI());
    }

    public void testGetPlots() throws Exception {
        final GetPlotsRsp rsp = harvester.getPlots();
        System.out.println(rsp);
    }

    public void testRefreshPlots() throws Exception {
    }

    public void testDeletePlot() throws Exception {
    }

    public void testAddPlotDirectory() throws Exception {
    }

    public void testGetPlotDirectories() throws Exception {
        final List<String> directories = harvester.getPlotDirectories();
        System.out.println(directories.size());
    }

    public void testRemovePlotDirectory() throws Exception {
    }
}
// File created at: Wednesday, April 21, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @author Joel
 */
public class KeyStoreLoader {
    public static KeyStore load(File file, String storePass)
            throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        String type = FilenameUtils.getExtension(file.getName());
        if (type.equalsIgnoreCase("p12")) {
            type = "PKCS12";
        }
        return load(file, type, storePass);
    }

    public static KeyStore load(File file, String type, String storePass)
            throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return load(fis, type, storePass);
        }
    }

    public static KeyStore load(InputStream source, String type, String storePass)
            throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(type);
        keyStore.load(source, storePass.toCharArray());
        return keyStore;
    }
}

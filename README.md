# [Chia](https://www.chia.net/) [RPC](https://github.com/Chia-Network/chia-blockchain/wiki/RPC-Interfaces) client for Java (1.8 and above)

**NOTE!! This repo is in development, and the code is draft**

### convert cert

```shell
openssl pkcs12 -export \
  -in private_full_node.crt \
  -inkey private_full_node.key \
  -out private_full_node.p12 \
  -name "chia.net"
```

### example

```java
final String endpoint = "http://127.0.0.1:8555";
final String keyStorePass = "keyStorePass";

KeyStore keyStore;
try (InputStream in = this.getClass().getResourceAsStream("/path/to/private_full_node.p12")) {
    keyStore = KeyStoreLoader.load(in, "PKCS12", keyStorePass);
}
CloseableHttpClient hc = ClientCertAuthHttpClientBuilder.build(keyStore, keyStorePass, true);
FullNode client = new FullNodeHttpImpl(hc, new URL(endpoint).toURI());

final BlockchainState state = client.getBlockchainState();
System.out.println(state.getSync().isSynced());
```
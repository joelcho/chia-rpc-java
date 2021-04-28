// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.RPCException;
import com.github.joelcho.chia.action.Action;
import com.github.joelcho.chia.impl.http.Caller;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;

/**
 * @author Joel
 */
public class WalletCaller {
    public static <T> T call(CloseableHttpClient httpClient, URI baseURI,
                             ObjectMapper mapper, ObjectNode data,
                             Action action) throws Exception {
        try {
            return Caller.call(httpClient, baseURI, mapper, data, action);
        } catch (RPCException e) {
            String em = e.getMessage();
            if (em.isEmpty()) {
                // assert self.service.wallet_state_manager is not None
                throw new RPCException("the error message is empty, maybe the wallet not initialized");
            } else {
                throw e;
            }
        }
    }
}

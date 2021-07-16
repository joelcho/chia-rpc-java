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
import com.github.joelcho.chia.types.node.Coin;
import com.github.joelcho.chia.types.primitive.Uint64;
import com.github.joelcho.chia.types.wallet.FarmedAmountRsp;
import com.github.joelcho.chia.types.wallet.SignedTransactionReq;
import com.github.joelcho.chia.types.wallet.TransactionRecord;
import com.github.joelcho.chia.types.wallet.WalletBalance;
import com.github.joelcho.chia.wallet.WalletModule;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;
import java.util.List;

/**
 * @author Joel
 */
public class WalletModuleHttpImpl implements WalletModule {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public WalletModuleHttpImpl(ObjectMapper objectMapper, CloseableHttpClient httpClient, URI uri, ObjectNode emptyNode) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.uri = uri;
        this.emptyNode = emptyNode;
    }

    @Override
    public WalletBalance getWalletBalance(long walletID) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("wallet_id", walletID);
        return WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.GET_WALLET_BALANCE);
    }

    @Override
    public TransactionRecord getTransaction(String transactionId) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("transaction_id", transactionId);
        return WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.GET_TRANSACTION);
    }

    @Override
    public List<TransactionRecord> getTransactions(long walletID, Long start, Long end) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("wallet_id", walletID);
        if (start != null) {
            params.put("start", start);
        }
        if (end != null) {
            params.put("end", end);
        }
        return WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.GET_TRANSACTIONS);
    }

    @Override
    public String getNextAddress(long WalletId, boolean newAddress) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("new_address", newAddress);
        params.put("wallet_id", WalletId);
        return WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.GET_NEXT_ADDRESS);
    }

    @Override
    public TransactionRecord sendTransaction(long walletId, Uint64 amount, String address, Uint64 fee) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("wallet_id", walletId);
        params.put("amount", amount.bigIntegerValue());
        params.put("address", address);
        params.put("fee", fee.bigIntegerValue());
        return WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.SEND_TRANSACTION);
    }

    @Override
    public void createBackup(String filePath) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("file_path", filePath);
        WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.CREATE_BACKUP);
    }

    @Override
    public long getTransactionCount(long walletID) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("wallet_id", walletID);
        Long n = WalletCaller.call(httpClient, uri, objectMapper, params, WalletAction.GET_TRANSACTION_COUNT);
        assert n != null;
        return n;
    }

    @Override
    public FarmedAmountRsp getFarmedAmount() throws Exception {
        return WalletCaller.call(httpClient, uri, objectMapper, emptyNode, WalletAction.GET_FARMED_AMOUNT);
    }

    @Override
    public TransactionRecord createSignedTransaction(SignedTransactionReq req) throws Exception {
        ObjectNode request = objectMapper.createObjectNode();
        ArrayNode additions = objectMapper.createArrayNode();
        for (SignedTransactionReq.Addition addition : req.getAdditions()) {
            ObjectNode add = objectMapper.createObjectNode();
            add.put("amount", addition.getAmount().toString());
            add.put("puzzle_hash", addition.getPuzzleHash());
            additions.add(add);
        }
        request.set("additions", additions);
        if (req.getFee() != null) {
            request.put("fee", req.getFee().toString());
        }
        final List<Coin> coinList = req.getCoins();
        if (coinList != null && !coinList.isEmpty()) {
            ArrayNode coins = objectMapper.createArrayNode();
            for (Coin coin : coinList) {
                ObjectNode o = objectMapper.createObjectNode();
                o.put("parent_coin_info", coin.getParentCoinInfo().toHexString(true));
                o.put("puzzle_hash", coin.getPuzzleHash().toHexString(true));
                o.put("amount", coin.getAmount().toString());
                coins.add(o);
            }
            request.set("coins", coins);
        }
        return WalletCaller.call(httpClient, uri, objectMapper, request, WalletAction.CREATE_SIGNED_TRANSACTION);
    }
}

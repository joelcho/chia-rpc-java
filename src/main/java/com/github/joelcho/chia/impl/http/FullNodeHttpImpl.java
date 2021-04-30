// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.FullNode;
import com.github.joelcho.chia.action.ActionResultMap;
import com.github.joelcho.chia.action.FullNodeAction;
import com.github.joelcho.chia.converter.Uint64Converter;
import com.github.joelcho.chia.types.node.*;
import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint128;
import com.github.joelcho.chia.types.primitive.Uint64;
import org.apache.http.impl.client.CloseableHttpClient;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author Joel
 */
public class FullNodeHttpImpl implements FullNode {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public FullNodeHttpImpl(CloseableHttpClient httpClient, URI endpoint) {
        this.httpClient = httpClient;
        this.uri = endpoint;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.emptyNode = objectMapper.createObjectNode();
    }

    @Override
    public BlockchainState getBlockchainState() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_BLOCKCHAIN_STATE);
    }

    @Override
    public Block getBlock(String headerHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        Block block = Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_BLOCK);
        if (block != null) {
            block.setHeaderHash(Bytes32.fromHex(headerHash));
        }
        return block;
    }

    @Override
    public List<Block> getBlocks(long start, long end, boolean excludeHeaderHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("start", start);
        params.put("end", end);
        params.put("exclude_header_hash", excludeHeaderHash);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_BLOCKS);
    }

    @Override
    public List<UnfinishedHeaderBlock> getUnfinishedBlockHeaders() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_UNFINISHED_BLOCK_HEADERS);
    }

    @Override
    public BlockRecord getBlockRecordByHeight(long height) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("height", height);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_BLOCK_RECORD_BY_HEIGHT);
    }

    @Override
    public BlockRecord getBlockRecord(String headerHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_BLOCK_RECORD);
    }

    @Override
    public List<BlockRecord> getBlockRecords(long start, long end) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("start", start);
        params.put("end", end);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_BLOCK_RECORDS);
    }

    @Override
    public Uint128 getNetworkSpace(String olderBlockHeaderHash, String newerBlockHeaderHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("older_block_header_hash", olderBlockHeaderHash);
        params.put("newer_block_header_hash", newerBlockHeaderHash);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_NETWORK_SPACE);
    }

    @Override
    public AdditionsAndRemovals getAdditionsAndRemovals(String headerHash, String newerBlockHeaderHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        params.put("newer_block_header_hash", newerBlockHeaderHash);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_ADDITIONS_AND_REMOVALS);
    }

    @Override
    public Uint64 getInitialFreezePeriod() throws Exception {
        String s = Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_INITIAL_FREEZE_PERIOD);
        assert s != null;
        BigInteger i = Uint64Converter.parseInteger(s);
        return Uint64.fromInteger(i);
    }

    @Override
    public NetworkInfo getNetworkInfo() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_NETWORK_INFO);
    }

    @Override
    public List<CoinRecord> getCoinRecordsByPuzzleHash(String puzzleHash, String includeSpendCoins, Long startHeight, Long endHeight) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("puzzle_hash", puzzleHash);
        params.put("include_spend_coins", includeSpendCoins);
        if (startHeight != null) {
            params.put("start_height", startHeight);
        }
        if (endHeight != null) {
            params.put("end_height", endHeight);
        }
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_COIN_RECORDS_BY_PUZZLE_HASH);
    }

    @Override
    public CoinRecord getCoinRecordByName(String name) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("name", name);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_COIN_RECORD_BY_NAME);
    }

    @Override
    public MempoolInclusionStatus pushTx(SpendBundle spendBundle) throws Exception {
        throw new UnsupportedOperationException("no implemented");
    }

    @Override
    public List<String> getAllMempoolTxIds() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_ALL_MEMPOOL_TX_IDS);
    }

    @Override
    public Map<String, MempoolItem> getAllMempoolItems() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FullNodeAction.GET_ALL_MEMPOOL_ITEMS);
    }

    @Override
    public MempoolItem getMempoolItemByTxId(String txId) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("tx_id", txId);
        return Caller.call(httpClient, uri, objectMapper, params, FullNodeAction.GET_MEMPOOL_ITEM_BY_TX_ID);
    }
}

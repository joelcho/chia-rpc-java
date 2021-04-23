// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.Action;
import com.github.joelcho.chia.FullNode;
import com.github.joelcho.chia.RPCException;
import com.github.joelcho.chia.ResultMapAction;
import com.github.joelcho.chia.types.node.*;
import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint128;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
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
        return call(httpClient, uri, objectMapper, emptyNode, Action.GET_BLOCKCHAIN_STATE);
    }

    @Override
    public Block getBlock(String headerHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        Block block = call(httpClient, uri, objectMapper, params, Action.GET_BLOCK);
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
        return call(httpClient, uri, objectMapper, params, Action.GET_BLOCKS);
    }

    @Override
    public List<UnfinishedHeaderBlock> getUnfinishedBlockHeaders() throws Exception {
        return call(httpClient, uri, objectMapper, emptyNode, Action.GET_UNFINISHED_BLOCK_HEADERS);
    }

    @Override
    public BlockRecord getBlockRecordByHeight(long height) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("height", height);
        return call(httpClient, uri, objectMapper, params, Action.GET_BLOCK_RECORD_BY_HEIGHT);
    }

    @Override
    public BlockRecord getBlockRecord(String headerHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        return call(httpClient, uri, objectMapper, params, Action.GET_BLOCK_RECORD);
    }

    @Override
    public List<BlockRecord> getBlockRecords(long start, long end) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("start", start);
        params.put("end", end);
        return call(httpClient, uri, objectMapper, params, Action.GET_BLOCK_RECORDS);
    }

    @Override
    public Uint128 getNetworkSpace(String olderBlockHeaderHash, String newerBlockHeaderHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("older_block_header_hash", olderBlockHeaderHash);
        params.put("newer_block_header_hash", newerBlockHeaderHash);
        return call(httpClient, uri, objectMapper, params, Action.GET_NETWORK_SPACE);
    }

    @Override
    public AdditionsAndRemovals getAdditionsAndRemovals(String headerHash, String newerBlockHeaderHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("header_hash", headerHash);
        params.put("newer_block_header_hash", newerBlockHeaderHash);
        return call(httpClient, uri, objectMapper, params, Action.GET_ADDITIONS_AND_REMOVALS);
    }

    @Override
    public long getInitialFreezePeriod() throws Exception {
        return call(httpClient, uri, objectMapper, emptyNode, Action.GET_INITIAL_FREEZE_PERIOD);
    }

    @Override
    public NetworkInfo getNetworkInfo() throws Exception {
        return call(httpClient, uri, objectMapper, emptyNode, Action.GET_NETWORK_INFO);
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
        return call(httpClient, uri, objectMapper, params, Action.GET_COIN_RECORDS_BY_PUZZLE_HASH);
    }

    @Override
    public CoinRecord getCoinRecordByName(String name) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("name", name);
        return call(httpClient, uri, objectMapper, params, Action.GET_COIN_RECORD_BY_NAME);
    }

    @Override
    public MempoolInclusionStatus pushTx(SpendBundle spendBundle) throws Exception {
        return null; // TODO
    }

    @Override
    public List<String> getAllMempoolTxIds() throws Exception {
        return call(httpClient, uri, objectMapper, emptyNode, Action.GET_ALL_MEMPOOL_TX_IDS);
    }

    @Override
    public Map<String, MempoolItem> getAllMempoolItems() throws Exception {
        return call(httpClient, uri, objectMapper, emptyNode, ResultMapAction.GET_ALL_MEMPOOL_ITEMS);
    }

    @Override
    public MempoolItem getMempoolItemByTxId(String txId) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("tx_id", txId);
        return call(httpClient, uri, objectMapper, params, Action.GET_MEMPOOL_ITEM_BY_TX_ID);
    }

    private static <T> T call(CloseableHttpClient httpClient, URI baseURI,
                              ObjectMapper mapper, ObjectNode data,
                              Action action) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(baseURI);
        uriBuilder.setPath(action.getMethodName());

        HttpPost post = new HttpPost(uriBuilder.build());
        if (data != null) {
            byte[] rawData = mapper.writeValueAsBytes(data);
            post.setEntity(new ByteArrayEntity(rawData));
        }
        post.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

        JsonNode jsonNode;
        try (CloseableHttpResponse response = httpClient.execute(post)) {
            final HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            jsonNode = mapper.readValue(in, JsonNode.class);
        }
        System.out.println(jsonNode.toPrettyString());
        JsonParser returnJsonParser = null;

        JsonNode statusNode = jsonNode.get("success");
        if (statusNode != null && statusNode.isBoolean()) {
            if (statusNode.booleanValue()) {
                String fieldName = action.getResultFieldName();
                if (fieldName == null) {
                    if (jsonNode instanceof ObjectNode) {
                        ((ObjectNode) jsonNode).remove("success");
                        returnJsonParser = mapper.treeAsTokens(jsonNode);
                    }
                } else {
                    JsonNode body = jsonNode.get(fieldName);
                    if (body != null) {
                        returnJsonParser = mapper.treeAsTokens(body);
                    }
                }
            } else {
                JsonNode errNode = jsonNode.get("error");
                if (errNode != null) {
                    throw new RPCException(errNode.asText());
                }
            }
        }

        if (returnJsonParser != null) {
            JavaType returnJavaType = mapper.getTypeFactory().constructType(action.getReturnType());
            if (action.getSecondType() == Action.SEC_TYPE_COLLECTION) {
                returnJavaType = mapper.getTypeFactory().constructCollectionType(List.class, returnJavaType);
            } else if (action.getSecondType() == Action.SEC_TYPE_MAP) {
                ResultMapAction rma = (ResultMapAction) action;
                JavaType keyType = mapper.getTypeFactory().constructType(rma.getKeyType());
                returnJavaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, returnJavaType);
            }
            return mapper.readValue(returnJsonParser, returnJavaType);
        }
        throw new RuntimeException("invalid response: " + mapper.writeValueAsString(jsonNode));
    }
}

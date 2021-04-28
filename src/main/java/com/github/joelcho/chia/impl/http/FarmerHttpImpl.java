// File created at: Tuesday, April 27, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.Farmer;
import com.github.joelcho.chia.action.FarmerAction;
import com.github.joelcho.chia.types.farmer.RewardTargetsRsp;
import com.github.joelcho.chia.types.farmer.SignagePointRsp;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;
import java.util.List;

/**
 * @author Joel
 */
public class FarmerHttpImpl implements Farmer {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public FarmerHttpImpl(CloseableHttpClient httpClient, URI endpoint) {
        this.httpClient = httpClient;
        this.uri = endpoint;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.emptyNode = objectMapper.createObjectNode();
    }

    @Override
    public SignagePointRsp getSignagePoint(String spHash) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("sp_hash", spHash);
        return Caller.call(httpClient, uri, objectMapper, params, FarmerAction.GET_SIGNAGE_POINT);
    }

    @Override
    public List<SignagePointRsp> getSignagePoints() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, FarmerAction.GET_SIGNAGE_POINTS);
    }

    @Override
    public RewardTargetsRsp getRewardTargets(boolean searchForPrivateKey) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("search_for_private_key", searchForPrivateKey);
        return Caller.call(httpClient, uri, objectMapper, params, FarmerAction.GET_REWARD_TARGETS);
    }

    @Override
    public void setRewardTargets(String farmerTarget, String poolTarget) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("farmer_target", farmerTarget);
        params.put("pool_target", poolTarget);
        Caller.call(httpClient, uri, objectMapper, params, FarmerAction.SET_REWARD_TARGETS);
    }
}

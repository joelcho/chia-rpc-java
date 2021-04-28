// File created at: Tuesday, April 27, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.Harvester;
import com.github.joelcho.chia.action.HarvesterAction;
import com.github.joelcho.chia.types.harvester.GetPlotsRsp;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.URI;
import java.util.List;

/**
 * @author Joel
 */
public class HarvesterHttpImpl implements Harvester {
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    private final URI uri;
    private final ObjectNode emptyNode;

    public HarvesterHttpImpl(CloseableHttpClient httpClient, URI endpoint) {
        this.httpClient = httpClient;
        this.uri = endpoint;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.emptyNode = objectMapper.createObjectNode();
    }

    @Override
    public GetPlotsRsp getPlots() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, HarvesterAction.GET_PLOTS);
    }

    @Override
    public void refreshPlots() throws Exception {
        Caller.call(httpClient, uri, objectMapper, emptyNode, HarvesterAction.REFRESH_PLOTS);
    }

    @Override
    public void deletePlot(String filename) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("filename", filename);
        Caller.call(httpClient, uri, objectMapper, params, HarvesterAction.DELETE_PLOT);
    }

    @Override
    public void addPlotDirectory(String dirname) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("dirname", dirname);
        Caller.call(httpClient, uri, objectMapper, params, HarvesterAction.ADD_PLOT_DIRECTORY);
    }

    @Override
    public List<String> getPlotDirectories() throws Exception {
        return Caller.call(httpClient, uri, objectMapper, emptyNode, HarvesterAction.GET_PLOT_DIRECTORIES);
    }

    @Override
    public void removePlotDirectory(String dirname) throws Exception {
        final ObjectNode params = objectMapper.createObjectNode();
        params.put("dirname", dirname);
        Caller.call(httpClient, uri, objectMapper, params, HarvesterAction.REMOVE_PLOT_DIRECTORY);
    }
}

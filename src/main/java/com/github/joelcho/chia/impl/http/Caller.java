// File created at: Tuesday, April 27, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.impl.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.joelcho.chia.action.Action;
import com.github.joelcho.chia.RPCException;
import com.github.joelcho.chia.action.ActionResultMap;
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
public class Caller {
    public static <T> T call(CloseableHttpClient httpClient, URI baseURI,
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
                if (action.getReturnType().equals(Void.TYPE)) {
                    // TODO primitive type unboxing may cause NullPointerException
                    return null;
                }
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
                ActionResultMap rma = (ActionResultMap) action;
                JavaType keyType = mapper.getTypeFactory().constructType(rma.getKeyType());
                returnJavaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, returnJavaType);
            }
            return mapper.readValue(returnJsonParser, returnJavaType);
        }
        throw new RuntimeException("invalid response: " + mapper.writeValueAsString(jsonNode));
    }
}

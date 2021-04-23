// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.joelcho.chia.types.ClassgroupElement;

/**
 * @author Joel
 */
public class ClassgroupElementConverter extends StdConverter<JsonNode, ClassgroupElement> {
    @Override
    public ClassgroupElement convert(JsonNode node) {
        JsonNode data = node.get("data");
        return ClassgroupElement.fromHex(data.textValue());
    }
}

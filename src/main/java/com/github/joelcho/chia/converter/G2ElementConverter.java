// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.joelcho.chia.types.node.G2Element;

/**
 * @author Joel
 */
public class G2ElementConverter  extends StdConverter<String, G2Element> {
    @Override
    public G2Element convert(String value) {
        return G2Element.fromHex(value);
    }
}
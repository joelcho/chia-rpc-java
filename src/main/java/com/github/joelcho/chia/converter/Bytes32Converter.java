// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.joelcho.chia.types.primitive.Bytes32;

/**
 * @author Joel
 */
public class Bytes32Converter extends StdConverter<String, Bytes32> {
    @Override
    public Bytes32 convert(String s) {
        return Bytes32.fromHex(s);
    }
}

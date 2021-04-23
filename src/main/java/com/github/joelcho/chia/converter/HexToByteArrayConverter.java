// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.joelcho.chia.util.HexUtil;

/**
 * @author Joel
 */
public class HexToByteArrayConverter extends StdConverter<String, byte[]> {
    @Override
    public byte[] convert(String value) {
        return HexUtil.decode(HexUtil.cleanHexPrefix(value));
    }
}

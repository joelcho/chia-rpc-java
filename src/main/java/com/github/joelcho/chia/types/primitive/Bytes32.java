// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.primitive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.Bytes32Converter;
import com.github.joelcho.chia.util.HexUtil;

/**
 * @author Joel
 */
@JsonDeserialize(converter = Bytes32Converter.class)
public class Bytes32 {

    public static Bytes32 fromHex(String str) throws RuntimeException {
        str = HexUtil.cleanHexPrefix(str);
        if (str.length() > 64) {
            throw new IllegalArgumentException("invalid hash hex str:" + str);
        }
        byte[] data = HexUtil.decode(str);
        return new Bytes32(data);
    }

    private final byte[] bytes = new byte[32];

    public Bytes32(byte[] b) {
        System.arraycopy(b, 0, this.bytes, 0, Math.min(b.length, this.bytes.length));
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public String toString() {
        return this.toHexString(false);
    }

    public String toHexString(boolean prefixed) {
        return HexUtil.toHexString(this.bytes, prefixed, true, 64);
    }
}

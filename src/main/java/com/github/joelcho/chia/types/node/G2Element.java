// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.G2ElementConverter;
import com.github.joelcho.chia.util.HexUtil;

/**
 * @author Joel
 */
@JsonDeserialize(converter = G2ElementConverter.class)
public class G2Element {
    public static G2Element fromHex(String str) throws RuntimeException {
        str = HexUtil.cleanHexPrefix(str);
        byte[] data = HexUtil.decode(str);
        return new G2Element(data);
    }

    private final byte[] bytes;

    private G2Element(byte[] b) {
        this.bytes = b;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public String toString() {
        return this.toHexString(false);
    }

    public String toHexString(boolean prefixed) {
        return HexUtil.toHexString(this.bytes, prefixed, false, 0);
    }
}

// File created at: Friday, May 07, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import com.github.joelcho.chia.types.primitive.Bytes32;

import java.io.ByteArrayOutputStream;

/**
 * @author Joel
 */
public class AddressUtil {
    private static final int M = 0x2bc830a3;

    public static String encode(Bytes32 h, String prefix) {
        final byte[] bytes = convertBits(h.getBytes(), 8, 5, true);
        return Bech32.encode(new Bech32.Bech32Data(prefix, bytes), M);
    }

    public static Bytes32 decode(String str) {
        final Bech32.Bech32Data decode = Bech32.decode(str, M);
        if (decode.data.length == 0) {
            throw new IllegalArgumentException("invalid value");
        }
        final byte[] bytes = convertBits(decode.data, 5, 8, false);
        return new Bytes32(bytes);
    }

    /**
     * General power-of-2 base conversion.
     */
    private static byte[] convertBits(final byte[] in, final int fromBits, final int toBits, final boolean pad) {
        int acc = 0;
        int bits = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        final int maxv = (1 << toBits) - 1;
        final int max_acc = (1 << (fromBits + toBits - 1)) - 1;
        for (int i = 0; i < in.length; i++) {
            int value = in[i] & 0xff;
            if ((value >>> fromBits) != 0) {
                throw new IllegalArgumentException("input value " + value + " exceeds " + fromBits + " bit size, at index " + i);
            }
            acc = ((acc << fromBits) | value) & max_acc;
            bits += fromBits;
            while (bits >= toBits) {
                bits -= toBits;
                out.write((acc >>> bits) & maxv);
            }
        }
        if (pad) {
            if (bits > 0) out.write((acc << (toBits - bits)) & maxv);
        } else if (bits >= fromBits || ((acc << (toBits - bits)) & maxv) != 0) {
            throw new IllegalArgumentException("invalid padding");
        }
        return out.toByteArray();
    }
}

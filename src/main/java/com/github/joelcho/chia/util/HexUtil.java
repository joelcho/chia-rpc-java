// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

/**
 * @author Joel
 */
public class HexUtil {
    public static String cleanHexPrefix(String x) {
        return Numeric.cleanHexPrefix(x);
    }

    public static String toHexString(byte[] bytes, boolean prefixed, boolean zeroPadded, int strSize) {
        String s = Hex.toHexString(bytes);
        if (strSize == 0) {
            strSize = bytes.length * 2;
        }
        if (s.length() < strSize && zeroPadded) {
            s = Strings.zeros(strSize - s.length()) + s;
        }
        if (prefixed) {
            s = "0x" + s;
        }
        return s;
    }

    public static byte[] decode(String x) {
        return org.bouncycastle.util.encoders.Hex.decode(x);
    }
}

// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import org.bouncycastle.util.encoders.Hex;

/**
 * @author Joel
 */
public class HexUtil {
    // https://github.com/web3j/web3j/blob/master/utils/src/main/java/org/web3j/utils/Numeric.java
    public static String cleanHexPrefix(String input) {
        if (containsHexPrefix(input)) {
            return input.substring(2);
        } else {
            return input;
        }
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

    // https://github.com/web3j/web3j/blob/master/utils/src/main/java/org/web3j/utils/Numeric.java
    public static boolean containsHexPrefix(String input) {
        return !Strings.isEmpty(input)
                && input.length() > 1
                && input.charAt(0) == '0'
                && input.charAt(1) == 'x';
    }
}

// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Joel
 */
public class SeedConverter extends StdConverter<String, List<String>> {
    public static final int SEED_COUNT = 24;

    @Override
    public List<String> convert(String value) {
        return splitMnemonic(value);
    }

    public static List<String> splitMnemonic(String str) {
        String[] arr = str.split("\\s+");
        if (arr.length != SEED_COUNT) {
            throw new IllegalArgumentException("invalid mnemonic: " + str);
        }
        return Arrays.asList(arr);
    }
}

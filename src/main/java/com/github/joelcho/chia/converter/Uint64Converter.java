// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.joelcho.chia.types.primitive.Uint64;

import java.math.BigInteger;

/**
 * @author Joel
 */
public class Uint64Converter extends StdConverter<String, Uint64> {
    @Override
    public Uint64 convert(String value) {
        BigInteger i = new BigInteger(value, 10);
        return Uint64.fromInteger(i);
    }
}

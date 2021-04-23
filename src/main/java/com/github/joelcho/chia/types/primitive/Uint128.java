// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.primitive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.Uint128Converter;

import java.math.BigInteger;

/**
 * @author Joel
 */
@JsonDeserialize(converter = Uint128Converter.class)
public class Uint128 extends UintValue {

    public static Uint128 fromInteger(BigInteger integer) {
        final int length = integer.bitLength();
        if (length > 128) {
            throw new IllegalArgumentException("invalid uint64 value:" + integer.toString());
        }
        return new Uint128(integer);
    }

    private Uint128(BigInteger integer) {
        super(integer);
    }
}

// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.primitive;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Joel
 */
public abstract class UintValue extends Number implements Comparable<UintValue> {
    private final BigInteger raw;

    protected UintValue(BigInteger integer) {
        this.raw = integer;
    }

    @Override
    public int compareTo(UintValue o) {
        return raw.compareTo(o.raw);
    }

    @Override
    public int intValue() {
        return raw.intValue();
    }

    @Override
    public long longValue() {
        return raw.longValue();
    }

    @Override
    public float floatValue() {
        return raw.floatValue();
    }

    @Override
    public double doubleValue() {
        return raw.doubleValue();
    }

    public BigInteger bigIntegerValue() {
        return this.raw;
    }

    public BigDecimal bigDecimalValue() {
        return new BigDecimal(this.raw);
    }

    @Override
    public String toString() {
        return raw.toString(10);
    }
}

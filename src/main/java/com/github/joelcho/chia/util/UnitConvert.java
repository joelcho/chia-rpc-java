// File created at: Friday, April 30, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.util;

import java.math.BigDecimal;

/**
 * Chia unit conversion functions.
 *
 * @author Joel
 */
public class UnitConvert {
    private UnitConvert() {
    }

    public static BigDecimal fromMojo(BigDecimal value, Unit toUint) {
        return value.divide(toUint.getMojoFactor());
    }

    public static BigDecimal toMojo(BigDecimal value, Unit fromUnit) {
        return value.multiply(fromUnit.getMojoFactor());
    }

    public enum Unit {
        MOJO("mojo", 1),
        COLOUREDCOIN("colouredcoin", 3),
        XCH("xch", 12);

        private final String name;
        private final BigDecimal mojoFactor;

        Unit(String name, int factor) {
            this.name = name;
            this.mojoFactor = BigDecimal.TEN.pow(factor);
        }

        public BigDecimal getMojoFactor() {
            return mojoFactor;
        }

        @Override
        public String toString() {
            return name;
        }

        public static UnitConvert.Unit fromString(String name) {
            if (name != null) {
                for (UnitConvert.Unit unit : UnitConvert.Unit.values()) {
                    if (name.equalsIgnoreCase(unit.name)) {
                        return unit;
                    }
                }
            }
            return UnitConvert.Unit.valueOf(name);
        }
    }
}

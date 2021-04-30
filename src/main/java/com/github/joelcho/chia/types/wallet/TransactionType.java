// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

/**
 * @author Joel
 */
public enum TransactionType {
    INCOMING_TX(0),
    OUTGOING_TX(1),
    COINBASE_REWARD(2),
    FEE_REWARD(3),
    INCOMING_TRADE(4),
    OUTGOING_TRADE(5),

    ;

    TransactionType(int i) {
        this.value = i;
    }

    private final int value;

    public int toIntValue() {
        return value;
    }

    static TransactionType fromInt(int i) {
        switch (i) {
            case 0:
                return INCOMING_TX;
            case 1:
                return OUTGOING_TX;
            case 2:
                return COINBASE_REWARD;
            case 3:
                return FEE_REWARD;
            case 4:
                return INCOMING_TRADE;
            case 5:
                return OUTGOING_TRADE;
        }
        throw new IllegalArgumentException("unknown TransactionType:" + i);
    }
}

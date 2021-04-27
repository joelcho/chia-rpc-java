// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community editionpackage com.github.joelcho.chia.types.wallet;
package com.github.joelcho.chia.types.wallet;

/**
 * @author Joel
 */
public enum WalletType {
    STANDARD_WALLET(0),
    RATE_LIMITED(1),
    ATOMIC_SWAP(2),
    AUTHORIZED_PAYEE(3),
    MULTI_SIG(4),
    CUSTODY(5),
    COLOURED_COIN(6),
    RECOVERABLE(7),
    DISTRIBUTED_ID(8),

    ;

    WalletType(int i) {
        this.value = i;
    }

    private final int value;

    public int toIntValue() {
        return value;
    }

    static WalletType fromInt(int i) {
        switch (i) {
            case 0:
                return STANDARD_WALLET;
            case 1:
                return RATE_LIMITED;
            case 2:
                return ATOMIC_SWAP;
            case 3:
                return AUTHORIZED_PAYEE;
            case 4:
                return MULTI_SIG;
            case 5:
                return CUSTODY;
            case 6:
                return COLOURED_COIN;
            case 7:
                return RECOVERABLE;
            case 8:
                return DISTRIBUTED_ID;
        }
        throw new IllegalArgumentException("unknown wallet type:" + i);
    }
}

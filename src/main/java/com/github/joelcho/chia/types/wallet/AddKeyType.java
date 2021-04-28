// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

/**
 * @author Joel
 */
public enum AddKeyType {
    NEW_WALLET, SKIP, RESTORE_BACKUP;

    @Override
    public String toString() {
        switch (this) {
            case SKIP:
                return "skip";
            case NEW_WALLET:
                return "new_wallet";
            case RESTORE_BACKUP:
                return "restore_backup";
        }
        return "";
    }
}

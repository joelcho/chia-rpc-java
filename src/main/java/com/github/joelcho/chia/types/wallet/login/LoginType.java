// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet.login;

/**
 * @author Joel
 */
public enum LoginType {
    None, Start, Skip, RestoreBackup;

    @Override
    public String toString() {
        switch (this) {
            case Skip:
                return "skip";
            case RestoreBackup:
                return "restore_backup";
            default:
                return "start";
        }
    }
}

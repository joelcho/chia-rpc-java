// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet.login;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Joel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginParamRestoreBackup extends LoginParam {
    private String filePath;

    public LoginParamRestoreBackup(long fingerprint, String filePath) {
        super.setFingerprint(fingerprint);
        super.setType(LoginType.RestoreBackup);
        this.filePath = filePath;
    }

    @Override
    public void setType(LoginType type) {
        /**/
    }
}

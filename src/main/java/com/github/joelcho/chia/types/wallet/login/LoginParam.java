// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet.login;

import lombok.Data;

/**
 * @author Joel
 */
@Data
public class LoginParam {
    private long fingerprint;
    private LoginType type;
}

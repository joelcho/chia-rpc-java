// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class FarmedAmountRsp {
    private Uint64 farmedAmount;
    private Uint64 poolRewardAmount;
    private Uint64 farmerRewardAmount;
    private Uint64 feeAmount;
    private long lastHeightFarmed;
}

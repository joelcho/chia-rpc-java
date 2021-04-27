// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.farmer;

import lombok.Data;

/**
 * @author Joel
 */
@Data
public class RewardTargetsRsp {
    private String farmerTarget;
    private String poolTarget;
    
    // #if searchForPrivateKey == true
    private boolean haveFarmerSk;
    private boolean havePoolSk;
    // #endif
}

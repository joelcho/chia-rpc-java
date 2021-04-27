// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.types.farmer.RewardTargetsRsp;
import com.github.joelcho.chia.types.farmer.SignagePointRsp;

import java.util.List;

/**
 * Farmer api interface
 *
 * @author Joel
 */
public interface Farmer {
    /**
     * Gets a signage point by signage point hash, as well as any winning proofs.
     *
     * @param spHash the hash of the challenge chain signage point
     */
    SignagePointRsp getSignagePoint(String spHash) throws Exception;

    /**
     * Gets a list of recent signage points as well as winning proofs.
     */
    List<SignagePointRsp> getSignagePoints() throws Exception;

    /**
     * Gets the addresses that the farmer is farming to.
     *
     * @param searchForPrivateKey whether to check if we own the private key for these addreses.
     *                            Can take a long time, and not guaranteed to return True.
     */
    RewardTargetsRsp getRewardTargets(boolean searchForPrivateKey) throws Exception;

    /**
     * Sets the reward targets in the farmer and configuration file.
     *
     * @param farmerTarget farmer target address
     * @param poolTarget   pool target address
     */
    void setRewardTargets(String farmerTarget, String poolTarget) throws Exception;
}

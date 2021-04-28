// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.action;

import com.github.joelcho.chia.types.farmer.RewardTargetsRsp;
import com.github.joelcho.chia.types.farmer.SignagePointRsp;

/**
 * Farmer action constants
 *
 * @author Joel
 */
public class FarmerAction {
    private FarmerAction() {
    }

    // region farmer actions
    public static final Action GET_SIGNAGE_POINT
            = new Action("get_signage_point", null, SignagePointRsp.class);
    public static final Action GET_SIGNAGE_POINTS
            = new Action("get_signage_points", "signage_points", SignagePointRsp.class, Action.SEC_TYPE_COLLECTION);
    public static final Action GET_REWARD_TARGETS
            = new Action("get_reward_targets", null, RewardTargetsRsp.class);
    public static final Action SET_REWARD_TARGETS
            = new Action("set_reward_targets", null, Void.class);
    // endregion
}

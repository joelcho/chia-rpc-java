// File created at: Wednesday, April 28, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.action;

import com.github.joelcho.chia.types.node.*;
import com.github.joelcho.chia.types.primitive.Uint128;

/**
 * Full node action constants
 *
 * @author Joel
 */
public class FullNodeAction {
    private FullNodeAction() {
    }

    // region full node actions
    public static final Action GET_BLOCKCHAIN_STATE
            = new Action("get_blockchain_state", "blockchain_state", BlockchainState.class);

    public static final Action GET_BLOCK
            = new Action("get_block", "block", Block.class);

    public static final Action GET_BLOCKS
            = new Action("get_blocks", "blocks", Block.class, Action.SEC_TYPE_COLLECTION);

    public static final Action GET_BLOCK_RECORD_BY_HEIGHT
            = new Action("get_block_record_by_height", "block_record", BlockRecord.class);

    public static final Action GET_BLOCK_RECORD
            = new Action("get_block_record", "block_record", BlockRecord.class);

    public static final Action GET_BLOCK_RECORDS
            = new Action("get_block_records", "block_records", BlockRecord.class, Action.SEC_TYPE_COLLECTION);

    public static final Action GET_UNFINISHED_BLOCK_HEADERS
            = new Action("get_unfinished_block_headers", "headers", UnfinishedHeaderBlock.class, Action.SEC_TYPE_COLLECTION);

    public static final Action GET_NETWORK_SPACE
            = new Action("get_network_space", "space", Uint128.class);

    public static final Action GET_ADDITIONS_AND_REMOVALS
            = new Action("get_additions_and_removals", null, AdditionsAndRemovals.class);

    public static final Action GET_INITIAL_FREEZE_PERIOD
            = new Action("get_initial_freeze_period", "INITIAL_FREEZE_END_TIMESTAMP", String.class);

    public static final Action GET_NETWORK_INFO
            = new Action("get_network_info", null, NetworkInfo.class);

    public static final Action GET_COIN_RECORDS_BY_PUZZLE_HASH
            = new Action("get_coin_records_by_puzzle_hash", "coin_records", CoinRecord.class, Action.SEC_TYPE_COLLECTION);

    public static final Action GET_COIN_RECORD_BY_NAME
            = new Action("get_coin_record_by_name", "coin_record", CoinRecord.class);

    public static final Action GET_ALL_MEMPOOL_TX_IDS
            = new Action("get_all_mempool_tx_ids", "tx_ids", String.class, Action.SEC_TYPE_COLLECTION);

    public static final Action GET_ALL_MEMPOOL_ITEMS
            = new ActionResultMap("get_all_mempool_items", "mempool_items", String.class, MempoolItem.class);

    public static final Action GET_MEMPOOL_ITEM_BY_TX_ID
            = new Action("get_mempool_item_by_tx_id", "mempool_item", MempoolItem.class);
    // endregion
}

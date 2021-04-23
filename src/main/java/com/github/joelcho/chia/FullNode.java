// File created at: Wednesday, April 21, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.types.node.*;
import com.github.joelcho.chia.types.primitive.Uint128;

import java.util.List;
import java.util.Map;

/**
 * Full Node api interface.
 *
 * @author Joel
 */
public interface FullNode {
    /**
     * Get current information about the blockchain, including the peak, sync information, difficulty, mempool size, etc.
     */
    BlockchainState getBlockchainState() throws Exception;

    /**
     * Gets a full block by header hash
     *
     * @param headerHash the header hash of the block to get
     */
    Block getBlock(String headerHash) throws Exception;

    /**
     * Gets a list of full blocks
     *
     * @param start             the start height
     * @param end               the end height (non-inclusive)
     * @param excludeHeaderHash whether to exclude the header hash in the response (default false)
     */
    List<Block> getBlocks(long start, long end, boolean excludeHeaderHash) throws Exception;

    /**
     * Retrieves recent unfinished header blocks.
     */
    List<UnfinishedHeaderBlock> getUnfinishedBlockHeaders() throws Exception;

    /**
     * Retrieves a block record by height (assuming the height <= peak height)
     *
     * @param height the height to get
     */
    BlockRecord getBlockRecordByHeight(long height) throws Exception;

    /**
     * Retrieves a block record by header hash
     *
     * @param headerHash the block's header_hash
     */
    BlockRecord getBlockRecord(String headerHash) throws Exception;

    /**
     * Retrieves block records in a range
     *
     * @param start the start height
     * @param end   the end height (non-inclusive)
     */
    List<BlockRecord> getBlockRecords(long start, long end) throws Exception;

    /**
     * Retrieves an estimate of the total plotted space of all farmers, in bytes.
     *
     * @param olderBlockHeaderHash the start header hash
     * @param newerBlockHeaderHash the end header hash
     */
    Uint128 getNetworkSpace(String olderBlockHeaderHash, String newerBlockHeaderHash) throws Exception;

    /**
     * Retrieves the additions and removals (state transitions) for a certain block.
     * Returns coin records for each addition and removal.
     *
     * @param headerHash           header hash of the block
     * @param newerBlockHeaderHash the end header hash
     */
    AdditionsAndRemovals getAdditionsAndRemovals(String headerHash, String newerBlockHeaderHash) throws Exception;

    /**
     * Retrieves the initial freeze period for the blockchain (no transactions allowed).
     */
    // TODO BUG
    long getInitialFreezePeriod() throws Exception;

    /**
     * Retrieves some information about the current network.
     */
    NetworkInfo getNetworkInfo() throws Exception;

    /**
     * Retrieves a list of coin records with a certain puzzle hash.
     *
     * @param puzzleHash        puzzle hash to search for
     * @param includeSpendCoins whether to include spent coins too, instead of just unspent
     * @param startHeight       confirmation start height for search. optional
     * @param endHeight         confirmation end height for search. optional
     */
    List<CoinRecord> getCoinRecordsByPuzzleHash(String puzzleHash, String includeSpendCoins, Long startHeight, Long endHeight) throws Exception;

    /**
     * Retrieves a coin record by its name/id.
     *
     * @param name coin name
     */
    CoinRecord getCoinRecordByName(String name) throws Exception;

    /**
     * Pushes a transaction / spend bundle to the mempool and blockchain.
     * Returns whether the spend bundle was successfully included into the mempool.
     *
     * @param spendBundle spend bundle to submit
     */
    MempoolInclusionStatus pushTx(SpendBundle spendBundle) throws Exception;

    /**
     * Returns a list of all transaction IDs (spend bundle hashes) in the mempool.
     */
    List<String> getAllMempoolTxIds() throws Exception;

    /**
     * Returns all items in the mempool.
     */
    Map<String, MempoolItem> getAllMempoolItems() throws Exception;

    /**
     * Gets a mempool item by tx id.
     *
     * @param txId spend bundle hash
     */
    MempoolItem getMempoolItemByTxId(String txId) throws Exception;
}

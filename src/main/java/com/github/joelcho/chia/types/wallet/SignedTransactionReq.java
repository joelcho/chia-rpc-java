// File created at: Saturday, May 08, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import com.github.joelcho.chia.types.node.Coin;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel
 */
@Data
public class SignedTransactionReq {
    @Data
    public static class Addition {
        private Uint64 amount;
        private String puzzleHash;
    }

    private List<Addition> additions;
    private List<Coin> coins;
    private Uint64 fee;

    public void addAddtion(Addition addition) {
        if (additions == null) {
            additions = new ArrayList<>();
        }
        additions.add(addition);
    }

    public void addCoin(Coin coin) {
        if (coins == null) {
            coins = new ArrayList<>();
        }
        coins.add(coin);
    }
}

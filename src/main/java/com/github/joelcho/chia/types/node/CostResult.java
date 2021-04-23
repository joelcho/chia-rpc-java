// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class CostResult {
    private Uint64 error;
    private List<NPC> npcList;
    private Uint64 cost;
}

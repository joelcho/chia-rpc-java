// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.github.joelcho.chia.types.primitive.Bytes32;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class NPC {
    private Bytes32 coinName;
    private Bytes32 puzzleHash;
    private Object conditions;// TODO;
}

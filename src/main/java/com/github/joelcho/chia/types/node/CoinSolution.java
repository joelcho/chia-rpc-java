// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.HexToByteArrayConverter;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class CoinSolution {
    private Coin coin;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] puzzleReveal;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] solution;
}

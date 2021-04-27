// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.harvester;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.HexToByteArrayConverter;
import com.github.joelcho.chia.types.node.G1Element;
import com.github.joelcho.chia.types.primitive.Bytes32;
import com.github.joelcho.chia.types.primitive.Uint64;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class PlotInfo {
    private String filename;
    private Uint64 size;
    @JsonProperty("plot-seed")
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] plotSeed; //TODO plot-seed
    private G1Element poolPublicKey;
    private Bytes32 poolContractPuzzleHash;
    private G1Element plotPublicKey;
    private Uint64 fileSize;
    private Uint64 timeModified;
}

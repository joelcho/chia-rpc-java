// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.HexToByteArrayConverter;
import com.github.joelcho.chia.types.primitive.Bytes32;
import lombok.Data;

/**
 * @author Joel
 */
@Data
public class ProofOfSpace {
    private Bytes32 challenge;
    private G1Element poolPublicKey;
    private Bytes32 poolContractPuzzleHash;
    private G1Element plotPublicKey;
    private int size;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] proof;
}

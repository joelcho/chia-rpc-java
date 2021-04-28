// File created at: Friday, April 23, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types.wallet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.HexToByteArrayConverter;
import com.github.joelcho.chia.converter.SeedConverter;
import lombok.Data;

import java.util.List;

/**
 * @author Joel
 */
@Data
public class PrivateKey {
    private long fingerprint;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] sk;
    @JsonDeserialize(converter = HexToByteArrayConverter.class)
    private byte[] pk;
    @JsonDeserialize(converter = SeedConverter.class)
    private List<String> seed;
}

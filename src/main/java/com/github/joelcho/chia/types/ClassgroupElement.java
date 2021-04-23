// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.joelcho.chia.converter.ClassgroupElementConverter;
import com.github.joelcho.chia.util.HexUtil;
import lombok.Data;

/**
 * @author Joel
 */
@Data
@JsonDeserialize(converter = ClassgroupElementConverter.class)
public class ClassgroupElement {

    public static int getSize() {
        return 100;
    }

    public static ClassgroupElement fromBytes(byte[] bytes) {
        return new ClassgroupElement(bytes);
    }

    public static ClassgroupElement fromHex(String x) throws RuntimeException {
        x = HexUtil.cleanHexPrefix(x);
        if (x.length() > getSize() * 2) {
            throw new IllegalArgumentException("cannot convert hex string to ClassgroupElement:" + x);
        }
        byte[] data = HexUtil.decode(x);
        return new ClassgroupElement(data);
    }

    private byte[] data;

    private ClassgroupElement(byte[] data) {
        this.data = data; // TODO 100
    }

    public String toHexString(boolean prefixed) {
        return HexUtil.toHexString(this.data, prefixed, true, getSize() * 2);
    }

    @Override
    public String toString() {
        return this.toHexString(false);
    }
}

// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia.action;

import java.lang.reflect.Type;

/**
 * @author Joel
 */
public class ActionResultMap extends Action {
    private final Type keyType;

    public ActionResultMap(String methodName, String resultFieldName, Type keyType, Type valueType) {
        super(methodName, resultFieldName, valueType, Action.SEC_TYPE_MAP);
        this.keyType = keyType;
    }

    public Type getKeyType() {
        return keyType;
    }
}

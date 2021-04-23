// File created at: Thursday, April 22, 2021
// File encoding  : UTF-8
// Line separator : LF
// Tab stop       : 4 spaces
// IDE            : IntelliJ IDEA community edition
package com.github.joelcho.chia;

import com.github.joelcho.chia.types.node.MempoolItem;

import java.lang.reflect.Type;

/**
 * @author Joel
 */
public class ResultMapAction extends Action {
    public static final Action GET_ALL_MEMPOOL_ITEMS
            = new ResultMapAction("get_all_mempool_items", "mempool_items", String.class, MempoolItem.class);

    private final Type keyType;

    public ResultMapAction(String methodName, String resultFieldName, Type keyType, Type valueType) {
        super(methodName, resultFieldName, valueType, Action.SEC_TYPE_MAP);
        this.keyType = keyType;
    }

    public Type getKeyType() {
        return keyType;
    }
}

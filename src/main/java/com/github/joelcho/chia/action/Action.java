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
public class Action {
    public static final int SEC_TYPE_VALUE = 0;
    public static final int SEC_TYPE_COLLECTION = 1;
    public static final int SEC_TYPE_MAP = 2;

    private final String methodName;
    private final String resultFieldName;
    private final Type returnType;
    private final int secondType;

    public Action(String methodName, String resultFieldName, Type returnType, int secondType) {
        this.methodName = methodName;
        this.resultFieldName = resultFieldName;
        this.returnType = returnType;
        this.secondType = secondType;
    }

    public Action(String methodName, String resultFieldName, Type returnType) {
        this(methodName, resultFieldName, returnType, SEC_TYPE_VALUE);
    }

    public String getMethodName() {
        return methodName;
    }

    public String getResultFieldName() {
        return resultFieldName;
    }

    public Type getReturnType() {
        return returnType;
    }

    public int getSecondType() {
        return secondType;
    }
}

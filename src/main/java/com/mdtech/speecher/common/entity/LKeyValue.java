package com.mdtech.speecher.common.entity;

public class LKeyValue {

    private Long key;
    private String val;

    public LKeyValue() {
    }

    public LKeyValue(Long key, String val) {
        this.key = key;
        this.val = val;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}

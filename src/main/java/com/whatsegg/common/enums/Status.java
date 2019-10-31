package com.whatsegg.common.enums;


public enum Status  {
    DELETED(-1, "已删除"),
    UNKNOWN(0, "未知"),
    NORMAL(1, "正常(有效)"),
    INVALID(2, "无效"),
    DISABLED(3, "失效"),; //目前购物车表使用到，代表失效购物车
    private final int code;
    private final String text;

    private Status(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer code() {
        return code;
    }

    public String text() {
        return text;
    }

    public static Status nameOf(String name) {
        try {
            return Status.valueOf(name);
        } catch (Exception e) {
        }

        return null;
    }

    public static Status codeOf(int code) {

        for (Status value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public boolean equals(Status status) {
        return status != null && this.toString().equals(status.toString());
    }
}

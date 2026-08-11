package cn.vskendo.demo.common.enums;

import lombok.Getter;

@Getter
public enum CtrlType {
    SUCCEED(200, "OK"),
    FAILED(500, "FAILED");

    CtrlType(int code, String msg) {
    }
}

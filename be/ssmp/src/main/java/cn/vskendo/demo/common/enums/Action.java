package cn.vskendo.demo.common.enums;

import lombok.Getter;

@Getter
public enum Action {
    ADD("add"),
    DELETE("delete"),
    UPDATE("update"),
    QUERY("query");

    Action(String action) {
    }

}

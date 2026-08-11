package cn.vskendo.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class JsonResult {
    private boolean success = false;
    private String msg = "";
    private Object obj = null;

    public static JsonResult success() {
        return success(null, null);
    }

    public static JsonResult success(Object data) {
        return success(null, data);
    }

    public static JsonResult success(String msg) {
        return success(msg, null);
    }

    public static JsonResult success(String msg, Object data) {
        return new JsonResult(true, msg, data);
    }


    public static JsonResult error(String msg, Object data) {
        return new JsonResult(false, msg, data);
    }

    public static JsonResult error(String message) {
        return error(message, null);
    }

    public static JsonResult error(Object data) {
        return error(null, data);
    }

    public static JsonResult error() {
        return success(null, null);
    }

}

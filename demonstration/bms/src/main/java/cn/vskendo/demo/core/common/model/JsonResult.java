package cn.vskendo.demo.core.common.model;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class JsonResult implements Serializable {
    private boolean success;
    private String msg;
    private Object obj;

    public static JsonResult judge(Object data) {
        if (ObjectUtils.isEmpty(data)) {
            return error();
        }
        if (data instanceof Boolean) {
            if (!(Boolean) data) return error(false);
        }
        return success(data);
    }

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
        return error(null, null);
    }

}

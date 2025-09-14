package cn.vskendo.demo.core.aop;

import cn.vskendo.demo.core.common.constants.HintMessage;
import cn.vskendo.demo.core.common.model.JsonResult;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<JsonResult> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        List<String> collect = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        log.warn(e.getMessage());
        if (ObjectUtils.isEmpty(collect)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResult.error(HintMessage.UNKNOWN_ERROR));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResult.error(collect.toString()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<JsonResult> UnknownException(Exception e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JsonResult.error(HintMessage.UNKNOWN_ERROR));
    }

}

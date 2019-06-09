package cn.tycoding.scst.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限异常处理
 *
 * @author tycoding
 * @date 2019-06-04
 */
public class GlobalException extends RuntimeException {

    @Getter
    @Setter
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }
}

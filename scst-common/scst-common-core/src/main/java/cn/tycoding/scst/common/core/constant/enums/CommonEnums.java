package cn.tycoding.scst.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Getter
@AllArgsConstructor
public enum CommonEnums {

    LOGIN_ERROR(500, "用户名或密码错误"),
    PARAM_ERROR(401, "参数错误"),
    USER_ERROR(500, "获取用户信息失败"),
    SYSTEM_ERROR(500, "系统内部错误"),
    FILE_ERROR(500, "文件上传失败");
    private final int code;
    private final String msg;
}

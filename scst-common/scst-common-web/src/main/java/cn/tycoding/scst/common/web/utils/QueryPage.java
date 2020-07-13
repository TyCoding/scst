package cn.tycoding.scst.common.web.utils;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Data
@ToString
public class QueryPage implements Serializable {

    private int page; //当前页
    private int limit; //每页显示的记录数
}

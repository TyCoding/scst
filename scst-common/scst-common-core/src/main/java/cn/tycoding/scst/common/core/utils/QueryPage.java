package cn.tycoding.scst.common.core.utils;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Data
@ToString
public class QueryPage implements Serializable {

    private int page; //当前页
    private int limit; //每页显示的记录数
}

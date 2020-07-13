package cn.tycoding.scst.common.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller公共层代码提取
 *
 * @author tycoding
 * @date 2020/7/13
 */
public class BaseController {

    public Map<String, Object> getData(IPage<?> page) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }

}

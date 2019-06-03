package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.entity.SysDept;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysDeptService extends BaseService<SysDept> {

    /**
     * 根据部门ID获取部门数据
     *
     * @param id
     * @return
     */
    SysDept findById(Long id);
}

package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.common.repository.MapperRepository;
import cn.tycoding.scst.system.api.entity.SysDept;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysDeptMapper extends MapperRepository<SysDept> {

    /**
     * 改变部门表节点排序
     *
     * @param id
     */
    void changeTopNode(Long id);
}

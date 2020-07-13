package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.system.api.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 改变部门表节点排序
     *
     * @param id
     */
    void changeTopNode(Long id);
}

package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysDept;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysDeptService extends BaseService<SysDept> {

    /**
     * 查询部门Tree树
     *
     * @return
     */
    List<Tree<SysDept>> tree();

    /**
     * 根据部门ID获取部门数据
     *
     * @param id
     * @return
     */
    SysDept findById(Long id);

    /**
     * @param dept
     * @return
     */
    List<SysDept> list(SysDept dept);

    /**
     * 添加部门
     *
     * @param dept
     */
    void add(SysDept dept);

    /**
     * 删除部门
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新部门
     *
     * @param dept
     */
    void update(SysDept dept);
}

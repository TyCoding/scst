package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysDept;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门Tree树
     *
     * @return
     */
    List<Tree<SysDept>> tree();

    /**
     * @param dept
     * @return
     */
    IPage<SysDept> list(SysDept dept, QueryPage queryPage);

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

    /**
     * 校验当前名称是否存在
     *
     * @param name 名称
     * @param id   ID，如果是修改操作，name就一定存在
     * @return
     */
    boolean checkName(String name, String id);
}

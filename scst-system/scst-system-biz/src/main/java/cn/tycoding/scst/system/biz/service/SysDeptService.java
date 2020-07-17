package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysDept;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门Tree树
     *
     * @return
     */
    List<Tree<SysDept>> tree();

    /**
     * 分页、条件查询
     */
    IPage<SysDept> list(SysDept sysDept, QueryPage queryPage);

    /**
     * 条件查询
     */
    List<SysDept> list(SysDept sysDept);

    /**
     * 添加部门
     */
    void add(SysDept dept);

    /**
     * 删除部门
     */
    void delete(Long id);

    /**
     * 更新部门
     */
    void update(SysDept sysDept);

    /**
     * 校验当前名称是否存在
     */
    boolean checkName(String name, String id);
}

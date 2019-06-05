package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysDept;
import cn.tycoding.scst.system.api.utils.TreeUtils;
import cn.tycoding.scst.system.biz.mapper.SysDeptMapper;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<Tree<SysDept>> tree() {
        List<SysDept> deptList = list(new SysDept());
        List<Tree<SysDept>> treeList = new ArrayList<>();
        deptList.forEach(dept -> {
            Tree<SysDept> tree = new Tree<>();
            tree.setId(dept.getId());
            tree.setParentId(dept.getParentId());
            tree.setName(dept.getName());
            treeList.add(tree);
        });
        return TreeUtils.build(treeList);
    }

    @Override
    public SysDept findById(Long id) {
        Example example = new Example(SysDept.class);
        example.createCriteria().andCondition("id=", id);
        List<SysDept> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<SysDept> list(SysDept dept) {
        try {
            Example example = new Example(SysDept.class);
            if (StringUtils.isNoneBlank(dept.getName())) {
                example.createCriteria().andCondition("name=", dept.getName());
            }
            example.setOrderByClause("create_time");
            return this.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void add(SysDept dept) {
        Long pId = dept.getParentId();
        if (pId == null) {
            dept.setParentId(0L);
        }
        dept.setCreateTime(new Date());
        this.save(dept);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysDeptMapper.deleteByPrimaryKey(id);
        sysDeptMapper.changeTopNode(id);
    }

    @Override
    @Transactional
    public void update(SysDept dept) {
        this.updateNotNull(dept);
    }
}

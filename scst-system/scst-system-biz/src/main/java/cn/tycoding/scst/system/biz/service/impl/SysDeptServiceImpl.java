package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.entity.SysDept;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept> implements SysDeptService {

    @Override
    public SysDept findById(Long id) {
        Example example = new Example(SysDept.class);
        example.createCriteria().andCondition("id=", id);
        List<SysDept> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }
}

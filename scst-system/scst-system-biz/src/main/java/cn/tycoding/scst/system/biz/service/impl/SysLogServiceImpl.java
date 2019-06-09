package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.common.log.utils.AddressUtil;
import cn.tycoding.scst.system.biz.mapper.SysLogMapper;
import cn.tycoding.scst.system.biz.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-08
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public List<SysLog> list(SysLog log) {
        try {
            Example example = new Example(SysLog.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(log.getUsername())) {
                criteria.andCondition("username=", log.getUsername().toLowerCase());
            }
            if (StringUtils.isNotBlank(log.getOperation())) {
                criteria.andCondition("operation like", "%" + log.getOperation() + "%");
            }
            if (StringUtils.isNotBlank(log.getLocation())) {
                criteria.andCondition("location like", "%" + log.getLocation() + "%");
            }
            if (StringUtils.isNotBlank(log.getTimeField())) {
                String[] split = log.getTimeField().split(",");
                criteria.andCondition("date_format(CREATE_TIME, '%Y-%m-%d') >=", split[0]);
                criteria.andCondition("date_format(CREATE_TIME, '%Y-%m-%d') <=", split[1]);
            }
            example.setOrderByClause("create_time desc");
            return sysLogMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void delete(Long id) {
        sysLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveLog(SysLog log) {
        log.setCreateTime(new Date());
        log.setLocation(AddressUtil.getAddress(log.getIp()));
        sysLogMapper.insert(log);
    }
}

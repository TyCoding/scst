package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.log.utils.AddressUtil;
import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.system.biz.mapper.SysLogMapper;
import cn.tycoding.scst.system.biz.service.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public IPage<SysLog> list(SysLog log, QueryPage queryPage) {
        IPage<SysLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(log.getUsername()), SysLog::getUsername, log.getUsername().toLowerCase());
        queryWrapper.like(StringUtils.isNotBlank(log.getOperation()), SysLog::getOperation, log.getOperation());
        queryWrapper.like(StringUtils.isNotBlank(log.getLocation()), SysLog::getLocation, log.getLocation());
        if (StringUtils.isNotBlank(log.getTimeField())) {
            String[] split = log.getTimeField().split(",");
            queryWrapper.apply("date_format(CREATE_TIME, '%Y-%m-%d') >=", split[0]);
            queryWrapper.apply("date_format(CREATE_TIME, '%Y-%m-%d') <=", split[1]);
        }
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return sysLogMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void delete(Long id) {
        sysLogMapper.deleteById(id);
    }

    @Override
    public void saveLog(SysLog log) {
        log.setCreateTime(new Date());
        log.setLocation(AddressUtil.getAddress(log.getIp()));
        sysLogMapper.insert(log);
    }
}

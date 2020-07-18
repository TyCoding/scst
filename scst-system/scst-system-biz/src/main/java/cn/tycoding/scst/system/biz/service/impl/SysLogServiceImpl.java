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
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<SysLog> list(SysLog log, QueryPage queryPage) {
        IPage<SysLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(log.getUsername()), SysLog::getUsername, log.getUsername());
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void add(SysLog log) {
        log.setCreateTime(new Date());
        log.setLocation(AddressUtil.getAddress(log.getIp()));
        baseMapper.insert(log);
    }
}

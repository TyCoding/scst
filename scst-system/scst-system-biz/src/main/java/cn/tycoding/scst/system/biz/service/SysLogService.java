package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @date 2019-06-08
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页、条件查询日志列表
     *
     * @param log
     * @return
     */
    IPage<SysLog> list(SysLog log, QueryPage queryPage);

    /**
     * 根据ID删除日志记录
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 保存操作日志
     *
     * @param log
     */
    void saveLog(SysLog log);
}

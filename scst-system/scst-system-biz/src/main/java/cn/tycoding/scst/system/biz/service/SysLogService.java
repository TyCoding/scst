package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.system.api.entity.SysLog;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-08
 */
public interface SysLogService {

    /**
     * 分页、条件查询日志列表
     *
     * @param log
     * @return
     */
    List<SysLog> list(SysLog log);

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

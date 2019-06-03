package cn.tycoding.scst.common.repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * tk.mapper通用Mapper接口
 *
 * @author tycoding
 * @date 2019-06-02
 */
public interface MapperRepository<T> extends Mapper<T>, MySqlMapper<T> {
}

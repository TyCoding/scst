package cn.tycoding.scst.common.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用Service接口
 *
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public interface BaseService<T> {

    List<T> selectAll();

    T selectByKey(Object key);

    void save(T entity);

    void batchDelete(List<Long> ids, String property, Class<T> clazz);

    void updateAll(T entity);

    void updateNotNull(T entity);

    List<T> selectByExample(Object example);
}

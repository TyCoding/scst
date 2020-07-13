package cn.tycoding.scst.system.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Tree<T> {

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * icon
     */
    private String icon;

    /**
     * 节点类型
     */
    private String type;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 子节点
     */
    private List<Tree<T>> children = new ArrayList<>();
}

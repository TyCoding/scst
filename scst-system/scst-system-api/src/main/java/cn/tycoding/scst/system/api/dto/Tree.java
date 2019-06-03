package cn.tycoding.scst.system.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@Data
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
     * 节点URL
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子节点
     */
    private List<Tree<T>> children = new ArrayList<>();
}

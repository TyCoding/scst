package cn.tycoding.scst.system.api.utils;

import cn.tycoding.scst.system.api.dto.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-03
 */
public class TreeUtils {

    public static <T> List<Tree<T>> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }

        List<Tree<T>> treeList = new ArrayList<>();
        nodes.forEach(children -> {
            //获取父节点ID
            Long pid = children.getParentId();
            if (pid == null || pid.equals(0L)) {
                //说明是父节点，直接添加，无需向下执行
                treeList.add(children);
                return;
            }
            //说明此节点不是父节点，遍历所有节点
            for (Tree<T> parent : nodes) {
                Long id = parent.getId();
                if (id != null && id.equals(pid)) {
                    //说明当前节点是父节点pid的子节点
                    parent.getChildren().add(children);
                    return;
                }
            }
        });
        return treeList;
    }
}

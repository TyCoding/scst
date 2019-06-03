package cn.tycoding.scst.system.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 *
 * @author tycoding
 * @date 2019-06-03
 */
@Data
@Table(name = "sys_menu")
public class SysMenu implements Serializable {

    private static final String TYPE_MENU = "menu";
    private static final String TYPE_BUTTON = "button";

    /**
     * 菜单ID
     */
    @Id
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级菜单ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 图标
     */
    private String icon;

    /**
     * Vue组件
     */
    private String component;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 前端URL
     */
    private String url;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url.trim();
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? "" : permission.trim();
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? "" : icon.trim();
    }
}

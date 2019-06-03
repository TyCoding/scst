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
 * 部门表
 *
 * @author tycoding
 * @date 2019-06-03
 */
@Data
@Table(name = "sys_dept")
public class SysDept implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 上级部门ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 名称
     */
    private String name;

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
}

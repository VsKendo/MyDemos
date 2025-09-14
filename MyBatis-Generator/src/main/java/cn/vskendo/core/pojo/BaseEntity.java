package cn.vskendo.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 * 用于自动注入操作记录
 * 需确保这些字段对应数据库里的字段名:
 * `created_by`    varchar(18)                 default NULL,
 * `created_time`  datetime           NOT NULL default CURRENT_TIMESTAMP,
 * `updated_by`    varchar(18)                 default NULL,
 * `updated_time`  datetime                    default CURRENT_TIMESTAMP,
 */
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    /**
     *
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    /**
     *
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Date getUpdatedTime() {
        return this.updatedTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}

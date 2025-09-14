package cn.vskendo.demo.core.common.pojo;

import cn.vskendo.demo.core.valid.Insert;
import cn.vskendo.demo.core.valid.Query;
import cn.vskendo.demo.core.valid.Update;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 账户表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    /**
     * 登录用户名
     */
    @NotBlank(groups = {Query.class, Insert.class, Update.class}, message = "缺少用户名")
    private String username;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号前缀
     */
    private String phonePre;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 账号状态：是否被封号等
     */
    private Short accountState;

    private String createdBy;

    private LocalDateTime createTime;

    private String updatedBy;

    private LocalDateTime updateTime;
}

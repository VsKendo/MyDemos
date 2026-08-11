package cn.vskendo.demo.core.common.model.pojo;

import cn.vskendo.demo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * <p>
 * 账户表
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    /**
     * 登录用户名
     */
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
    private Integer phonePre;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 账号状态：是否被禁用等
     */
    private Byte accountState;
}

package cn.vskendo.demo.core.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 基金基础信息表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
public class Fund implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 基金类型，如混合型、债券型等
     */
    private String fundType;

    /**
     * 基金管理人或公司
     */
    private String manager;

    /**
     * 成立日期
     */
    private LocalDate setupDate;

    /**
     * 状态：1=运作中，0=已清算
     */
    private Byte status;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;
}

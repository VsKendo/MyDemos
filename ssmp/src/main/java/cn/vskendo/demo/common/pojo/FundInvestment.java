package cn.vskendo.demo.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 模拟投资记录表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
@TableName("fund_investment")
public class FundInvestment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 买入日期
     */
    private LocalDate investDate;

    /**
     * 投入金额（总金额）
     */
    private BigDecimal investAmount;

    /**
     * 买入时单位净值
     */
    private BigDecimal unitPrice;

    /**
     * 买入的份额 = 投入金额 / 单位净值
     */
    private BigDecimal share;

    /**
     * 备注（如模拟组合名、策略说明）
     */
    private String note;

    private LocalDateTime createTime;
}

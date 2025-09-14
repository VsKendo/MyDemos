package cn.vskendo.demo.core.common.pojo;

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

/**
 * <p>
 * 基金分析指标表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
@TableName("fund_analysis")
public class FundAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 分析日期
     */
    private LocalDate date;

    /**
     * 近 7 日平均涨幅
     */
    private BigDecimal avg7dGrowth;

    /**
     * 最大回撤（%）
     */
    private BigDecimal maxDrawdown;

    /**
     * 20 日移动平均
     */
    private BigDecimal ma20;

    /**
     * 策略标签，如高波动、稳健等
     */
    private String strategyTag;
}

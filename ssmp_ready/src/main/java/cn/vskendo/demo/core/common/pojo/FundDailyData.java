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
import java.time.LocalDateTime;

/**
 * <p>
 * 基金每日行情数据
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
@TableName("fund_daily_data")
public class FundDailyData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 交易日期
     */
    private LocalDate date;

    /**
     * 单位净值
     */
    private BigDecimal netValue;

    /**
     * 涨跌幅（%）
     */
    private BigDecimal growthRate;

    /**
     * 估值（如有）
     */
    private BigDecimal estimateValue;

    /**
     * 成交量（如有）
     */
    private Long volume;

    /**
     * 数据入库时间
     */
    private LocalDateTime createTime;
}

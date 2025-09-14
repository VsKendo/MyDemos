package cn.vskendo.demo.core.common.model.pojo;

import cn.vskendo.demo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * <p>
 * 储蓄表
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@Data
@Accessors(chain = true)
@TableName("debit_card")
@EqualsAndHashCode(callSuper = true)
public class DebitCard extends BaseEntity {

    @TableId(value = "debit_card_id", type = IdType.AUTO)
    private Integer debitCardId;

    /**
     * 银行卡号
     */
    private String debitCardNumber;

    /**
     * 银行卡 CSV
     */
    private String csv;

    /**
     * 账户余额，单位为分
     */
    private Integer moneyAmount;

    /**
     * 银行卡状态：是否被禁用等
     */
    private Short cardState;
}

package cn.vskendo.demo.core.common.dto;

import cn.vskendo.demo.core.valid.Insert;
import cn.vskendo.demo.core.valid.Update;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class FundDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 基金代码
     */
    @NotBlank(groups = {Insert.class, Update.class}, message = "基金代码不能为空")
    private String fundCode;

    /**
     * 基金名称
     */
    @NotBlank(groups = {Insert.class}, message = "基金名不能为空")
    private String fundName;

    /**
     * 基金类型，如混合型、债券型等
     */
    private String fundType;
}

package cn.vskendo.demo.common.pojo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by Mybatis Generator on 2025/03/23
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TagObject {
    private Integer toId;

    private String tagName;

    private String targetName;

    private String note;
}
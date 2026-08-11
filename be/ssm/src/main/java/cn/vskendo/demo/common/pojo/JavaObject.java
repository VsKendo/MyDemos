package cn.vskendo.demo.common.pojo;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JavaObject {
    private Integer jcoId;

    private String packageName;

    private Integer accessModifiers;

    private String oName;

    private String mainType;

    private String mainTag;

    private String note;
}
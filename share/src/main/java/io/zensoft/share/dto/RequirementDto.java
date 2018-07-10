package io.zensoft.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by temirlan on 7/5/18.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequirementDto {
    private String name;
    private int type;
}

package io.zensoft.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by temirlan on 7/11/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDto {
    private int id;
    private String name;
}

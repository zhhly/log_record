package com.zh.log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * User类
 * </p>
 *
 * @author zh
 * @date 2025-06-19 21:26
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    Integer id;
    String name;
    Integer age;
}

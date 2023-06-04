package com.recipes.entity.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class EmployeeDto {
    Integer userId;

    String username;

    String realName;

    String avatar;

    String desc;

    String[] roles;
}

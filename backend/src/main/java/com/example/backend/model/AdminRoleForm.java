package com.example.backend.model;


import com.example.backend.enumeration.Profile;

import lombok.Data;

@Data
public class AdminRoleForm {
 private Profile profile;
 private String roleName;
}

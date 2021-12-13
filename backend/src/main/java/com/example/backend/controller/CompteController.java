package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.AdminRoleForm;
import com.example.backend.service.CompteService;

@RestController
@RequestMapping("/odcmanager/api/v1")
public class CompteController {
	@Autowired
	private CompteService compteService;
	@PostMapping(path="/AddRoleAdmin")
	void AddAdminToUser(@RequestBody AdminRoleForm adminRoleForm) {
		compteService.AddAdminToRole(adminRoleForm.getProfile(), adminRoleForm.getRoleName());
	}

}

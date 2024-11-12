package com.MotherSon.CRM.security.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.models.RolePermission;
import com.MotherSon.CRM.repository.RolePermissionsActionsRepository;
import com.MotherSon.CRM.repository.RolePermissionsRepository;
import com.MotherSon.CRM.repository.RoleRepository;
import com.MotherSon.CRM.rolepermission.RolePermissionActions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ms.jwt.models.Role;
//import com.ms.jwt.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository genericRepository;
	
	@Autowired
    private RolePermissionsActionsRepository rolePermissionRepository;


	@Autowired
    private RolePermissionsRepository rolePermissionActionsRepository;

    @Autowired
    private ObjectMapper objectMapper;
	
    
    @Transactional
	public void saveGeneric(String generic)throws IOException  {
    	
    	
    	JsonNode rootNode = objectMapper.readTree(generic);
        String roleName = rootNode.get("name").asText();
        
        String roleDes = rootNode.get("description").asText();
        
        // Create Role
        Role role = new Role();
        role.setName(roleName);
        
        role.setDescription(roleDes);
         

        // Iterate over permissions
        JsonNode permissionsNode = rootNode.get("permissions");
        List<RolePermission> rolePermissions = new ArrayList<>();

        for (JsonNode permissionNode : permissionsNode) {
            String module = permissionNode.get("module").asText();

            // Create RolePermission
            RolePermission rolePermission = new RolePermission();
            rolePermission.setModule(module);
            rolePermission.setRole(role);

            // Iterate over actions
            JsonNode actionsNode = permissionNode.get("actions");
            List<RolePermissionActions> actions = new ArrayList<RolePermissionActions>();

            for (JsonNode actionNode : actionsNode) {
                String action = actionNode.asText();

                // Create RolePermissionActions
                RolePermissionActions rolePermissionAction = new RolePermissionActions();
                rolePermissionAction.setAction(action);
                rolePermissionAction.setRolePermission(rolePermission);

                actions.add(rolePermissionAction);
            }

            rolePermission.setActions(actions);
            rolePermissions.add(rolePermission);
        }

        role.setPermissions(rolePermissions);
        genericRepository.save(role);
	}
}

package edu.mum.springbatchproject.service.Imp;


import edu.mum.springbatchproject.domain.Role;
import edu.mum.springbatchproject.repository.RoleRepo;
import edu.mum.springbatchproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void saveRole(Role role) {
        if(null != role){
            if(!roleRepo.existsRoleByType(role.getType())){
                roleRepo.save(role);
            }
        }
    }

    @Override
    public Role getRole(String role) {
        List<Role> roleList = roleRepo.findAll();
        for(Role role1: roleList){
            if(role1.getRoleType().equalsIgnoreCase(role)) return role1;
        }
        return null;
    }


}

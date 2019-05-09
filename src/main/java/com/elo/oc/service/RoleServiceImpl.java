package com.elo.oc.service;

import com.elo.oc.dao.RoleDAO;
import com.elo.oc.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void saveRole(Role theRole) {
        roleDAO.saveRole(theRole);
    }

    @Override
    public List<Role> getRolesPublic() {
        return roleDAO.getRolesPublic();
    }

    @Override
    public void deleteRole(Integer id) {
        roleDAO.deleteRole(id);
    }

    @Override
    
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Override
    public Role findById(Integer id) {
        return roleDAO.findById(id);
    }

    @Override
    public Optional<Role> findRole(String roleName) {
        return roleDAO.getRoles().stream()
                .filter(role -> role.getRoleName().equals(roleName))
                .findFirst();
    }
}

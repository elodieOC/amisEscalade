package com.elo.oc.service;

import com.elo.oc.dao.RoleDAO;
import com.elo.oc.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public void saveRole(Role theRole) {
        roleDAO.saveRole(theRole);
    }

    @Override
    @Transactional
    public List<Role> getRolesPublic() {
        return roleDAO.getRolesPublic();
    }

    @Override
    @Transactional
    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Override
    @Transactional
    public Role findById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    @Transactional
    public Optional<Role> findRole(String roleName) {
        return roleDAO.getRoles().stream()
                .filter(role -> role.getRoleName().equals(roleName))
                .findFirst();
    }
}

package com.elo.oc.service;

import com.elo.oc.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void saveRole(Role theRole);
    void deleteRole(Integer id);
    List < Role > getRolesPublic();

    List < Role > getRoles();
    Role findById(Integer id);
    Optional<Role> findRole(String name);





}

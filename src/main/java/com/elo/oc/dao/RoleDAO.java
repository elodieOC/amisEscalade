package com.elo.oc.dao;

import com.elo.oc.entity.Role;

import java.util.List;

public interface RoleDAO {

    void saveRole(Role theRole);
    void deleteRole(int id);

    List < Role > getRoles();
    List < Role > getRolesPublic();
    Role findById(int id);





}

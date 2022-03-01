package com.example.Eschool.Repository;

import com.example.Eschool.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
    List<Role> findAll();

}

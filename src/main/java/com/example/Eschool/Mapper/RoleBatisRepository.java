package com.example.Eschool.Mapper;

import com.example.Eschool.Entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleBatisRepository {
    @Insert("INSERT into roles values (null,#{name});")
    int insertRole(Role role);

    @Select("Select * from roles where name=#{name}")
    Role findRoleByName(String name);

    @Select("Select * from roles")
    List<Role> getAllRoles();

}

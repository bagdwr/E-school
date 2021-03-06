package com.example.Eschool.Repository;

import com.example.Eschool.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}

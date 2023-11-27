package com.dbms.usaccidents.usaccidentsanalysis.repository;

import com.dbms.usaccidents.usaccidentsanalysis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLoginid(String loginId);

    boolean existsByLoginid(String loginId);
}

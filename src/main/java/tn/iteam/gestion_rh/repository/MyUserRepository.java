package tn.iteam.gestion_rh.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.iteam.gestion_rh.entity.MyUser;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

  
}
package com.gab.cashonline.cashBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gab.cashonline.cashBE.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

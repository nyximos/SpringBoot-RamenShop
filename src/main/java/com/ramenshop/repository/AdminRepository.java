package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}

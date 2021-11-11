package com.ramenshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramenshop.data.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}

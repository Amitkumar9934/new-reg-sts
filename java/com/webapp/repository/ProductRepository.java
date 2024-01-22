package com.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}

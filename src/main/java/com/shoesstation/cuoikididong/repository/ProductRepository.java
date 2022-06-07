package com.shoesstation.cuoikididong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoesstation.cuoikididong.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
 
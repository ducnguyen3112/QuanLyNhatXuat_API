package com.shoesstation.cuoikididong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoesstation.cuoikididong.entity.DeliveryDocket;


@Repository
public interface DeliveryDocketRepository extends JpaRepository<DeliveryDocket, Integer> {

}
 
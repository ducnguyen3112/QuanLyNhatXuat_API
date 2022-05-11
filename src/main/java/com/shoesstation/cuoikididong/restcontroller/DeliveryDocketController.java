package com.shoesstation.cuoikididong.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.DeliveryDocket;
import com.shoesstation.cuoikididong.repository.DeliveryDocketRepository;

@RestController
public class DeliveryDocketController {
	
	@Autowired
	private DeliveryDocketRepository deliveryDocketRepository;
	@GetMapping("/deliveryDockets")
	public List<DeliveryDocket> findAllDeliveryDockets(){
		return deliveryDocketRepository.findAll();
	}
}

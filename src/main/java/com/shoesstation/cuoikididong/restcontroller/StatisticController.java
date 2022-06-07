package com.shoesstation.cuoikididong.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.dto.Statistics;
import com.shoesstation.cuoikididong.entity.DeliveryDocket;
import com.shoesstation.cuoikididong.entity.DeliveryDocketDetail;
import com.shoesstation.cuoikididong.entity.Product;
import com.shoesstation.cuoikididong.entity.ReceivedDocket;
import com.shoesstation.cuoikididong.entity.ReceivedDocketDetail;
import com.shoesstation.cuoikididong.repository.DeliveryDocketRepository;
import com.shoesstation.cuoikididong.repository.ProductRepository;
import com.shoesstation.cuoikididong.repository.ReceivedDocketRepository;
import com.shoesstation.cuoikididong.repository.TopProductRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/statistics")
public class StatisticController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private DeliveryDocketRepository deliveryRepository;
	@Autowired
	private ReceivedDocketRepository ReceivedRepository;
	@Autowired
	private TopProductRepository topProductRepository;
	@GetMapping
	public Statistics loadData() {
		Statistics statistics=new Statistics();
		List<Product> products=new ArrayList<Product>();
		products=productRepository.findAll();
		int slt=0;
		int valueslt=0;
		for (Product product : products) {
			slt+=product.getInventory();
			valueslt+=product.getInventory()+product.getPrice();
		}
		statistics.setInventory(slt);
		statistics.setValueInventory(valueslt);
		int numDelivery=0;
		int valueDelivery=0;
		List<DeliveryDocket> deliveryDockets=new ArrayList<DeliveryDocket>();
		deliveryDockets=deliveryRepository.findAll();
		for (DeliveryDocket deliveryDocket : deliveryDockets) {
			numDelivery++;
			for (DeliveryDocketDetail deliveryDocketDetail : deliveryDocket.getDeliveryDocketDetails()) {
				valueDelivery+=deliveryDocketDetail.getQuantity()*deliveryDocketDetail.getPrice();
			}
		}
		statistics.setNumDelivery(numDelivery);
		statistics.setValueDelivery(valueDelivery);
		int numReceived=0;
		int valueReceived=0;
		List<ReceivedDocket> ReceivedDockets=new ArrayList<ReceivedDocket>();
		ReceivedDockets=ReceivedRepository.findAll();
		for (ReceivedDocket ReceivedDocket : ReceivedDockets) {
			numReceived++;
			for (ReceivedDocketDetail ReceivedDocketDetail : ReceivedDocket.getReceivedDocketDetails()) {
				valueReceived+=ReceivedDocketDetail.getQuantity()*ReceivedDocketDetail.getPrice();
			}
		}
		statistics.setNumReceived(numReceived);
		statistics.setValueReceived(valueReceived);
		statistics.setListProducts(topProductRepository.topFiveProducts());
		
		return statistics;
	}
}

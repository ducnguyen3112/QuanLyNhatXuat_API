package com.shoesstation.cuoikididong.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.DeliveryDocketDetail;
import com.shoesstation.cuoikididong.entity.Product;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestResponse;
import com.shoesstation.cuoikididong.repository.DeliveryDocketDetailRepository;
import com.shoesstation.cuoikididong.repository.ProductRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/deliveryDocketDetails")
public class DeliveryDocketDetailController {
	
	@Autowired
	private DeliveryDocketDetailRepository deliveryDocketDetailRepository;
	@Autowired
	private ProductRepository productRepository;
	@GetMapping
	public List<DeliveryDocketDetail> findAlldeliveryDocketDetails(){
		return deliveryDocketDetailRepository.findAll();
	}
	@GetMapping("/{id}")
	public DeliveryDocketDetail getdeliveryDocketDetail(@PathVariable int id){
		Optional<DeliveryDocketDetail> deliveryDocketDetailOptional=deliveryDocketDetailRepository.findById(id);
		DeliveryDocketDetail deliveryDocketDetail=null;
		if (deliveryDocketDetailOptional.isPresent()) {
			deliveryDocketDetail=deliveryDocketDetailOptional.get();
		}else {
			throw new ResourceNotFoundException("Kh??ng t??m chi ti???t phi???u xu???t c?? m?? id: " +id);
		}
		return deliveryDocketDetail;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public DeliveryDocketDetail savedeliveryDocketDetail(@RequestBody DeliveryDocketDetail deliveryDocketDetail) {
		deliveryDocketDetail.setId(0);
		Optional<Product> productOptional=productRepository.findById(deliveryDocketDetail.getProductId());
		
		if (!productOptional.isPresent()) {
			throw new ResourceNotFoundException("S???n ph???m c?? id: " +deliveryDocketDetail.getProductId()+" Kh??ng t???n t???i");
		}else {
			Product product=productOptional.get();
			int inventory=product.getInventory();
			if (inventory < deliveryDocketDetail.getQuantity()) {
				throw new RuntimeException("V?????t qu?? s??? l?????ng t???n kho(<="+inventory+")");
			}else {
				inventory-=deliveryDocketDetail.getQuantity();
				product.setInventory(inventory);
				productRepository.save(product);
			}
		}
		
		return deliveryDocketDetailRepository.save(deliveryDocketDetail);
	}
	
	
	@PutMapping("/{id}")
	public DeliveryDocketDetail updatedeliveryDocketDetail (@PathVariable int id,@RequestBody DeliveryDocketDetail deliveryDocketDetail) {
		Optional<DeliveryDocketDetail> deliveryDocketDetailOptional=deliveryDocketDetailRepository.findById(id);
		DeliveryDocketDetail c=null;
		if (deliveryDocketDetailOptional.isPresent()) {
			c=deliveryDocketDetailOptional.get();
			deliveryDocketDetail.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Kh??ng t??m th???y chi ti???t phi???u xu???t c?? id: " +id);
		}
		return deliveryDocketDetailRepository.save(deliveryDocketDetail);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestResponse> deletedeliveryDocketDetail(@PathVariable int id) {
		Optional<DeliveryDocketDetail> deliveryDocketDetailOptional=deliveryDocketDetailRepository.findById(id);
		if (!deliveryDocketDetailOptional.isPresent()) {
			throw new ResourceNotFoundException("Kh??ng t??m th???y chi ti???t phi???u xu???t c?? id: " +id);
		}
		deliveryDocketDetailRepository.deleteById(id);
		return ResponseEntity.ok(new RestResponse("Chi ti???t phi???u xu???t c?? id: "+id+" ???? ???????c x??a!", HttpStatus.OK,LocalDateTime.now()));
		
	}
}

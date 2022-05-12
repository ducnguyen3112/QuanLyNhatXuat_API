package com.shoesstation.cuoikididong.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.DeliveryDocket;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestErrorResponse;
import com.shoesstation.cuoikididong.repository.DeliveryDocketRepository;


@RestController
@RequestMapping("/deliveryDockets")
public class DeliveryDocketController {
	
	@Autowired
	private DeliveryDocketRepository deliveryDocketRepository;
	@GetMapping
	public List<DeliveryDocket> findAlldeliveryDockets(){
		return deliveryDocketRepository.findAll();
	}
	@GetMapping("/{id}")
	public DeliveryDocket getdeliveryDocket(@PathVariable int id){
		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
		DeliveryDocket deliveryDocket=null;
		if (deliveryDocketOptional.isPresent()) {
			deliveryDocket=deliveryDocketOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có mã id: " +id);
		}
		return deliveryDocket;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public DeliveryDocket savedeliveryDocket(@RequestBody DeliveryDocket deliveryDocket) {
		deliveryDocket.setId(0);
		return deliveryDocketRepository.save(deliveryDocket);
	}
	
	
	@PutMapping("/{id}")
	public DeliveryDocket updatedeliveryDocket (@PathVariable int id,@RequestBody DeliveryDocket deliveryDocket) {
		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
		DeliveryDocket c=null;
		if (deliveryDocketOptional.isPresent()) {
			c=deliveryDocketOptional.get();
			deliveryDocket.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
		}
		return deliveryDocketRepository.save(deliveryDocket);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestErrorResponse> deletedeliveryDocket(@PathVariable int id) {
		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
		if (!deliveryDocketOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
		}
		deliveryDocketRepository.deleteById(id);
		return ResponseEntity.ok(new RestErrorResponse("Phiếu xuất có id: "+id+"đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
}

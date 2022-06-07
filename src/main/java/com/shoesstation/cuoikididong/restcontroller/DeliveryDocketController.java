package com.shoesstation.cuoikididong.restcontroller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.DeliveryDocket;
import com.shoesstation.cuoikididong.entity.DeliveryDocketDetail;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.repository.DeliveryDocketRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
		deliveryDocket.setCreatedAt(new Date());
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
	
	
	
//	@DeleteMapping("/{id}")
//	public  ResponseEntity<RestResponse> deletedeliveryDocket(@PathVariable int id) {
//		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
//		if (!deliveryDocketOptional.isPresent()) {
//			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
//		}
//		deliveryDocketRepository.deleteById(id);
//		return ResponseEntity.ok(new RestResponse("Phiếu xuất có id: "+id+" đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
//		
//	}
	@PatchMapping("/{id}")
	public DeliveryDocket updateDeliveryDocketOnField(@RequestBody Map<Object, Object> fields, @PathVariable int id) {
		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
		
		if (!deliveryDocketOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
		}else {
			 fields.forEach((k, v) -> {
		       // use reflection to get field k on manager and set it to value v
		        Field field = ReflectionUtils.findRequiredField(DeliveryDocket.class, (String) k);
		        field.setAccessible(true);
		        ReflectionUtils.setField(field, deliveryDocketOptional.get(), v);
		    });
			
		}
		return deliveryDocketRepository.save(deliveryDocketOptional.get());
		 
	}
	
	@GetMapping("/{id}/deliveryDocketDetails")
	public List<DeliveryDocketDetail> getDeliveryDocketDetailById(@PathVariable int id) {
	
		Optional<DeliveryDocket> deliveryDocketOptional=deliveryDocketRepository.findById(id);
		if (!deliveryDocketOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
		}
		return  deliveryDocketOptional.get().getDeliveryDocketDetails();
	}
}

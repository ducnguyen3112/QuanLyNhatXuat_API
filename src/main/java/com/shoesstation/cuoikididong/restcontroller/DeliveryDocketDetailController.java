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

import com.shoesstation.cuoikididong.entity.DeliveryDocketDetail;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestErrorResponse;
import com.shoesstation.cuoikididong.repository.DeliveryDocketDetailRepository;


@RestController
@RequestMapping("/deliveryDocketDetails")
public class DeliveryDocketDetailController {
	
	@Autowired
	private DeliveryDocketDetailRepository deliveryDocketDetailRepository;
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
			throw new ResourceNotFoundException("Không tìm chi tiết phiếu xuất có mã id: " +id);
		}
		return deliveryDocketDetail;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public DeliveryDocketDetail savedeliveryDocketDetail(@RequestBody DeliveryDocketDetail deliveryDocketDetail) {
		deliveryDocketDetail.setId(0);
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
			throw new ResourceNotFoundException("Không tìm thấy chi tiết phiếu xuất có id: " +id);
		}
		return deliveryDocketDetailRepository.save(deliveryDocketDetail);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestErrorResponse> deletedeliveryDocketDetail(@PathVariable int id) {
		Optional<DeliveryDocketDetail> deliveryDocketDetailOptional=deliveryDocketDetailRepository.findById(id);
		if (!deliveryDocketDetailOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy chi tiết phiếu xuất có id: " +id);
		}
		deliveryDocketDetailRepository.deleteById(id);
		return ResponseEntity.ok(new RestErrorResponse("Chi tiết phiếu xuất có id: "+id+"đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
}

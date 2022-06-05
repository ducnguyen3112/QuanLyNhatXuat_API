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

import com.shoesstation.cuoikididong.entity.Product;
import com.shoesstation.cuoikididong.entity.ReceivedDocketDetail;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestResponse;
import com.shoesstation.cuoikididong.repository.ProductRepository;
import com.shoesstation.cuoikididong.repository.ReceivedDocketDetailRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/receivedDocketDetails")
public class ReceivedDocketDetailController {
	
	@Autowired
	private ReceivedDocketDetailRepository ReceivedDocketDetailRepository;
	@Autowired
	private ProductRepository productRepository;
	@GetMapping
	public List<ReceivedDocketDetail> findAllReceivedDocketDetails(){
		return ReceivedDocketDetailRepository.findAll();
	}
	@GetMapping("/{id}")
	public ReceivedDocketDetail getReceivedDocketDetail(@PathVariable int id){
		Optional<ReceivedDocketDetail> ReceivedDocketDetailOptional=ReceivedDocketDetailRepository.findById(id);
		ReceivedDocketDetail ReceivedDocketDetail=null;
		if (ReceivedDocketDetailOptional.isPresent()) {
			ReceivedDocketDetail=ReceivedDocketDetailOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm chi tiết phiếu xuất có mã id: " +id);
		}
		return ReceivedDocketDetail;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedDocketDetail saveReceivedDocketDetail(@RequestBody ReceivedDocketDetail receivedDocketDetail) {
		receivedDocketDetail.setId(0);
		Optional<Product> productOptional=productRepository.findById(receivedDocketDetail.getProductId());
		
		if (!productOptional.isPresent()) {
			throw new ResourceNotFoundException("Sản phẩm có id: " +receivedDocketDetail.getProductId()+" Không tồn tại");
		}else {
			Product product=productOptional.get();
				product.setInventory(product.getInventory()+receivedDocketDetail.getQuantity());
				productRepository.save(product);
		}
		return ReceivedDocketDetailRepository.save(receivedDocketDetail);
	}
	
	
	@PutMapping("/{id}")
	public ReceivedDocketDetail updateReceivedDocketDetail (@PathVariable int id,@RequestBody ReceivedDocketDetail receivedDocketDetail) {
		Optional<ReceivedDocketDetail> ReceivedDocketDetailOptional=ReceivedDocketDetailRepository.findById(id);
		ReceivedDocketDetail c=null;
		if (ReceivedDocketDetailOptional.isPresent()) {
			c=ReceivedDocketDetailOptional.get();
			receivedDocketDetail.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy chi tiết phiếu xuất có id: " +id);
		}
		return ReceivedDocketDetailRepository.save(receivedDocketDetail);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestResponse> deleteReceivedDocketDetail(@PathVariable int id) {
		Optional<ReceivedDocketDetail> receivedDocketDetailOptional=ReceivedDocketDetailRepository.findById(id);
		if (!receivedDocketDetailOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy chi tiết phiếu xuất có id: " +id);
		}
		ReceivedDocketDetailRepository.deleteById(id);
		return ResponseEntity.ok(new RestResponse("Chi tiết phiếu xuất có id: "+id+" đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
}

package com.shoesstation.cuoikididong.restcontroller;

import java.time.LocalDateTime;
import java.util.Date;
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

import com.shoesstation.cuoikididong.entity.ReceivedDocket;
import com.shoesstation.cuoikididong.entity.ReceivedDocketDetail;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestResponse;
import com.shoesstation.cuoikididong.repository.ReceivedDocketRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/receivedDockets")
public class ReceivedDocketController {
	
	@Autowired
	private ReceivedDocketRepository receivedDocketRepository;
	@GetMapping
	public List<ReceivedDocket> findAllreceivedDockets(){
		return receivedDocketRepository.findAll();
	}
	@GetMapping("/{id}")
	public ReceivedDocket getreceivedDocket(@PathVariable int id){
		Optional<ReceivedDocket> receivedDocketOptional=receivedDocketRepository.findById(id);
		ReceivedDocket receivedDocket=null;
		if (receivedDocketOptional.isPresent()) {
			receivedDocket=receivedDocketOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm thấy phiếu nhập có mã id: " +id);
		}
		return receivedDocket;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedDocket savereceivedDocket(@RequestBody ReceivedDocket receivedDocket) {
		receivedDocket.setId(0);
		receivedDocket.setCreatedAt(new Date());
		return receivedDocketRepository.save(receivedDocket);
	}
	
	
	@PutMapping("/{id}")
	public ReceivedDocket updatereceivedDocket (@PathVariable int id,@RequestBody ReceivedDocket receivedDocket) {
		Optional<ReceivedDocket> receivedDocketOptional=receivedDocketRepository.findById(id);
		ReceivedDocket c=null;
		if (receivedDocketOptional.isPresent()) {
			c=receivedDocketOptional.get();
			receivedDocket.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy phiếu nhập có id: " +id);
		}
		return receivedDocketRepository.save(receivedDocket);
	}
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestResponse> deletereceivedDocket(@PathVariable int id) {
		Optional<ReceivedDocket> receivedDocketOptional=receivedDocketRepository.findById(id);
		if (!receivedDocketOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy phiếu nhập có id: " +id);
		}
		receivedDocketRepository.deleteById(id);
		return ResponseEntity.ok(new RestResponse("Phiếu nhập có id: "+id+" đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
	@GetMapping("/{id}/receivedDocketDetails")
	public List<ReceivedDocketDetail> getReceivedDocketDetailById(@PathVariable int id) {
	
		Optional<ReceivedDocket> deliveryDocketOptional=receivedDocketRepository.findById(id);
		if (!deliveryDocketOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy phiếu xuất có id: " +id);
		}
		return  deliveryDocketOptional.get().getReceivedDocketDetails();
	}
	
}

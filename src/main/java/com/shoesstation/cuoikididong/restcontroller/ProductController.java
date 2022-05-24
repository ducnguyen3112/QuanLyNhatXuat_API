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
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestResponse;
import com.shoesstation.cuoikididong.repository.ProductRepository;


@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@GetMapping
	public List<Product> findAllproducts(){
		return productRepository.findAll();
	}
	@GetMapping("/{id}")
	public Product getproduct(@PathVariable int id){
		Optional<Product> productOptional=productRepository.findById(id);
		Product product=null;
		if (productOptional.isPresent()) {
			product=productOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm thấy sản phẩm có mã id: " +id);
		}
		return product;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product saveproduct(@RequestBody Product product) {
		product.setId(0);
		product.setInventory(0);
		return productRepository.save(product);
	}
	
	
	@PutMapping("/{id}")
	public Product updateproduct (@PathVariable int id,@RequestBody Product product) {
		Optional<Product> productOptional=productRepository.findById(id);
		Product c=null;
		if (productOptional.isPresent()) {
			c=productOptional.get();
			product.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy sản phẩm có id: " +id);
		}
		return productRepository.save(product);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestResponse> deleteproduct(@PathVariable int id) {
		Optional<Product> productOptional=productRepository.findById(id);
		if (!productOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy sản phẩm có id: " +id);
		}
		productRepository.deleteById(id);
		return ResponseEntity.ok(new RestResponse("Sản phẩm có id: "+id+" đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
}

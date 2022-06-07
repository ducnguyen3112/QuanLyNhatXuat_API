package com.shoesstation.cuoikididong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoesstation.cuoikididong.dto.TopProduct;
@Repository
public interface TopProductRepository  extends JpaRepository<TopProduct, Integer>{
	@Query(nativeQuery = true, value = "select products.id, products.name, sum(delivery_docket_detail.quantity*delivery_docket_detail.price) as value from products, delivery_docket_detail\r\n"
			+ "where products.id= delivery_docket_detail.product_id  \r\n"
			+ "group by products.id limit 5")
	List<TopProduct> topFiveProducts();
}

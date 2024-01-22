package com.webapp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.webapp.entity.Product;
import com.webapp.repository.ProductRepository;

@Service
public class RegService {
	
	@Autowired
	private ProductRepository productRepo;
	
	

	public List<Product> readAllReg() {
		List<Product> result = productRepo.findAll();
		return result;
	}

	public void deleteProduct(long id) {
		productRepo.deleteById(id);
		
	}

	public void saveReg(Product product) {
		productRepo.save(product);
		
	}

	public Product getRegById(long id) {
		Product result =productRepo.findById(id).get();
		return result;
	}

	

	

	
	

	

}

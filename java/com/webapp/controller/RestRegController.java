package com.webapp.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.dto.ProductDto;
import com.webapp.entity.Product;
import com.webapp.exception.RecordNotFoundException;
import com.webapp.repository.ProductRepository;

@RestController
@RequestMapping("/api/reg")
public class RestRegController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping
	public List<Product> readreg(){
		List<Product> product = productRepo.findAll();
		return product;
	}
	
	@PostMapping
	public ResponseEntity<?> savereg(@Valid @RequestBody Product product,
			BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
		Product savepro = productRepo.save(product);
		ProductDto dto = new ProductDto();
		dto.setId(savepro.getId());
		dto.setFirstName(savepro.getFirstName());
		dto.setEmail(savepro.getEmail());
		
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletereg(@PathVariable long id){
		Optional<Product> findById = productRepo.findById(id);
		if(findById.isPresent()) {
			productRepo.deleteById(id);
		}else {
			throw new RecordNotFoundException("record not found by id:"+id);
		}
		return new ResponseEntity<>("Record is Deleted", HttpStatus.OK);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deletereg(@PathVariable long id) {
//		Optional<Product> findById = productRepo.findById(id);
//		
//		if(findById.isPresent()) {
//			productRepo.deleteById(id);
//		}else {
//			throw new ResourseNotFoundExceptation("Registration not found!!"+id);
//		}
//		return new ResponseEntity<>("Record is deleted",HttpStatus.OK);
//		
//	}

}

package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.dto.ProductDto;
import com.webapp.entity.Product;
import com.webapp.service.RegService;
import com.webapp.util.Email;

@Controller
public class RegController {
	@Autowired
	private Email emailService;
	
	@Autowired
	private RegService service;
	
	//http:localhost:8080/viewfrontpage
	
	@RequestMapping("/viewfrontpage")
	public String ViewPage() {
		
		return "new-reg";
		
	}
	
//	@RequestMapping("/savereg")
//	public String saveReg(
//			@RequestParam("firstName") String fName,
//			@RequestParam("lastName") String lName,
//			@RequestParam("email") String email,
//			@RequestParam("mobile") long mobile,
//			ModelMap model
//			) {
//		Product product = new Product();
//		product.setFirstName(fName);
//		product.setLastName(lName);
//		product.setEmail(email);
//		product.setMobile(mobile);
//		
//		service.saveProduct(product);
//		model.addAttribute("msg","Record is saved!!");
//		
//		return("new-reg");
//	}
	
	@RequestMapping("/saveReg")
	public String saveReg(
			ProductDto dto,
			ModelMap model
			) {
		Product product = new Product();
		product.setFirstName(dto.getFirstName());
		product.setLastName(dto.getLastName());
		product.setEmail(dto.getEmail());
		product.setMobile(dto.getMobile());
		
		service.saveReg(product);
		emailService.sendEmail(dto.getEmail(),"Welcome","Today no Class");
		model.addAttribute("msg","Record is saved!!");
		return "new-reg";
	}
	
	@RequestMapping("/readAllreg")
	public String readAllReg(Model model) {
		List<Product> res= service.readAllReg();
		model.addAttribute("table",res);
		return "list-reg";
	}
	
	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("uid") long id, Model model) {
		service.deleteProduct(id);
		List<Product> res=service.readAllReg();
		model.addAttribute("table",res);
		return "list-reg";
	}
	
	@RequestMapping("/getRegistrationById")
	public String getRegById(@RequestParam("uid") long id,Model model) {
		Product product = service.getRegById(id);
		model.addAttribute("reg",product);
		return "update-reg";
		}
	
	@RequestMapping("/updateReg")
	public String updateRegistration(ProductDto dto,Model model) {
		
		Product product =new Product();
		product.setId(dto.getId());
		product.setFirstName(dto.getFirstName());
		product.setLastName(dto.getLastName());
		product.setEmail(dto.getEmail());
		product.setMobile(dto.getMobile());
		
		service.saveReg(product);
		List<Product> res=service.readAllReg();
		model.addAttribute("table",res);
		return "list-reg";
		}
	
	
}

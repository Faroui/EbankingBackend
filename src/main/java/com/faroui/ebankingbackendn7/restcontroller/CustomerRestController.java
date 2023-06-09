package com.faroui.ebankingbackendn7.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.faroui.ebankingbackendn7.dto.CustomerDTO;
import com.faroui.ebankingbackendn7.exception.CustomerNotFoundException;
import com.faroui.ebankingbackendn7.service.BankAccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class CustomerRestController {
	
	private BankAccountService bankAccountService;

	public CustomerRestController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@GetMapping("/customers")
	public List<CustomerDTO> customers(){
		return bankAccountService.listCustomers();
	}
	
	@GetMapping("/customers/search")
	public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword){
		return bankAccountService.searchCustomers("%"+keyword+"%");
	}
	
	@GetMapping("/customers/{id}")
	public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
		return bankAccountService.getCustomer(customerId);
	}
	
	@PostMapping("/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO request) {
		return bankAccountService.saveCustomer(request);
	}
	
	@PutMapping("/customers/{customerId}")
	@ResponseBody
	public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(customerId);
		return bankAccountService.updateCustomer(customerDTO);
	}
	
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		bankAccountService.deleteCustomer(id);
	}
}

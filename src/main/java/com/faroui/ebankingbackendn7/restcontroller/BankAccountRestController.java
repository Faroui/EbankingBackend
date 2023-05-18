package com.faroui.ebankingbackendn7.restcontroller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.faroui.ebankingbackendn7.dto.AccountHistoryDTO;
import com.faroui.ebankingbackendn7.dto.AccountOperationDTO;
import com.faroui.ebankingbackendn7.dto.BankAccountDTO;
import com.faroui.ebankingbackendn7.dto.CreditDTO;
import com.faroui.ebankingbackendn7.dto.DebitDTO;
import com.faroui.ebankingbackendn7.dto.TransferRequestDTO;
import com.faroui.ebankingbackendn7.exception.BalanceNotSufficientException;
import com.faroui.ebankingbackendn7.exception.BankAccountNotFoundException;
import com.faroui.ebankingbackendn7.service.BankAccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ebank/account")
@Slf4j
@CrossOrigin(origins = "*")
public class BankAccountRestController {
	
	private BankAccountService bankAccountService;

	public BankAccountRestController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@GetMapping("/get/{accountId}")
	public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
		return bankAccountService.getBankAccount(accountId);
	}
	
	@GetMapping("/list")
	public List<BankAccountDTO> listAccount()  {
		return bankAccountService.listBankAccount();
	}
	
	
	@GetMapping("/operations/{accountId}")
	public List<AccountOperationDTO> getHistorique(@PathVariable String accountId){
		return bankAccountService.historique(accountId);
	}
	
	
	@GetMapping("/{accountId}/pageOperations")
	public AccountHistoryDTO getAccountHistorique(
			@PathVariable String accountId,
			@RequestParam(name ="page", defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException{
		
		return bankAccountService.getAccountHistory(accountId, page, size);
	}
	
	@PostMapping("/debit")
	@ResponseBody
	public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
		return debitDTO;
	}
	
	@PostMapping("/credit")
	@ResponseBody
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
		bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
		return creditDTO;
	}
	
	@PostMapping("/transfer")
	public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountService.transfert(transferRequestDTO.getAccountSource(),
				transferRequestDTO.getAccountDestination(),
				transferRequestDTO.getAmount());
	}
	
	@GetMapping("/{accountId}/pageAccounts")
	public Page<BankAccountDTO> listAccounts( @RequestParam(name ="page", defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size)  {
		List<BankAccountDTO> users = bankAccountService.listBankAccount();
				Pageable paging = PageRequest.of(page, size);
				int start = Math.min((int)paging.getOffset(), users.size());
				int end = Math.min((start + paging.getPageSize()), users.size());

				Page<BankAccountDTO> pages = new PageImpl<>(users.subList(start, end), paging, users.size());
				return pages;
	
	}
	
	

}

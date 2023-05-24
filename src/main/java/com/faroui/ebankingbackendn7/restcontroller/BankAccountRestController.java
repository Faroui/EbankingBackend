package com.faroui.ebankingbackendn7.restcontroller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
@CrossOrigin(origins = "*")
public class BankAccountRestController {
	
	private BankAccountService bankAccountService;

	public BankAccountRestController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@GetMapping("/accounts/{accountId}")
	public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
		return bankAccountService.getBankAccount(accountId);
	}
	
	@GetMapping("/accounts")
	public List<BankAccountDTO> listAccount()  {
		return bankAccountService.listBankAccount();
	}
	
	
	@GetMapping("/accounts/{accountId}/operations")
	public List<AccountOperationDTO> getHistorique(@PathVariable String accountId){
		return bankAccountService.historique(accountId);
	}
	
	
	@GetMapping("/accounts/{accountId}/pageOperations")
	public AccountHistoryDTO getAccountHistorique(
			@PathVariable String accountId,
			@RequestParam(name ="page", defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException{
		
		return bankAccountService.getAccountHistory(accountId, page, size);
	}
	
	@PostMapping("/accounts/debit")
	@ResponseBody
	public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
		return debitDTO;
	}
	
	@PostMapping("/accounts/credit")
	@ResponseBody
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
		bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
		return creditDTO;
	}
	@DeleteMapping("/accounts/{accountId}")
	public void delete(@PathVariable String accountId){
		bankAccountService.deleteAccount(accountId);
	}
	@PostMapping("/accounts/transfer")
	public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountService.transfert(transferRequestDTO.getAccountSource(),
				transferRequestDTO.getAccountDestination(),
				transferRequestDTO.getAmount());
	}
	
	@GetMapping("/accounts/{accountId}/pageAccounts")
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

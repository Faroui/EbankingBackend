package com.faroui.ebankingbackendn7.service;

import java.util.List;

import com.faroui.ebankingbackendn7.dto.CustomerDTO;
import com.faroui.ebankingbackendn7.dto.SavingBankAccountDTO;
import com.faroui.ebankingbackendn7.dto.AccountHistoryDTO;
import com.faroui.ebankingbackendn7.dto.AccountOperationDTO;
import com.faroui.ebankingbackendn7.dto.BankAccountDTO;
import com.faroui.ebankingbackendn7.dto.CurrentBankAccountDTO;
import com.faroui.ebankingbackendn7.exception.BalanceNotSufficientException;
import com.faroui.ebankingbackendn7.exception.BankAccountNotFoundException;
import com.faroui.ebankingbackendn7.exception.CustomerNotFoundException;

public interface BankAccountService {
	
	CustomerDTO saveCustomer(CustomerDTO customerDTO);
	
	CustomerDTO updateCustomer(CustomerDTO customerDTO);
	
	List<CustomerDTO> listCustomers();
	
	void deleteCustomer(Long customerId);
	
	CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
	
	CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
	
	SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
	
	BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
	
	void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
	
	void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
	
	void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
	
	List<BankAccountDTO> listBankAccount();
	
	List<AccountOperationDTO> historique(String accountId);
	
	AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
	
	List<CustomerDTO> searchCustomers(String keyword);
}

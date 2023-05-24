package com.faroui.ebankingbackendn7.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.faroui.ebankingbackendn7.dto.AccountOperationDTO;
import com.faroui.ebankingbackendn7.dto.CurrentBankAccountDTO;
import com.faroui.ebankingbackendn7.dto.CustomerDTO;
import com.faroui.ebankingbackendn7.dto.SavingBankAccountDTO;
import com.faroui.ebankingbackendn7.entities.AccountOperation;
import com.faroui.ebankingbackendn7.entities.CurrentAccount;
import com.faroui.ebankingbackendn7.entities.Customer;
import com.faroui.ebankingbackendn7.entities.SavingAccount;

@Service
public class BankAccountMapperImpl {
	public CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
	public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
		SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
		BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
		savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
		savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
		return savingBankAccountDTO;
		
	}
	public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
		savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
		return null;
		
	}
	public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
		CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
		BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
		currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
		currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
		return currentBankAccountDTO;
	}
	public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
		CurrentAccount currentAccount = new CurrentAccount();
		BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
		currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
		return currentAccount;
	}
	public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
		AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
		BeanUtils.copyProperties(accountOperation, accountOperationDTO);
		return accountOperationDTO;
	}
}

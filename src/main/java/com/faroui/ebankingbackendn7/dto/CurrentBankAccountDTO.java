package com.faroui.ebankingbackendn7.dto;

import java.util.Date;

import com.faroui.ebankingbackendn7.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CurrentBankAccountDTO extends BankAccountDTO {
	private String id;
	private double balance;
	private Date createdAt;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double overDraft;
}

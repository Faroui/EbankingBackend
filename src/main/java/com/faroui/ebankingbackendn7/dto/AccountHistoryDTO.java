package com.faroui.ebankingbackendn7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor @AllArgsConstructor
public class AccountHistoryDTO {
	private String accountId;
	private double balance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<AccountOperationDTO> accountOperationDTOS;
}

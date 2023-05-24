package com.faroui.ebankingbackendn7.dto;

import java.util.Date;

import com.faroui.ebankingbackendn7.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AccountOperationDTO {
	private Long id;
	private Date operationDate;
	private double amount;
	private OperationType type;
	private String description;
}

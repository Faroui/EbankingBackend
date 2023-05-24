package com.faroui.ebankingbackendn7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DebitDTO {
	private String accountId;
	private double amount;
	private String description;
}

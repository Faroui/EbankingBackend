package com.faroui.ebankingbackendn7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faroui.ebankingbackendn7.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}

package com.faroui.ebankingbackendn7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faroui.ebankingbackendn7.entities.SavingAccount;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, String> {

}

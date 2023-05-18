package com.faroui.ebankingbackendn7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faroui.ebankingbackendn7.entities.CurrentAccount;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, String> {

}

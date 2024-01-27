package com.hdfc.atm.iservices;

public interface IAccountService {
	public boolean deposit(Integer amount);
	public boolean withdraw(Integer amount);
	public Integer getBalanceInfo();

}

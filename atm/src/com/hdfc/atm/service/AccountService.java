package com.hdfc.atm.service;

import com.hdfc.atm.iservices.IAccountService;

public class AccountService implements IAccountService{

	private static Integer balance = 10000;	
	@Override
	public boolean deposit(Integer amount) {
		boolean isDeposited = false;
		try
		{
			this.balance += amount;
			isDeposited = true;
		}
		catch(Exception ex)
		{
			System.out.println("Exception raised while depositing the amount : "+ ex.getMessage());
		}
		return isDeposited;
	}
	@Override
	public boolean withdraw(Integer amount) {

		boolean isWithdrawn = false;
		try
		{
			this.balance -= amount;
			isWithdrawn = true;
		}
		catch(Exception ex)
		{
			System.out.println("Exception raised while depositing the amount : "+ ex.getMessage());
		}
		return isWithdrawn;
	}
	@Override
	public Integer getBalanceInfo() {
		
		return this.balance;
	}



}

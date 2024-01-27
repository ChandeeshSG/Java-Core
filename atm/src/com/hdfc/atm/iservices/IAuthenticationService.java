package com.hdfc.atm.iservices;

public interface IAuthenticationService {
	public boolean authenticate(Integer pin);
	public boolean resetPin(Integer newPin);
	
}

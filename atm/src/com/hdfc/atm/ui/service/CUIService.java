package com.hdfc.atm.ui.service;

import java.util.Scanner;

import com.hdfc.atm.iservices.IAccountService;
import com.hdfc.atm.iservices.IAuthenticationService;
import com.hdfc.atm.service.AccountService;
import com.hdfc.atm.service.AuthenticationService;
import com.hdfc.atm.ui.iservice.ICUIService;

public class CUIService implements ICUIService {
	IAuthenticationService authenticationService = new AuthenticationService();
	Scanner scanner = new Scanner(System.in);

	@Override
	public void showCUI() {
		while (true) {
			showMenu();
		}
	}

	private void showMenu() {
		System.out.println("\n1.Deposite\n2.Withdraw\n3.Balance\n4.PinChange\n5.Exit");
		promptUserChoice();
	}

	private void promptUserChoice() {
		System.out.println("Enter Choice : ");
		Integer choice = scanner.nextInt();
		performoperations(choice);
	}

	private void performoperations(Integer choice) {
		IAccountService accountService = new AccountService();
		Integer amount = null;
		switch (choice) {
		case 1:
			amount = readAmount();
			if (ValidateAmount(amount)) {
				if (accountService.deposit(amount)) {
					System.out.println("Amount Deposited...");
				} else {
					System.out.println("Failed to deposite amount...");
				}
			} else {
				System.out.println("Enter a Valid Amount...");
			}
			break;
		case 2:
			if (authorizeUser()) {
				amount = readAmount();
				if (ValidateAmount(amount)) {
					if (accountService.getBalanceInfo() >= amount) 
					{
						if (accountService.withdraw(amount)) {
							System.out.println("Amount dectuded...");
						} else {
							System.out.println("Failed to deduct the amount..");
						}
					}else {
						System.out.println("Insufficient Balance...");
					}
				} else {
					System.out.println("Invalid Amount...");
				}
			}
			break;
		case 3:
			if (authorizeUser()) {
				System.out.println("Balance : " + accountService.getBalanceInfo());
			}
			break;
		case 4:
			if (authorizeUser()) {
				Integer newpin = readPIN();
				if (ValidatePIN(newpin)) {
					if (authenticationService.resetPin(newpin)) {
						System.out.println("PIN Changed Successful...");
					} else {
						System.out.println("PIN Changed Unsuccessful...");
					}
				}
			} else {
				System.out.println("Invalid Pin Entered");
			}
			break;
		case 5:
			System.exit(0);
		default:
			System.out.println("Invalid Choice");
			break;
		}
	}

	private Integer readAmount() {
		System.out.println("Enter amount : ");
		return scanner.nextInt();
	}

	private boolean ValidateAmount(Integer amount) {
		return ((amount > 0) && (amount % 100 == 0)) ? true : false;
	}

	private Integer readPIN() {
		System.out.println("Enter PIN : ");
		return scanner.nextInt();
	}

	private boolean ValidatePIN(Integer pin) {
		return ((pin > 999) && (pin <= 9999)) ? true : false;
	}

	private boolean authorizeUser() {

		boolean isAuthorized = false;
		Integer count = 1;
		Integer pin = null;
		while (count <= 3) {
			pin = readPIN();
			if (ValidatePIN(pin)) {
				if (authenticationService.authenticate(pin)) {
					isAuthorized = true;
					break;
				} else {
					count++;
					if (count > 3) {
						System.out.println("Unauthorized User, Terminating the program");
						System.exit(0);
					}
				}

			} else {
				System.out.println("Invalid Pin...");
			}

		}

		return isAuthorized;
	}
}

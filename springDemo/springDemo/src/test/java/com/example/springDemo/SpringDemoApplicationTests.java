package com.example.springDemo;

import com.example.entities.Account;
import com.example.exception.AccountAlreadyExistException;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;
import com.example.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SpringDemoApplicationTests {

	@Autowired
	private Service service;

	Account account;

	@BeforeEach
	public void setUp(){
		account =new Account();
		account.setAccountNumber(12345678912345L);
		account.setaccountHolderName("Payal");
		account.setBranch("Pune");
		account.setMobileNumber(9876543212L);
		account.setAccountBalance(3500000L);

	}

	@Test
	@Order(1)
	void testAddAccount(){
		//Account account= new Account(12345678909875L,"Payal","Pune",9876543212L,250000L);
		Account actual= service.addAccount(account);
		Account expected=service.getAccountByAccountNumber(account.getAccountNumber());

		assertEquals(expected,actual);
		//assertEquals(expected.getAccountBalance(),actual.getAccountBalance());
	}

	@Test
	@Order(2)
	void testAccountNumberAlreadyPresentForAdd(){
	account.setAccountNumber(98865432198765L);
	service.addAccount(account);

	assertThrows(AccountAlreadyExistException.class,()->service.addAccount(account),
			"Account already Exist");
	}


	@Test
	void testInvalidDataForAddAccount(){

		account.setAccountNumber(567898765432L);  //less than 14 digit
		assertThrows(InvalidEntryException.class,()->service.addAccount(account),"Account Number must be 14 digt long");

		account.setAccountNumber(98765412345678L);
		account.setaccountHolderName("Raj");
		assertThrows(InvalidEntryException.class,()->service.addAccount(account),
				"Account Holder name length should be more than 4 letters ");

		account.setaccountHolderName("Payal");
		account.setMobileNumber(98765432L);
		assertThrows(InvalidEntryException.class,()-> service.addAccount(account),
				"Mobile number must be 10 digit long");

	}

	void testInvalidDataForUpdateAccount(){

	}

	@Test
	void  testGetAccountByAccountNumber() throws DataNotFoundException {
		account.setAccountNumber(96993108071234l);
		service.addAccount(account);
		Account expected = service.getAccountByAccountNumber(account.getAccountNumber());
		Account actual = service.getAccountByAccountNumber(account.getAccountNumber());
		assertEquals(expected, actual);
		assertThrows(DataNotFoundException.class, () -> service.getAccountByAccountNumber(96900108071234l), "Account not present");
	}



}

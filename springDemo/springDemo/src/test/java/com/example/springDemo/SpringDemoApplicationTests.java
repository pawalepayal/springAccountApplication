package com.example.springDemo;

import com.example.controller.MyController;
import com.example.entities.Account;
import com.example.exception.AccountAlreadyExistException;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;
import com.example.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
class SpringDemoApplicationTests {

    @Autowired
    private Service service;
    Account account;
    @Autowired
    MockMvc mockmvc;

    @Autowired
    MyController controller;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountNumber(12345678912345L);
        account.setaccountHolderName("Payal");
        account.setBranch("Pune");
        account.setMobileNumber(9876543212L);
        account.setAccountBalance(3500000L);

    }

    @Test
    @Order(1)
    void addAccountTest() {
        Account actual = service.addAccount(account);
        Account expected = service.getAccountByAccountNumber(account.getAccountNumber());

        assertEquals(expected, actual);

    }

    @Test
    @Order(4)
    void accountNumberAlreadyPresentForAddTest() {
        account.setAccountNumber(98865432198765L);
        service.addAccount(account);

        assertThrows(AccountAlreadyExistException.class, () -> service.addAccount(account),
                "Account already Exist");
    }

    @Order(3)
    @Test
    void invalidDataForAddAccountTest() {

        account.setAccountNumber(567898765432L);  //less than 14 digit
        assertThrows(InvalidEntryException.class, () -> service.addAccount(account), "Account Number must be 14 digit long");

        account.setAccountNumber(98765412345678L);
        account.setaccountHolderName("Raj");
        assertThrows(InvalidEntryException.class, () -> service.addAccount(account),
                "Account Holder name length should be more than 4 letters ");

        account.setaccountHolderName("Payal");
        account.setMobileNumber(98765432L);
        assertThrows(InvalidEntryException.class, () -> service.addAccount(account),
                "Mobile number must be 10 digit long");

    }

    @Order(2)

    @Test
    void updateAccountTest() {

        account.setAccountNumber(88995678901234L);
        service.addAccount(account);
        account.setaccountHolderName("Ramesh");
        Account actual = service.updateAccount(account.getAccountNumber(), account);
        Account expected = service.getAccountByAccountNumber(account.getAccountNumber());
        assertEquals(expected, actual);

    }

    @Order(5)
    @Test
    void getAccountByAccountNumberTest() {
        account.setAccountNumber(96993108071234L);
        service.addAccount(account);
        Account expected = service.getAccountByAccountNumber(account.getAccountNumber());
        Account actual = service.getAccountByAccountNumber(account.getAccountNumber());
        assertEquals(expected, actual);
        assertThrows(DataNotFoundException.class, () -> service.getAccountByAccountNumber(96900108071234L), "Account not present");
    }

    @Order(6)
    @Test
    void deleteaccountByAccountNumberTest() {

        assertThrows(DataNotFoundException.class, () -> service.deleteAccount(123456789123475L), "Invalid entry");

    }


    @Test
    void controllerAddAccountTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        account.setAccountNumber(98765432123456L);
        this.mockmvc.perform(post("/account/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isCreated());
    }

    @Test
    void controllerUpdateAccountTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        account.setaccountHolderName("Rajesh");
        account.setAccountBalance(4500000);
        account.setBranch("mumbai");
        account.setMobileNumber(1234567899);
        this.mockmvc.perform(put("/account/update/12345678912345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect((status().isOk()));
    }

    @Test
    void controllerdeleteAccountTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockmvc.perform(delete("/account/delete/12345678912345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect((status().isAccepted()));
    }

    @Test
    void controllergetAllAccountTest() throws Exception {
        account.setAccountNumber(12345678901234L);
        service.addAccount(account);
        this.mockmvc.perform(get("/account/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));

    }

    @Test
    void controllergetAccountByAccountNumberTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockmvc.perform(get("/account/get/12345678912345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect((status().isFound()));


    }

    public static Object[] addAccounts(){
        return new Object[] []{{12345688882445L,"P","Pune",9876543213L,120000L},
                { 123456789897L,"Pawale","Mumbai",9876549913L,890000L} };
    }

    @ParameterizedTest
    @MethodSource("addAccounts")
    public void addMethodByMethodSourceTest(long number, String name, String branch, long mobileNum, long accountBal){
      // System.out.println(+accountNumber+" "+accountHolderName+" "+branch+" "+mobileNumber+" "+accountBalance);
        account.setAccountNumber(number);
        account.setaccountHolderName(name);
        account.setBranch(branch);
        account.setBranch(branch);
        account.setMobileNumber(mobileNum);
        account.setAccountBalance(accountBal);
        assertThrows(InvalidEntryException.class,()->service.addAccount(account),"Invalid");
    }

//    public static Stream<act> updateAccountMethod(){
//        return Stream.of(new Account(12343434234567L,"Manoj","Pune",1232312345L,6700000L));
//    }
    public static Object[] Accounts1(){
        return new Object[]{
                12345688882445L,"Manik", "Punjab", 9876543213L, 120000L};
        }


    @ParameterizedTest
    @MethodSource("Accounts1")
    public void updateMethodByMethodSourceTest(long number, String name, String branch, long mobileNum, long accountBal){
        account.setAccountNumber(12345688882445L);
        service.addAccount(account);
        account.setaccountHolderName("Suresh");
        Account actual = service.updateAccount(number, account);
        Account expected = service.getAccountByAccountNumber(account.getAccountNumber());
        assertEquals(expected, actual);

       // assertThrows(InvalidEntryException.class,()->service.updateAccount(accountNumber,account),"invalid");
    }


    public List<Account> getAccount(){
        return service.getAllAccounts();
    }
    @ParameterizedTest
    @MethodSource("getAccount")
    public void getAccountMethodByMethodSource(List<Account> actual ){
        account.setAccountNumber(12765456762343L);
        account.setaccountHolderName("Rajveer");
        account.setBranch("Mumbai");
        account.setMobileNumber(9876554548L);
        account.setAccountBalance(980000L);
        service.addAccount(account);

       // List<Account> actual=service.getAllAccounts();
        Account expected=service.getAccountByAccountNumber(12765456762343L);

       // List<Account> expected=service.getAccount();
        assertEquals(expected,actual);

    }
}

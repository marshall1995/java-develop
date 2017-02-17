package pl.lodz.uni.math.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.bank.BankAccount;
import pl.lodz.uni.math.client.Client;
import pl.lodz.uni.math.exceptions.BankAccountHaveOtherOwnerException;
import pl.lodz.uni.math.exceptions.BankAccountNotExistException;
import static org.junit.Assert.*;

public class TransactionTest {
	
	private static Client clientA;
	private static Client clientB;
	private static Transaction transactionForTests;
	private static List<Transaction> transactionListForTests = new ArrayList<Transaction>();
	
	@BeforeClass
    public static void setClientsForTests() {
		
        clientA = new Client(1,"ClientA");
        clientA.addAccount("123456789012345", "description");
        clientA.getBankAccount("123456789012345").setAmount(new BigDecimal(2000));
        
        clientB = new Client(2,"ClientB");
        clientB.addAccount("123456789012346", "description");
        
        ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientA.getBankAccount("123456789012345"), 1000, 
				"description", TransactionType.DEPOSIT, clientA.getBankAccount("123456789012345").getCheckNumber());
		transactionForTests = new Deposit(valuesForTransaction);
		
		transactionListForTests.add(transactionForTests);
    }
	
	@Test
	public void testTransactionHistoryForClient(){
		assertEquals(transactionListForTests, transactionForTests.getClientTransactionHistory(clientA));
	}
	
	@Test(expected=BankAccountHaveOtherOwnerException.class)
	public void testTransactionWrongAccountOwner(){
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientB.getBankAccount("123456789012346"), 1000, 
				"description", TransactionType.DEPOSIT, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Deposit(valuesForTransaction);
		
	}
	
	@Test(expected=BankAccountNotExistException.class)
	public void testDepositReturnAccountAmount(){
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				new BankAccount(), 1000, 
				"description", TransactionType.DEPOSIT, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Deposit(valuesForTransaction);
	}
}

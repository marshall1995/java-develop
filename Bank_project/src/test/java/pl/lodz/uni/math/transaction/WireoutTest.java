package pl.lodz.uni.math.transaction;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.bank.BankAccount;
import pl.lodz.uni.math.client.Client;
import pl.lodz.uni.math.exceptions.BankAccountNotEnoughMoneyException;
import pl.lodz.uni.math.exceptions.BankAccountNotExistException;


public class WireoutTest {

	private static Client clientA;
	private static Client clientB;
	
	@BeforeClass
    public static void setClientsForTests() {
		
        clientA = new Client(1,"ClientA");
        clientA.addAccount("123456789012345", "description");
        clientA.getBankAccount("123456789012345").setAmount(new BigDecimal(2000));

        clientB = new Client(2, "ClientB");
        clientB.addAccount("123456789012346", "description");
        clientB.getBankAccount("123456789012346").setAmount(new BigDecimal(1000));
    }
	
	@Test
	public void testWireoutDoTransaction(){
		clientA.getBankAccount("123456789012345").setAmount(new BigDecimal(2000));
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientA.getBankAccount("123456789012345"), 1000, 
				"description", TransactionType.CHECK, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Wireout(valuesForTransaction,clientB.getBankAccount("123456789012346"),"12345","12345");
		
	}
	
	@Test
	public void testWireoutReturnAccountAmount(){
		assertEquals(clientA.getBankAccount("123456789012345").getAmount(),new BigDecimal(1000));
		assertEquals(clientB.getBankAccount("123456789012346").getAmount(),new BigDecimal(2000));
	}
	
	@Test(expected=BankAccountNotExistException.class)
	public void testWireoutWrongGetterAccount(){
		BankAccount bankAccountWithNoClient = new BankAccount();
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientA.getBankAccount("123456789012345"), 1000, 
				"description", TransactionType.CHECK, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Wireout(valuesForTransaction,bankAccountWithNoClient,"12345","12345");
	}
	
	@Test(expected=BankAccountNotEnoughMoneyException.class)
	public void testWireoutNotEnoughMoneyOnSenderAccount(){
		clientA.getBankAccount("123456789012345").setAmount(new BigDecimal(0));
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientA.getBankAccount("123456789012345"), 1000, 
				"description", TransactionType.CHECK, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Wireout(valuesForTransaction,clientB.getBankAccount("123456789012346"),"12345","12345");
	}

}
package pl.lodz.uni.math.transaction;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.client.Client;

public class DepositTest {
	
	private static Client clientA;
	
	@BeforeClass
    public static void setClientsForTests() {
		
        clientA = new Client(1,"ClientA");
        clientA.addAccount("123456789012345", "description");
        clientA.getBankAccount("123456789012345").setAmount(new BigDecimal(2000));
    }
	
	@Test
	public void testDepositDoTransaction(){
		ValuesForTransaction valuesForTransaction = new ValuesForTransaction(clientA, 
				clientA.getBankAccount("123456789012345"), 1000, 
				"description", TransactionType.DEPOSIT, clientA.getBankAccount("123456789012345").getCheckNumber());
		new Deposit(valuesForTransaction);
		
	}
	
	@Test
	public void testDepositReturnAccountAmount(){
		assertEquals(clientA.getBankAccount("123456789012345").getAmount(),new BigDecimal(3000));
		
	}
}

package pl.lodz.uni.math.client;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Test.None;

import pl.lodz.uni.math.bank.BankAccount;
import pl.lodz.uni.math.exceptions.WrongClientNameException;;

public class ClientTest {
	
	private static Client clientA;
	private static Client clientAMock;
	private static Client clientWithError;
	
	@BeforeClass
    public static void setClientsForTests() {
		clientA = new Client(1, "ClientA");
		clientA.addAccount("123456789012345", "stuff");
		
        clientAMock = EasyMock.createMock(Client.class);
        EasyMock.expect(clientAMock.getId()).andReturn(1).anyTimes();
        EasyMock.expect(clientAMock.getName()).andReturn("ClientA").anyTimes();
        EasyMock.expect(clientAMock.getBankAccount("123456789012345")).andReturn(
        		clientA.getBankAccount("123456789012345")).anyTimes();
        
        EasyMock.replay(clientAMock);
    }
	
	@Test
	public void testClientConstructorGetId(){
		assertEquals(clientA.getId(),clientAMock.getId());
	}
	
	@Test
	public void testClientConstructorGetName(){
		assertEquals(clientA.getName(), clientAMock.getName());
	}
	
	@Test(expected=WrongClientNameException.class)
	public void testClientConstructorWrongNameLengthToSmall(){
		clientWithError = new Client(2,"ON");
	}
	
	@Test(expected=WrongClientNameException.class)
	public void testClientConstructorWrongNameLengthToLong(){
		clientWithError = new Client(2,"qwertyuiopasdfghjklzxcvbnm");
	}
	
	@Test(expected=WrongClientNameException.class)
	public void testClientConstructorWrongNameNotOnlyLetters(){
		clientWithError = new Client(2,"ClientA2");
	}
	
	@Test
	public void testClientAddingBankAccount(){
		assertEquals(clientA.getBankAccount("123456789012345").getNumber(),clientAMock.getBankAccount("123456789012345").getNumber());
		assertEquals(clientA.getBankAccount("123456789012345").getDescription(),clientAMock.getBankAccount("123456789012345").getDescription());
	}
	
	@Test
	public void testClientGetAccount(){
		assertEquals(clientA.getBankAccount("123456789012345").getClient(), clientA);
	}
	
	@Test
	public void testClientGetAccountWrongAccountNumber(){
		assertEquals(clientA.getBankAccount("123456789012346").getClient(), null);
	}
}
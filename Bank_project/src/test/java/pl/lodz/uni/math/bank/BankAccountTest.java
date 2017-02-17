package pl.lodz.uni.math.bank;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.client.Client;
import pl.lodz.uni.math.exceptions.EmptyBankAccountDescriptionException;
import pl.lodz.uni.math.exceptions.WrongBankAccountNumberException;

public class BankAccountTest {
	private static Client clientAMock;
	private static BankAccount bankAccountA;
	private static BankAccount bankAccountAMock;
	private static BankAccount bankAccountWithError;
	
	@BeforeClass
    public static void setBackAccountsForTests() {
		
        clientAMock = EasyMock.createMock(Client.class);
        EasyMock.expect(clientAMock.getId()).andReturn(1).anyTimes();
        EasyMock.expect(clientAMock.getName()).andReturn("ClientA").anyTimes();
        
        EasyMock.replay(clientAMock);
        
        bankAccountA = new BankAccount("123456789012345","description",clientAMock);
        
        bankAccountAMock = EasyMock.createMock(BankAccount.class);
        EasyMock.expect(bankAccountAMock.getNumber()).andReturn("123456789012345").anyTimes();
        EasyMock.expect(bankAccountAMock.getDescription()).andReturn("description").anyTimes();
        EasyMock.expect(bankAccountAMock.getClient()).andReturn(clientAMock).anyTimes();
        
        EasyMock.replay(bankAccountAMock);
    }
	
	@Test
	public void testBankAccountConstructor(){
		assertEquals(bankAccountAMock.getNumber(), bankAccountA.getNumber());
		assertEquals(bankAccountAMock.getClient(), clientAMock);
	}
	
	@Test
	public void testBankAccountWithNoClient(){
		BankAccount bankAccountWithNoClient = new BankAccount();
		assertEquals(null, bankAccountWithNoClient.getClient());
	}
	
	@Test(expected=WrongBankAccountNumberException.class)
	public void testBankAccountWithWrongNumberLength(){
		bankAccountWithError = new BankAccount("12345678901234567890","description",clientAMock);
	}
	
	@Test(expected=WrongBankAccountNumberException.class)
	public void testBankAccountWithOtherSignsNotOnlyNumbers(){
		bankAccountWithError = new BankAccount("123456789012E45","description",clientAMock);
	}
	
	@Test(expected=EmptyBankAccountDescriptionException.class)
	public void testBankAccountWithNoDescription(){
		bankAccountWithError = new BankAccount("123456789012345","",clientAMock);
	}
}

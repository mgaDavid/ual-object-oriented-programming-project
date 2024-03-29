package test;

import com.poo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static Account account, account2;
    private static Client client, client2;
    private static Operation operation1, operation2, operation3;
    private static BigDecimal tax = new BigDecimal(0.42).setScale(2, RoundingMode.HALF_UP);

    @BeforeEach
    public void setUp() throws ParseException {
        IdDocument document = new IdDocument("123", "PASSAPORTE");
        PhoneContact phoneContact = new PhoneContact(123456789, "TELEMÓVEL");

        Date birthday = new SimpleDateFormat("yyyy/MM/dd").parse("2002/11/07");
        client = new Client("DIEGO SOARES", document, birthday, "UAL", "diego@test.com", phoneContact);

        account = new Account(1, client, 100, true);
        client.addAccount(account);

        IdDocument document2 = new IdDocument("1234", "BI");
        PhoneContact phoneContact2 = new PhoneContact(123456788, "TELEMÓVEL");
        Date birthday2 = new SimpleDateFormat("yyyy/MM/dd").parse("2000/11/07");
        client2 = new Client("DAVID ARCO", document2, birthday2, "UAL 2", "david@test.com", phoneContact2);

        account2 = new Account(2, client2, 0, false);
        client2.addAccount(account2);

        operation1 = new Operation("CRÉDITO", 100, tax);
        operation2 = new Operation("CRÉDITO", 300, tax);
        operation3 = new Operation("DÉBITO", 50, tax);
        account.registerOperation(operation1);
        account.registerOperation(operation2);
        account.registerOperation(operation3);
    }

    @Test
    void getNumber() {
        assertEquals(account.getNumber(),1);
        assertNotEquals(account2.getNumber(), 3);
    }

    @Test
    void getDependents() {
        account.addDependent(client2);
        assertEquals(1, account.getDependents().size());
        assertEquals(client2, account.getDependents().get(0));
    }

    @Test
    void getBalance() {
        assertEquals(new BigDecimal(448.74).setScale(4, RoundingMode.HALF_UP), account.getBalance());
        assertNotEquals(new BigDecimal(0).setScale(4, RoundingMode.HALF_UP), account.getBalance());
    }

    @Test
    void getOverdraft() {
        assertTrue(account.getOverdraft());
        assertFalse(account2.getOverdraft());
    }

    @Test
    void getOperations() {
        assertTrue(account.getOperations().contains(operation1));
        assertTrue(account.getOperations().contains(operation2));
        assertTrue(account.getOperations().contains(operation3));
    }

    @Test
    void setOverdraft() {
        assertTrue(account.getOverdraft());
        account.setOverdraft(false);
        assertFalse(account.getOverdraft());
    }

    @Test
    void registerOperation() {
        Operation operation4 = new Operation("CRÉDITO", 999, tax);
        account.registerOperation(operation4);
        assertEquals(operation4, account.getOperations().get(4));
    }

    @Test
    void addDependent() {
        account.addDependent(client2);
        assertEquals(client2, account.getDependents().get(0));
    }

    @Test
    void removeDependent() {
        account.removeDependent(client2);
        assertEquals(0, account.getDependents().size());
    }

    @Test
    void getClient() {
        assertEquals(client, account.getClient());
    }
}
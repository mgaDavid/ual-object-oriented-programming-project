package test;

import com.poo.Account;
import com.poo.Client;
import com.poo.IdDocument;
import com.poo.PhoneContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static IdDocument document, document2;
    private static PhoneContact phoneContact2;
    private static Client client, client2;
    private static Account account, account2;

    @BeforeAll
    public static void setUp() throws ParseException {
        document = new IdDocument("123", "PASSAPORTE");
        PhoneContact phoneContact = new PhoneContact(123456789, "TELEMÓVEL");

        Date birthday = new SimpleDateFormat("yyyy/MM/dd").parse("2002/11/07");
        client = new Client("DIEGO SOARES", document, birthday, "UAL", "diego@test.com", phoneContact);

        account = new Account(1, client, 100, true);
        client.addAccount(account);

        document2 = new IdDocument("1234", "BI");
        phoneContact2 = new PhoneContact(123456788, "TELEMÓVEL");
        Date birthday2 = new SimpleDateFormat("yyyy/MM/dd").parse("2000/11/07");
        client2 = new Client("DAVID ARCO", document2, birthday2, "UAL 2", "david@test.com", phoneContact2);

        account2 = new Account(2, client2, 0, false);
        client2.addAccount(account2);
    }

    @Test
    void getDocument() {
        assertEquals(document.getNumber(), client.getDocument().getNumber());
        assertEquals(document.getType(), client.getDocument().getType());
        assertEquals("123", client.getDocument().getNumber());
        assertEquals("PASSAPORTE", client.getDocument().getType());
    }

    @Test
    void getAccounts() {
        ArrayList<Account> clientAccounts = new ArrayList<>();
        clientAccounts.add(account);

        assertEquals(clientAccounts.size(), client.getAccounts().size());
        assertEquals(clientAccounts.get(0), client.getAccounts().get(0));
    }

    @Test
    void setName() {
        assertEquals("DIEGO SOARES", client.getName());
        client.setName("DIEGO O SOARES");
        assertEquals("DIEGO O SOARES", client.getName());
    }

    @Test
    void setAddress() {
        assertEquals("UAL", client.getAddress());
        client.setAddress("UAL B");
        assertEquals("UAL B", client.getAddress());
    }

    @Test
    void setEmail() {
        assertEquals("diego@test.com", client.getEmail());
        client.setEmail("diego2@test.com");
        assertEquals("diego2@test.com", client.getEmail());
    }

    @Test
    void setContact() {
        assertEquals("TELEMÓVEL", client.getContact().getType());
        assertEquals(123456789, client.getContact().getNumber());
        client.setContact(new PhoneContact(987654321, "CASA"));
        assertEquals("CASA", client.getContact().getType());
        assertEquals(987654321, client.getContact().getNumber());
    }

    @Test
    void addAccount() {
        Account newAccount = new Account(3, client, 0, false);
        client.addAccount(newAccount);
        assertEquals(newAccount, client.getAccounts().get(1));
    }

    @Test
    void getAccount() {
        assertEquals(account, client.getAccount(1));
    }

    @Test
    void isMyAccount() {
        assertTrue(client.isMyAccount(1));
        assertFalse(client.isMyAccount(2));
        assertTrue(client2.isMyAccount(2));
    }

    @Test
    void testEquals() {
        assertTrue(client.equals(document));
        assertFalse(client.equals(document2));
    }

    @Test
    void getBirthday() {
        assertEquals(client.getBirthday(), "2002/11/07");
        assertEquals(client2.getBirthday(), "2000/11/07");
    }

    @Test
    void getAddress() {
        assertEquals(client.getAddress(), "UAL");
        assertEquals(client2.getAddress(), "UAL 2");
    }

    @Test
    void getName() {
        assertEquals("DIEGO SOARES", client.getName());
    }

    @Test
    void getEmail() {
        assertEquals("diego2@test.com", client.getEmail());
    }

    @Test
    void getContact() {
        assertEquals(phoneContact2, client2.getContact());
    }

    @Test
    void removeAccount() {
        assertEquals(1, client2.getAccounts().size());
        client2.removeAccount(account2);
        assertEquals(0, client2.getAccounts().size());
    }
}
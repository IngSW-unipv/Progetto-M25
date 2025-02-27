package it.unipv.ingsfw.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import it.unipv.ingsfw.model.users.Cliente;
import it.unipv.ingsfw.model.users.ClienteDAO;

public class ClienteTest {

    private Cliente clienteCompleto;
    private Cliente clienteCompletoFalse;
    private Cliente clienteEmailPassword;
    private Cliente clienteId;
    private Cliente clienteNullo;
    private ClienteDAO clienteDAO;

    @Before
    public void setUp() {
        clienteCompleto = new Cliente("CL000", "Stefano", "Rossi", "RSSSTF23Y45R304I", "Admin@f2aService.com", "Admin");
        clienteCompletoFalse = new Cliente("CL000", "Stefano", "Rossi", "RSSSTF23Y45R304I", "Admin@f2aService.com", "Adin");
        clienteEmailPassword = new Cliente("giovanni.neri@email.com", "pwd111");
        clienteId = new Cliente("CL002");
        clienteNullo = new Cliente();
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testCostruttoreCompleto() {
        assertEquals("CL000", clienteCompleto.getIdCliente());
        assertEquals("Stefano", clienteCompleto.getNome());
        assertEquals("Rossi", clienteCompleto.getCognome());
        assertEquals("RSSSTF23Y45R304I", clienteCompleto.getCf());
        assertEquals("Admin@f2aService.com", clienteCompleto.getEmail());
        assertEquals("Admin", clienteCompleto.getPassword());
    }

    @Test
    public void testCostruttoreEmailPassword() {
        assertEquals("giovanni.neri@email.com", clienteEmailPassword.getEmail());
        assertEquals("pwd111", clienteEmailPassword.getPassword());
    }

    @Test
    public void testCostruttoreId() {
        assertEquals("CL002", clienteId.getIdCliente());
        assertNull(clienteId.getNome());
        assertNull(clienteId.getCognome());
        assertNull(clienteId.getCf());
        assertNull(clienteId.getEmail());
        assertNull(clienteId.getPassword());
    }

    @Test
    public void testCostruttoreNullo() {
        assertNull(clienteNullo.getIdCliente());
        assertNull(clienteNullo.getEmail());
        assertNull(clienteNullo.getPassword());
    }

    @Test
    public void testSetGetIdCliente() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente("CL003");
        assertEquals("CL003", cliente.getIdCliente());
    }

    @Test
    public void testSetGetNome() {
        Cliente cliente = new Cliente();
        cliente.setNome("Anna");
        assertEquals("Anna", cliente.getNome());
    }

    @Test
    public void testSetGetCognome() {
        Cliente cliente = new Cliente();
        cliente.setCognome("Verdi");
        assertEquals("Verdi", cliente.getCognome());
    }

    @Test
    public void testSetGetCf() {
        Cliente cliente = new Cliente();
        cliente.setCf("VRDANNA90B02M123Y");
        assertEquals("VRDANNA90B02M123Y", cliente.getCf());
    }

    @Test
    public void testSetGetEmail() {
        Cliente cliente = new Cliente();
        cliente.setEmail("anna.verdi@example.com");
        assertEquals("anna.verdi@example.com", cliente.getEmail());
    }

    @Test
    public void testSetGetPassword() {
        Cliente cliente = new Cliente();
        cliente.setPassword("securePass");
        assertEquals("securePass", cliente.getPassword());
    }

    @Test
    public void testToString() {
        String expected = "Cliente CL000\nNome: Stefano\nCognome: Rossi\nCf: RSSSTF23Y45R304I\nEmail: Admin@f2aService.com\nPassword Admin";
        assertEquals(expected, clienteCompleto.toString());
    }

    @Test
    public void testSelectAllClienti() {
        assertNotNull(clienteDAO.selectAll());
    }

    @Test
    public void testInsertCliente() {
    	
    	//per come è strutturato il codice IDCL non deve essere presente su DB
    	//eseguito il test ricordarsi di eliminare la tupla 
   	
        Cliente nuovoCliente = new Cliente("CL026", "Test", "Cliente", "TSTCLT00A00X000Z", "test.cliente@example.com", "testpassword");
        assertTrue(clienteDAO.insertCliente(nuovoCliente));
    }

    @Test
    public void testSelectIdByEmailPasswordValido() {
        assertNotNull(clienteDAO.selectIdByEmailPassword(clienteCompleto));
    }

    @Test
    public void testSelectClienteByEmailEPasswordValido() {
        assertNotNull(clienteDAO.selectClienteByEmailEPassword(clienteCompleto));
    }

    @Test
    public void testSelectClienteByEmailEPasswordNonValido() {
        assertNull(clienteDAO.selectClienteByEmailEPassword(new Cliente("emailNonEsistente", "passwordNonEsistente")));
    }

    @Test
    public void testGetNewIdCliente() {
        assertNotNull(clienteDAO.getNewIdCliente());
    }
}
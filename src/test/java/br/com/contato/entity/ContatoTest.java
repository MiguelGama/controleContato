package br.com.contato.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoTest {

    @Test
    public void testContato() {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setEmail("test@example.com");
        contato.setNome("Test");
        contato.setTelefone("1234567890");
        contato.setCep("65340-000");
        contato.setEndereco("Rua Teste");
        contato.setCidade("Cidade Teste");
        contato.setUf("UF");
        Date date = new Date();
        contato.setDataCadastro(date);

        assertEquals(1L, contato.getId());
        assertEquals("test@example.com", contato.getEmail());
        assertEquals("Test", contato.getNome());
        assertEquals("1234567890", contato.getTelefone());
        assertEquals("65340-000", contato.getCep());
        assertEquals("Rua Teste", contato.getEndereco());
        assertEquals("Cidade Teste", contato.getCidade());
        assertEquals("UF", contato.getUf());
        assertEquals(date, contato.getDataCadastro());
    }
}
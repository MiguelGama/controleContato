package br.com.contato.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class EnderecoDTOTest {

    @Test
    public void testEnderecoDTO() {
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setCep("070450-000");
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("São Miguel");
        endereco.setUf("MA");
        endereco.setBairro("");
        endereco.setStatus(200);
        endereco.setOk(true);
        endereco.setStatusText("ok");

        assertEquals("070450-000", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("São Miguel", endereco.getCidade());
        assertEquals("MA", endereco.getUf());
        assertEquals("", endereco.getBairro());
        assertEquals(200, endereco.getStatus());
        assertEquals(true, endereco.isOk());
        assertEquals("ok", endereco.getStatusText());
    }
}
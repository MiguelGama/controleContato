package br.com.contato.service;

import br.com.contato.dto.EnderecoDTO;
import br.com.contato.entity.Contato;
import br.com.contato.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private CepService cepService;

    @InjectMocks
    private ContatoService contatoService;

    @Test
    public void testGetAllContatos() {
        Contato contato1 = new Contato();
        Contato contato2 = new Contato();
        List<Contato> expectedContatos = Arrays.asList(contato1, contato2);

        when(contatoRepository.findAll()).thenReturn(expectedContatos);

        List<Contato> actualContatos = contatoService.getAllContatos();

        assertEquals(expectedContatos, actualContatos);
    }

    @Test
    public void testGetContatoById() {
        Contato expectedContato = new Contato();
        expectedContato.setId(1L);

        when(contatoRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedContato));

        Contato actualContato = contatoService.getContatoById(1L);

        assertEquals(expectedContato, actualContato);
    }

    @Test
    public void testCreateContato() {
        Contato contato = new Contato();
        contato.setCep("65340-000");

        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setCep("65340-000");
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setUf("UF");

        when(cepService.buscaEnderecoPorCep("65340-000")).thenReturn(endereco);
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato createdContato = contatoService.createContato(contato);

        assertEquals(contato, createdContato);
    }
}
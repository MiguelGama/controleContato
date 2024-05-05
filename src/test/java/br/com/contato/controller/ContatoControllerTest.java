package br.com.contato.controller;

import br.com.contato.entity.Contato;
import br.com.contato.service.ContatoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ContatoControllerTest {

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private ContatoController contatoController;

    @Test
    public void testGetAllContatos() throws Exception {
        Contato contato1 = new Contato();
        Contato contato2 = new Contato();
        List<Contato> contatos = Arrays.asList(contato1, contato2);

        when(contatoService.getAllContatos()).thenReturn(contatos);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
        mockMvc.perform(get("/api/contato")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void testGetContatoById() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);

        when(contatoService.getContatoById(1L)).thenReturn(contato);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
        mockMvc.perform(get("/api/contato/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    public void testeGetContatoByIdNotFound() throws Exception {
        when(contatoService.getContatoById(1L)).thenReturn(null);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
        mockMvc.perform(get("/api/contato/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testeUpdateContato() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);

        when(contatoService.updateContato(contato)).thenReturn(contato);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
        mockMvc.perform(get("/api/contato/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

}
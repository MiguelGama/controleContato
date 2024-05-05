package br.com.contato.service;

import br.com.contato.dto.EnderecoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CepServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CepService cepService;

    @Test
    public void testBuscaEnderecoPorCep_validarCep() {
        String cep = "65340-000";
        EnderecoDTO expectedEndereco = new EnderecoDTO();
        expectedEndereco.setCep(cep);

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        when(restTemplate.exchange(
                eq(String.format(CepService.API_URL, cep)),
                eq(HttpMethod.GET),
                eq(entity),
                eq(EnderecoDTO.class)))
                .thenReturn(ResponseEntity.ok(expectedEndereco));

        EnderecoDTO actualEndereco = cepService.buscaEnderecoPorCep(cep);

        assertThat(actualEndereco)
                .usingRecursiveComparison()
                .ignoringFields("bairro", "status", "ok", "statusText")  // ignore the fields that you are not interested in
                .isEqualTo(expectedEndereco);
    }

    @Test
    public void testBuscaEnderecoPorCep_Cep_invalido() {
        String cep = "invalid";

        when(restTemplate.exchange(
                eq(String.format(CepService.API_URL, cep)),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(EnderecoDTO.class)))
                .thenThrow(HttpClientErrorException.class);

        assertThrows(HttpClientErrorException.class, () -> cepService.buscaEnderecoPorCep(cep));
    }

    @Test
    public void testBuscaEnderecoPorCep_Cep_vazio() {
        String cep = "";

        assertThrows(IllegalArgumentException.class, () -> cepService.buscaEnderecoPorCep(cep));
    }
}
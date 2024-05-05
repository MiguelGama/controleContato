package br.com.contato.service;

import br.com.contato.dto.EnderecoDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    public static final String API_URL = "https://cdn.apicep.com/file/apicep/%s.json";

    private final RestTemplate restTemplate;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public EnderecoDTO buscaEnderecoPorCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP nulo ou vazio");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<EnderecoDTO> responseEntity = restTemplate.exchange(
                String.format(API_URL, cep),
                HttpMethod.GET,
                entity,
                EnderecoDTO.class);
        return responseEntity.getBody();

    }
}
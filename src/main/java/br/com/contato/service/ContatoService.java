package br.com.contato.service;

import br.com.contato.dto.EnderecoDTO;
import br.com.contato.entity.Contato;
import br.com.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private CepService cepService;

    public List<Contato> getAllContatos() {
        return contatoRepository.findAll();
    }

    public Contato getContatoById(Long id) {
        return contatoRepository.findById(id).orElse(null);
    }

    public Contato createContato(Contato contato) {
        EnderecoDTO endereco = getEnderecoByCep(contato.getCep());
        contato.setEndereco(endereco.getLogradouro());
        contato.setCidade(endereco.getCidade());
        contato.setUf(endereco.getUf());
        return contatoRepository.save(contato);
    }

    private EnderecoDTO getEnderecoByCep(String cep) {
        return cepService.buscaEnderecoPorCep(cep);
    }

    public Contato updateContato(Contato contato) {
        if (contato != null && contato.getId() > 0) {
            EnderecoDTO endereco = getEnderecoByCep(contato.getCep());
            contato.setEndereco(endereco.getLogradouro());
            contato.setCidade(endereco.getCidade());
            contato.setUf(endereco.getUf());
            return contatoRepository.save(contato);
        }
        return null;
    }

    public void deleteContato(Long id) {
        contatoRepository.deleteById(id);
    }
}
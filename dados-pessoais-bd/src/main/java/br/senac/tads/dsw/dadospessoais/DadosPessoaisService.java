package br.senac.tads.dsw.dadospessoais;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisRepository repository;

    @Autowired
    private ConhecimentoRepository conhecimentoRepository;
  
    private DadosPessoaisDto buildDto(DadosPessoais entity) {
        List<Integer> conhecimentosIds = new ArrayList<>();
        for (Conhecimento c : entity.getConhecimentos()) {
            conhecimentosIds.add(c.getId());
        }
        List<FotoPessoaDto> fotos = new ArrayList<>();
        if (entity.getFotos() != null) {
            for (FotoPessoa f : entity.getFotos()) {
                fotos.add(new FotoPessoaDto(f.getId(), f.getNomeArquivo(), f.getLegenda()));
            }
        }
        DadosPessoaisDto dto = new DadosPessoaisDto(entity.getApelido(), 
        entity.getNome(), entity.getEmail(), 
        entity.getTelefone(), entity.getDataNascimento(), 
        conhecimentosIds, fotos);
        return dto;
    }

    private DadosPessoais buildEntity(DadosPessoaisDto dto) {
        DadosPessoais entity = new DadosPessoais();
        entity.setApelido(dto.apelido());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());
        entity.setDataNascimento(dto.dataNascimento());

        Set<Conhecimento> conhecimentos = new LinkedHashSet<>();
        for (Integer conhecimentoId : dto.conhecimentosIds()) {
            Optional<Conhecimento> optConhecimento = 
            conhecimentoRepository.findById(conhecimentoId);
            if (optConhecimento.isPresent()) {
                conhecimentos.add(optConhecimento.get());
            }
        }
        entity.setConhecimentos(conhecimentos);
        // for (FotoPessoaDto foto : dados.fotos()) {
        //     // Criar FotoPessoa para cada foto
        // }
        // entity.setFotos(null);
        return entity;
    }

    @Transactional(readOnly = true)
    public List<DadosPessoaisDto> findAll(int pagina, int quantidade, String textoBusca) {
        List<DadosPessoais> entities;
        if (textoBusca != null && textoBusca.length() > 0) {
            entities = repository.findByNomeContainingIgnoreCase(textoBusca);
        } else {
            entities = repository.findAll();
        }
        List<DadosPessoaisDto> resultado = new ArrayList<>();
        for (DadosPessoais entity: entities) {
            DadosPessoaisDto dto = buildDto(entity);
            resultado.add(dto);
        }
        return resultado;
    }

    @Transactional(readOnly = true)
    public Page<DadosPessoaisDto> findAllPaged(int pagina,int quantidade, String textoBusca) {
        Page<DadosPessoais> page = repository.findAll(PageRequest.of(pagina, quantidade));
        List<DadosPessoaisDto> resultado = new ArrayList<>();
        for (DadosPessoais entity: page.getContent()) { 
            DadosPessoaisDto dto = buildDto(entity);
            resultado.add(dto);
        }
        return new PageImpl<DadosPessoaisDto>(resultado, PageRequest.of(pagina, quantidade), repository.count());
    }
    

    @Transactional(readOnly = true)
    public DadosPessoaisDto findByApelido(String apelido) {
        Optional<DadosPessoais> optEntity = repository.findByApelidoIgnoreCase(apelido);
        if (optEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário " + apelido + " não encontrado");
        }
        DadosPessoais entity = optEntity.get();
        return buildDto(entity);
    }


    @Transactional
    public void addNew(DadosPessoaisDto dados) {
        DadosPessoais entity = buildEntity(dados);
        repository.save(entity);
    }

    @Transactional
    public void update(String apelido, DadosPessoaisDto dados) {
        Optional<DadosPessoais> optAtual = repository.findByApelido(apelido);
        if (optAtual.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário " + apelido + "  não encontrado");
        }
        DadosPessoais atual = optAtual.get();
        DadosPessoais entity = buildEntity(dados);
        entity.setId(atual.getId());
        entity.setApelido(apelido);
        repository.save(entity);
    }


    @Transactional
    public void delete(String apelido) {
        repository.deleteByApelido(apelido);
    }
}

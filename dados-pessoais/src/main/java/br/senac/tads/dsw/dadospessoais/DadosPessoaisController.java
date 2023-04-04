package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {

    private Map<String, DadosPessoais> listaDados = new LinkedHashMap<>();

    public DadosPessoaisController() {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Fulano da Silva");
        dados.setEmail("fulano@teste.com.br");
        dados.setApelido("fulano");
        dados.setTelefone("(11) 99999-1122");
        dados.setDataNascimento(LocalDate.parse("2000-10-20")); // LocalDate.of(20, 10, 2000)
        List<Conhecimento> conhecimentos = new ArrayList<>();
        conhecimentos.add(new Conhecimento(1, "Java"));
        conhecimentos.add(new Conhecimento(2, "HTML"));
        conhecimentos.add(new Conhecimento(3, "CSS"));
        conhecimentos.add(new Conhecimento(4, "Javascript"));
        dados.setConhecimentos(conhecimentos);

        // dados.setConhecimentos(Arrays.asList(new Conhecimento(1, "Java"),
        // new Conhecimento(2, "HTML"),
        // new Conhecimento(3, "CSS"),
        // new Conhecimento(4, "Javascript")
        // );
        listaDados.put(dados.getApelido(), dados);
    }

    @GetMapping
    public List<DadosPessoais> obterTodosDados() {
        return new ArrayList<>(listaDados.values());
    }

    @GetMapping("/{apelido}")
    public DadosPessoais obterDadosPorApelido(@PathVariable String apelido) {
        return listaDados.get(apelido);
    }

    // @GetMapping("/enviar")
    @PostMapping("/enviar")
    public DadosPessoais enviarDadosBasico(
        @RequestParam("nome") String nome,
        @RequestParam("apelido") String apelido,
        @RequestParam("email") String email,
        @RequestParam("telefone") String telefone,
        @RequestParam("dataNascimento") String dataNascimentoStr
    ) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setApelido(apelido);
        dados.setEmail(email);
        dados.setTelefone(telefone);
        dados.setDataNascimento(LocalDate.parse(dataNascimentoStr));

        listaDados.put(apelido, dados);
        return dados;
    }

    @PostMapping
    public DadosPessoais criarDadosPessoais(@RequestBody DadosPessoais dadosEnviados) {
        listaDados.put(dadosEnviados.getApelido(), dadosEnviados);
        return dadosEnviados;
    }

    @PutMapping("/{apelido}")
    public DadosPessoais atualizarDadosPessoais(
        @PathVariable String apelido, 
        @RequestBody DadosPessoais dadosEnviados) {

        return null;
    }

    @DeleteMapping("/{apelido}")
    public void apagarDadosPessoais(@PathVariable String apelido) {

    }


    
}

package br.senac.tads.dsw.exemplos;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExemploController {

    @GetMapping("/tradicional")
    public String exemploTradicionalThymeleaf() {
        return "tela-tradicional";
    }

    @GetMapping("/tradicional-dinamico")
    public String exemploTradicionalDinamico(Model model) {
        model.addAttribute("nome", "Fulano de Souza");
        model.addAttribute("dataHora", LocalDateTime.now());
        model.addAttribute("numero", 1234);
        return "tela-dinamico";
    }

    @GetMapping(value = "/exemplo01")
    @ResponseBody
    public String ex01() {
        return "Texto do exemplo 01";
    }

    @GetMapping(value = "/exemplo02", produces = "application/json")
    @ResponseBody
    public String ex02() {
        return """
                {
                    "nome": "Fulano da Silva",
                    "telefone": "(11) 99999-1234",
                    "email": "fulano@teste.com.br"
                }
                """;
    }

    @GetMapping("/exemplo03")
    @ResponseBody
    public DadosPessoais ex03() {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Ciclano de Souza");
        dados.setEmail("ciclano@teste.com.br");
        dados.setTelefone("(11) 99999-2223");
        return dados;
    }
    
}

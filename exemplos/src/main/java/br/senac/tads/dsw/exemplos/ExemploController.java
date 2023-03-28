package br.senac.tads.dsw.exemplos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExemploController {

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

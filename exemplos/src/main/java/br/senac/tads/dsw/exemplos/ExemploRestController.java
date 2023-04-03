package br.senac.tads.dsw.exemplos;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ExemploRestController {

    @GetMapping(value = "/exemplo01")
    public String ex01() {
        return "Texto do exemplo 01";
    }

    @GetMapping(value = "/exemplo02", produces = "application/json")
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
    public DadosPessoais ex03() {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Ciclano de Souza do RestController");
        dados.setEmail("ciclano@teste.com.br");
        dados.setTelefone("(11) 99999-2223");
        return dados;
    }

    @GetMapping("/exemplo04")
    public DadosPessoais ex04(@RequestParam("nome") String nome) {
        String email = nome.split(" ")[0].toLowerCase();
        email = email.concat("@teste.com.br");

        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setEmail(email);
        dados.setTelefone("(11) 99999-3344");
        return dados;
    }

    @GetMapping("/exemplo04b")
    public DadosPessoais ex04b(
        @RequestParam("nome") String nome,
        @RequestParam("telefone") String telefone) {
        String email = nome.split(" ")[0].toLowerCase();
        email = email.concat("@teste.com.br");

        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setEmail(email);
        dados.setTelefone(telefone);
        return dados;
    }

    @GetMapping("/exemplo05/{apelido}")
    public DadosPessoais exemplo05(@PathVariable String apelido) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(apelido);
        dados.setEmail(apelido + "@teste.com.br");
        dados.setTelefone("(11) 98765-1122");
        return dados;
    }

    @GetMapping("/exemplo05b/{apelido}/{numero}")
    public DadosPessoais exemplo05(
        @PathVariable String apelido,
        @PathVariable Integer numero) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(apelido);
        dados.setEmail(apelido + "@teste.com.br");
        dados.setTelefone("(11) 98765-1122");
        dados.setNumero(numero);
        return dados;
    }

    @GetMapping("/header-user-agent")
    public String exemploHeader(@RequestHeader("user-agent") String userAgent) {
        boolean isMobile = userAgent.toLowerCase().contains("mobile");
        if (isMobile) {
            return "Acesso via dispositivo móvel";
        } else {
            return "Acesso normal";
        }
        //return userAgent;
    }

    @GetMapping("/todos-cabecalhos")
    public Map<String, String> exemploTodosCabecalhos(
        @RequestHeader Map<String, String> cabecalhos) {
        return cabecalhos;
    }
    
}

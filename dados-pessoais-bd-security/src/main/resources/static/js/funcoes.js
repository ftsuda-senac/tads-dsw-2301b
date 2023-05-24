function buscarDadosJson(urlJson, params, jwt) {
    // Versão com função anônima
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let urlAjustada = urlJson;
        if (params) {
            urlAjustada = urlAjustada + '?' + params
        }
        xhr.open('GET', urlJson, true);
        if (jwt != null) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + jwt);
        }
        xhr.setRequestHeader('Content-type', 'application/json');

        xhr.onload = function () {
            if (xhr.status === 200) {
                const dados = JSON.parse(xhr.responseText);
                resolve(dados);
            } else {
                reject({
                    status: xhr.status,
                    mensagem: 'Erro na requisição'
                });
            }
        };
        xhr.send();
    });
}

function enviarDadosJson(urlJson, dadosParaEnviar, contentType = 'application/json', accept = 'application/json') {

    // Versão com função anônima
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', urlJson, true);

        xhr.setRequestHeader('Content-type', contentType);
        xhr.setRequestHeader('Accept', accept);

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                if (accept === 'application/json') {
                    const dadosArr = JSON.parse(xhr.responseText);
                    resolve(dadosArr);
                }
                resolve(xhr.responseText)
            } else {
                if (xhr.status === 400) {
                    // Assumir que houve erro de validacao
                    const camposErros = []
                    console.log(xhr.responseText)

                    const dadosErro = JSON.parse(xhr.responseText)
                    for (const err of dadosErro.errors) {
                        camposErros.push({
                            campo: err.field,
                            mensagem: err.defaultMessage
                        });
                    }
                    reject({
                        status: xhr.status,
                        mensagem: 'Erro na validação dos dados',
                        erros: camposErros
                    });
                } else {
                    reject({
                        status: xhr.status,
                        mensagem: 'Erro na requisição'
                    });
                }
            }
        };
        let dados;
        if (contentType === 'application/json') {
            dados = JSON.stringify(dadosParaEnviar)
        } else if (contentType === 'application/x-www-form-urlencoded') {
            dados = dadosParaEnviar.toString()
        }
        xhr.send(dados);
    });
}
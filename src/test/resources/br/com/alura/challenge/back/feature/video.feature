#language:pt

@save
@delete

Funcionalidade: Todas as funcionalidades dos endpoints de Video


    Esquema do Cenário: Listar todos os videos

    Quando realizar uma consulta de todos os videos e informo o <limit> e a <page>
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | limit | page | codigo_operacao |
    | '10'  | '0'  | '200'           |

    
    
    Esquema do Cenário: Listar por categoria

    Dado que estou no endpoint para listar por categoria <categoriaId>
    Quando realizar uma consulta para listar videos por categoria
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | categoriaId | codigo_operacao |
    |    '1'      | '200'           |    


    Esquema do Cenário: Procurar por id
    
    Dado que estou no endpoint para procurar por id <videoId>
    Quando realizar uma consulta por id
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | videoId | codigo_operacao |
    | '1'     | '200'           |
    | '-1'    | '404'           |


    Esquema do Cenário: criar video
    
    Dado que estou no endpoint para criar o video <title> , <description> , <categoryId> , <url>
    Quando realizar um registro
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | title                                                                       | description | categoryId | url          | codigo_operacao |
    | 'TESTE'                                                                     |    'TESTE'  |  '1'       | 'http://'    | '201'           |
    | 'TESTE'                                                                     |    'TESTE'  |   '0'      |  'http://'   |  '404'          |
    | 'curso de Testes de Integração: Testes de SQL e DAOs automatizados em Java' |    'TESTE'  |  '1'       | 'http://'    | '400'           |



    Esquema do Cenário: update video
    
    Dado que estou no endpoint para atualizar o video <videoId>,<description> , <categoryId> , <url>
    Quando realizar uma atualizacao
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | videoId  | description | categoryId | url          | codigo_operacao |
    |  '1'     |    'TESTE'  |  '1'       | 'http://'    | '200'           |
    |  '-1'    |    'TESTE'  |   '1'      |  'http://'   |  '404'          |


    Esquema do Cenário: deletar por id
    
    Dado que estou no endpoint para deletar por id <videoId>
    Quando realizar uma delacao por id
    Entao a API deve me retornar o código da operação <codigo_operacao> e os dados apresentados no corpo da solicitação

    Exemplos:
    | videoId | codigo_operacao |
    | '1'     | '204'           |
    | '-1'    | '404'           |






    


    
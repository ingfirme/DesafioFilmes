# Desafio Itaú - Ingrid Cordeiro Firme

## Objetivo

Criar uma aplicação Back-end que tenha como finalidade buscar um filme em uma API externa, receber e armazenar comentários e notas sobre o filme buscado e de acordo com o tipo do perfil do usuário executar outras funcionalidades. Cada ação realizada concede um ponto para o usuário e conforme o acúmulo de pontos são disponibilizadas novas funcionalidades.

<u>Tipos de Perfil</u>

1 - LEITOR: Após o cadastro, esse usuário poderá logar e buscar por um filme. Ele poderá ver as informações, comentários e dar  nota para um filme. A cada avaliação, ele ganhará 1 ponto em seu perfil.

2 - BÁSICO: O usuário LEITOR poderá se tornar BÁSICO quando adquirir 20 pontos. Nesse perfil será possível postar comentários, notas e responder comentários ganhando 1 ponto a cada ação realizada. 

3 - AVANÇADO: O usuário BÁSICO poderá se tornar AVANÇADO quando adquirir 100 pontos. Esse perfil tem as capacidades do BÁSICO, reagir a comentários com “gostei” ou "não gostei” e citar comentários feitos por outros usuários.

4 - MODERADOR: Um usuário poderá se tornar MODERADOR de 2 formas: um moderador torna outro usuário moderador ou quando atingir 1000 pontos.  O moderador além das capacidades do perfil AVANÇADO poderá alterar o perfil de outro usuário para moderador, excluir comentários e marcar comentários repetidos.



## Construído com

* Java 17
* Maven
* Spring Boot 
* Spring Boot Cloud
* Spring Boot Security
* Flyway
* MySQL
* Redis




## Requisitos

* Java 
* Docker
* Postman



##  Instalação

**1 - Executar a seguinte linha de comando no terminal para iniciar banco de dados MySQL através do Docker**

```
docker-compose up
```

**1.1 - Executar a seguinte linha de comando no terminal para iniciar banco de dados Redis através do Docker**

```
docker run --name redis -p 6379:6379 redis:5.0.3
```

**2 - Executar o arquivo ".jar" que está no diretorio "target" para subir a aplicação com o seguinte código no terminal:**

```
java -jar .\ProjetoIngrid-0.0.1-SNAPSHOT.jar
```

**3 - Importar a coleção e variáveis de ambiente contidas na pasta "collectionPostman" para o Postman**

**4 - Para as APIs 3,4,5,6,7 deve ser informado um token de authenticação gerado pela API 2 - Realizar login, caso não seja informado o usuario não terá acesso aos recursos.**

## Execução das APIs com Postman

**1 - Criar usuário**

Executar no Postman a API para criar usuários passando os seguintes parâmetros:

| POST | http://localhost:8080/api/users |
| ---- | ------------------------------- |

```json
{
	"nome":"Nome Teste",
	"sobrenome":"Sobrenome Teste",
	"email":"teste@teste.com",
	"idade":"31",
	"senha":"senhausuario"
}
```

Resposta: todos os usuários cadastrados iniciam com o perfil 1 - LEITOR

```json
{
    "nickname": 2,
    "nome": "Nome Teste",
    "sobrenome": "SObrenome Teste",
    "email": "teste@teste.com",
    "idade": "31",
    "senha": "senhausuario",
    "qtde_nota": 0,
    "qtde_comentario": 0,
    "qtde_resposta": 0,
    "qtde_total": 0,
    "id_perfil": 1
}
```

Obs: Caso o usuário erre a senha 4 vezes será exibida a mensagem "limite de tentativas de login excedidas" 


**2 - Realizar login**

Executar no Postman a API para fazer login passando os seguintes parâmetros:

| POST | http://localhost:8080/api/login |
| ---- | ------------------------------- |

```json
{
    "usuario":"teste@teste.com",
    "senha":"senhausuario"
}
```

Resposta:

* Usuário e senha digitados corretamente: login feito com sucesso + token de validação de segurança gerado;

* Usuário correto e senha incorreta: senha inválida;

* Usuário correto e senha incorreta apos 3 tentativas: limite de tentativas excedido;

* Usuário incorreto: usuário não cadastrado.

  


**3 - Buscar um filme na API externa**

Executar no Postman a API para consultar um filme passando os seguintes parâmetros:

| GET  | http://localhost:8080/api/buscarFilme?titulo= |
| ---- | --------------------------------------------- |

![image-20220629203543644](https://user-images.githubusercontent.com/106877504/176971786-91b84915-e54d-4c83-baa1-451d1e87bc2e.png)


Resposta:

```json
{
    "year": "1997",
    "director": "James Cameron",
    "writer": "James Cameron",
    "title": "Titanic",
    "plot": "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",
    "genre": "Drama, Romance",
    "actors": "Leonardo DiCaprio, Kate Winslet, Billy Zane"
}
```



**4 - Buscar comentários sobre um filme**

Executar no Postman a API para consultar os comentários de um filme. O parâmetro é automaticamente o título do filme que foi buscado na API (3)

| GET  | http://localhost:8080/api/comentarios?titulo={{titulo}} |
| ---- | ------------------------------------------------------- |

![image-20220629214046769](https://user-images.githubusercontent.com/106877504/176971885-c39701cd-4330-439a-878d-9c39c708fd5d.png)


Resposta:

```json
[
    {
        "id_comentario": 1,
        "titulo": "Titanic",
        "nickname": 2,
        "comentario": "Filme muito legal"
    }
]
```



**5 - Avaliar com uma nota (1 a 5) o filme buscado**

Executar no Postman a API para criticar um filme passando os seguintes parâmetros: O parâmetro titulo é automaticamente o preenchido com o filme que foi buscado na API (3)

| POST | http://localhost:8080/api/nota |
| ---- | ------------------------------ |

```json
{
	"titulo":"{{titulo}}",
	"nota":"5"
}
```

Resposta:

```json
{
    "id_nota": 13,
    "titulo": "Titanic",
    "nickname": 2,
    "nota": 5
}
```



**6 - Fazer um comentário sobre o filme buscado**

Executar no Postman a API para cadastrar um comentário passando os seguintes parâmetros: O parâmetro titulo é automaticamente o preenchido com o filme que foi buscado na API (3)

Obs: Caso o usuário não possua o perfil acima de LEITOR, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/comentario |
| ---- | ------------------------------------ |

```json
{
	"titulo":"{{titulo}}",
	"comentario":"Filme muito legal"
}
```

Resposta:

```json
{
    "id_comentario": 1,
    "titulo": "Titanic",
    "nickname": 2,
    "comentario": "Filme muito legal"
}
```

**7 - Responder um comentário de outro usuário**

Executar no Postman a API para responder o comentário passando os seguintes parâmetros: 

Obs: Caso o usuário não possua o perfil acima de LEITOR, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/resposta_comentario?id_comentario=1 |
| ---- | ------------------------------------------------------------ |

![image-20220629215032410](https://user-images.githubusercontent.com/106877504/176971920-b9e25038-ca67-487c-b3a1-8444b53be883.png)

```json
{
	"comentario":"Concordo, o filme é sensacional"
}
```

Resposta:

```json
{
    "id_resposta_comentario": 2,
    "nickname": 2,
    "id_comentario": 1,
    "comentario": "Concordo, o filme é sensacional"
}
```



**8 - Reagir a um comentário (gostei - não gostei)**

Executar no Postman a API para reagir a um comentário passando os seguintes parâmetros:

Obs: Caso o usuário não possua o perfil acima de BÁSICO, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/reacoes |
| ---- | --------------------------------- |

```json
{
	"id_comentario":"1",
    "reacao": true
}
```

Resposta:

```json
{
    "id_reacao": 1,
    "id_comentario": "1",
    "nickname": 2,
    "reacao": true
}
```



**9 - Citar um comentário**

Executar no Postman a API para citar um comentário passando os seguintes parâmetros:

Obs: Caso o usuário não possua o perfil acima de BÁSICO, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/citarComentario |
| ---- | ----------------------------------------- |

```json
{
	"id_comentario":"1",
    "titulo":"{{titulo}}",
    "comentario":"Olha, realmente aconteceu oq voce disse"
}
```

Resposta:

```json
Citacao realizada
```



**10 - Alterar perfil de usuário para MODERADOR**

Executar no Postman a API para alterar o perfil de outro usuário passando os seguintes parâmetros:

Obs: Caso o usuário não possua o perfil MODERADOR, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/atualizaUsuario?email=ingfirme@gmail.com |
| ---- | ------------------------------------------------------------ |

![image-20220629221326682](https://user-images.githubusercontent.com/106877504/176972110-3e0893d8-11ac-4adf-93bf-657692113f1c.png)

Resposta: 1 (True) = ação executada com sucesso.



**11 - Excluir comentário**

Executar no Postman a API para excluir comentário passando os seguintes parâmetros:

Obs1: Caso o usuário não possua o perfil MODERADOR, ele não poderá executar essa ação;

Obs2:Essa ação exclui também respostas e reações vinculadas ao comentário excluído. 

 

| DELETE | http://localhost:8080/api/deletaComentario?id_comentario=1 |
| ------ | ---------------------------------------------------------- |

![image-20220630232636639](https://user-images.githubusercontent.com/106877504/176972136-f0995299-5407-42e1-a9d4-d61fd7121c15.png)

Resposta: 

```json
Comentário deletado.
```



**12 - Marcar um comentário como repetido**

Executar no Postman a API para marcar um comentário como repetido passando os seguintes parâmetros:

Obs: Caso o usuário não possua o perfil MODERADOR, ele não poderá executar essa ação. 

| POST | http://localhost:8080/api/marcarComentarioRepetido |
| ---- | -------------------------------------------------- |

```json
{
	"id_comentario":"1"
}
```

Resposta:

```json
Comentario marcado como duplicado
```



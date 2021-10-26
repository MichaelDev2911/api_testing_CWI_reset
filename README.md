# api_testing_CWI_reset
* Link base do projeto
  * http://treinamento-api.herokuapp.com/apidoc/index.html
 
      ![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/76186505/138476210-4c609893-1abd-4b80-8f57-47d6b7d9e4c0.gif)
 
 Como **Steve Jobs** disse uma vez: 
> Decidir o que não fazer é tão 
> importante quanto decidir 
> o que fazer 
      
 _**Resultado AllTests**_
 >
 _**Passed**_  :+1:
- [x] _**22**_
>
 _**Refused**_  :eyes:
- [ ] _**4**_
 >
 _**Testes Recusados**_ :eyes:
* DeleteBookingTest
   * @DisplayName("Deletar uma reserva que não existe")
 > Status code esperado 404 Not Found, retornou 405 Method Not Allowed
* GetBookingTest
   *  @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
 > Status code esperado 500 Internal Server Error, retornou 200 Success
   *  @DisplayName("Lista os Ids Buscando pela data de checkout and checkout")
 > Status code esperado 400 Bad Request, retornou 200 Success
* PostBokkingTest
   * @DisplayName("validar a criação de uma reserva enviando mais parâmetros no payload da reserva")
 > Status code esperado 400 Bad Request, retornou 200 Success

      
_**Anotações Gerais Projeto**_
1. Tipo primitivo int tem pouco recurso para grandes aplicações podendo estourar seu valor máximo que é 2.147.483.647
2. Retorno somente dos ids no Get https://treinamento-api.herokuapp.com/booking
3. Em https://treinamento-api.herokuapp.com/booking/1, a variável 1 está como string 
4. Status code 200 ao fazer um Post na aplicação
5. Status code 200 ao fazer um Put na aplicação
6. Status code 201 ao fazer um Delete na aplicação
7. Status code 201 ao fazer um Get em https://treinamento-api.herokuapp.com/ping
8. Status (405 Method Not Allowed) ao tentar excluir reserva que não existe
9. Resultado não esperado ao filtrar por parâmetros checkin,checkout,fisrtname,lastname()
10. Status code 200 não esperado ao tentar filtrar busca com parâmetro mal formatado()
11. Resultado não esparado ao criar uma reserva com parâmetros adicionais()
12. pedido para validar mais de um **livro** em sequência
13. Retorno do Get https://treinamento-api.herokuapp.com/booking muda e não busca de forma crescente ou decrescente os Ids.
14. Filtro checkout and checkout retornando status code 200

_**A seguir retorno esperado e possíveis BUGs dos itens listados acima**_
1. O mais comum é utilizar o tipo Long como identificador pois seu valor máximo atende a 9.223.372.036.854.775.807 identificações
2. Não está errado mais acredito que retornar uma lista de usuário por paginação contendo seus atributos principais seria mais interressante para aplicação
3. se o projeto está todo com o tipo primitivo int acredito que este campo deveria estar da mesma forma que as demais
4. Dentro dos padrões RestFull o esperado ao criar um recurso é 201 Created.
5. Não esta errado,mais outro possivel resultado seria um 204, pois o Put atualiza para um novo documento e o que foi alterado não exista mais
6. Status code 204 ou 200 seriam os mais apropriados para operação pois o 200 daria sucesso de exclusão e o 204 avisa que não contém mais o item
7. Não está errado, mais status code 200 de sucesso seria o ideal.
8. Status code 404 seria o ideal, já o status 405 da a entender que a busca foi feita de uma forma errada,mais no caso só o recurso que não existe
9. Alguns filtros da aplicação estão com possível BUG.checkin busca datas além da requisitada apenas,mais as que são iguais não e checkout da mesma forma
10. BUG ao filtrar a aplicção pois ela aceita busca com parâmetros incompletos exemplo https://treinamento-api.herokuapp.com/booking?fname=Jim ,neste caso fname não existe
11. Erro grave ao adicionar nova reserva com parâtros adicionais podendo ser usado por pessoas de má fé para hackear informações,instalar recursos inesperados e roubar dados secretos
12. Documentação pediu para adicionar livros em sequência,porém a API se trata de reservas e não de livros
13. Alinhar com o desenvolvimento para que a busca seja feita de forma crescente para que fique mais fácil a navegação, também deixando mais flexível a automatização dos testes,pois de momento em momento muda a ordem de apresentação dos Ids e acaba que a implementação que busca o id na posição zero do array muda e assim os resultados esperados também
14. Ao quere filtrar por checkout and checkout deveria dar status 400 bad request.



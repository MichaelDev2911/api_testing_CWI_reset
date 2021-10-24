# api_testing_CWI_reset
* Link base do projeto
  * http://treinamento-api.herokuapp.com/apidoc/index.html
  * Alguns frameworks e configurações   :)
      ![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/76186505/138476210-4c609893-1abd-4b80-8f57-47d6b7d9e4c0.gif)
      
_**Anotações Gerais Projeto**_
1. Tipo primitivo int tem pouco recurso para grandes aplicações podendo estourar seu valor máximo que é 2.147.483.647
2. Retorno somente dos ids no Get https://treinamento-api.herokuapp.com/booking
3. Em https://treinamento-api.herokuapp.com/booking/1, a variável 1 está como string 
4. Status code 200 ao fazer um Post na aplicação
5. Status code 200 ao fazer um Put na aplicação
6. Status code 201 ao fazer um Delete na aplicação
7. Status code 201 ao fazer um Get em https://treinamento-api.herokuapp.com/ping
8. Erro (405 Method Not Allowed) ao não tentar excluir reserva que não existe
9. Resultado não esperado ao filtrar por parâmetros checkin,checkout,fisrtname,lastname()
10. Status code 200 não esperado ao tentar filtrar busca com parâmetro mal formatado()
11. Resultado não esparado ao criar uma reserva com parâmetros adiciomnais()
12. pedido para validar mais de um **livro** em sequência

_**Retorno Esperado e possíveis BUG das Anotações Gerais do Projeto**_
1.O mais comum é utilizar o tipo Long como identificador pois seu valor máximo atende a 9.223.372.036.854.775.807 identificações
2.Não está errado mais acredito que retornar uma lista de usuário por paginação contendo seus atributos principais seria mais interressante para aplicação
3.se o projeto está todo com o tipo primitivo int acredito que este campo deveria estar da mesma forma que as demais
4.


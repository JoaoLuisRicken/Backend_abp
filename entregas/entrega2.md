# ABP de Backend: Software de Gestão de Cinema

## Membros:
- Jhoni Cauan Freitas Elias
- João Gustavo
- João Luis Machado Ricken
##  Documentação da API de Gerenciamento de Cinema
###  Visão Geral

Esta API gerencia informações relacionadas a cinemas, incluindo filmes, salas, assentos, sessões, ingressos e usuários.
O sistema é baseado em uma arquitetura RESTful e utiliza o Spring Boot.
 
## Entidades
### Movie
Representa um filme disponível no cinema.

Atributos principais:

* movieId: Identificador único do filme.

* title: Título do filme.

* description: Sinopse do filme.

* ageRating: Classificação indicativa.

* genres: Gênero (ação, drama, comédia, etc.).

* active: Se o filme está em cartaz.

* imageUrl: Guarda a URL do cartaz.

### Room

Representa uma sala de cinema.

Atributos principais:

* roomId: Identificador único da sala.

* name: Nome ou número da sala.

### Seat
Representa um assento dentro de uma sala.

Atributos principais:

* seatId: Identificador do assento.

* seatRow: Linha em que o assento se encontra.

* seatColumn:  Coluna em que o assento se encontra.

* seatType: Tipo do acento (normal, V.I.P, preferencial, etc.).

* room: Referencia a sala que esta localizado o assento

### Session
Representa uma exibição específica de um filme.

Atributos principais:

* sessionId: Identificador da sessão.

* startTime: Data e hora da exibição.

* movie: Referencia o filme da sessão

* room: Referencia a sala em que ocorrera a sessão

### Ticket

Representa um ingresso vendido.

Atributos principais:

* ticketId: Identificador do ingresso.

* session: Referencia a sessão que ocorrera o filme.

* seat: Referencia o acento escolhido.

### User

Representa um usuário do sistema (cliente ou funcionário).

Atributos principais:

userId: Identificador do usuário.

name: Nome completo.

email: E-mail para login.

password: Senha.

birthDay: Data de nascimento

role: Tipo de usuário (Client, EMPLOYEE).

## Funcionalidades
### Movie
* Adicionar novos filmes
* Editar informações sobre o filme (title,description,ageRating,genres,image)
* Tirar o filme de exibição
* Visualizar todos os filmes 
* Visualizar todos os filmes ativos podendo filtrar por title ou genres [talvez seja utilizado um filtro de popularidade do filme em questão de quantidade de ingressos comprados]
* Permitir restaurar versões anteriores das informações do filme

### Room
* Adicionar novas salas
* Visualizar salas
* Editar salas (name)

### Seat
* Adicionar novos acentos no cinema
* Atualizar os acentos (seatType)
* Visualizar assentos de uma sala
* Desativar o acento como opção para ser escolhido 

### Session
* Adicionar sessões para um filme em certa sala
* Visualizar sessões podendo filtra-las pelo dia e horario
* Cancelar sessões [caso o sistema use transações permite rembolsos ou troca de ingressos pra sessão]

### Ticket
* Adicionar ingressos comprados pra uma sessão dizendo o acento do cliente
* Cancelar ingresso [caso o sistema use transações podera ser efetuado rembolso]
* Visualizar os ingressos da sua conta

### User
* Adicionar novos clientes
* Adicionar novos funcionarios
* Editar as informações do usuario(name,email,password)
* Excluir usuario
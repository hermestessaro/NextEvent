
# NextEvent

Uma aplicação para ser entregue em um desafio de processo seletivo. O aplicativo busca uma lista de eventos em uma API.



## Features

- Listagem dos eventos.
- Detalhes de cada evento.
- Opção para realizar check-in.
- Opção de compartilhar o evento.


## Detalhes Técnicos

O app foi feito utilizando os preceitos de Clean Architecture e um MVVM base, utilizando Use Cases para isolar melhor os ViewModels e respeitar melhor as ideias de SOLID. Para a UI foi utilizado o Jetpack Compose, para fazer a conexão com a API foi utilizado Retrofit, para a injeção de dependências foi utilizado o Hilt, e outras ferramentas foram utilizadas com propósitos diversos, como Coil - para carregar imagens, Flow - para manipular fluxos assíncronos de dados, Navigation - para realizar a troca de telas. O layout é cru porém foi feito tentando seguir as guidelines do Material Design, utilizando a biblioteca Material3.
Foram feitos testes unitários de algumas classes, e havia a intenção de realizar os testes de UI, porém não foi possível criá-los em tempo hábil.


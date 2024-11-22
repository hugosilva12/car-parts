# TP_MTSDS

## **Arquitetura do Projeto**
 <img title="Logotipo" alt="Logo" src="https://gitlab.estg.ipp.pt/grupo_mtsds/tp_mtsds/-/raw/development/Documentos/Arquitetura.png" width="600">
 
## *Descrição dos serviços*

### Auth-Service
Neste serviço são criadas as contas que permitem fazerem autenticação no sistema. É também aqui que é efetuada a  gestão de permissões e perfis de utilizadores.

### User-Service
Este serviço tem como função realizar a gestão de utilizadores do sistema, o sistema possui 3 tipos de utilizadores (Admin, Worker e Client). <br>

### Purchase-Service
Este serviço tem como função realizar a gestão de compras de automóveis. <br>

### Cardisassembly-Service
Este serviço tem como função realizar a gestão das peças desmontadas sendo o serviço `central` do projeto.<br>

### Advertising-Service
Este serviço tem como função realizar a gestão de anúncios das peças.<br>

### Storage-Service
Este serviço tem como função gerir o armazém da empresa, é aqui que é gerida a ocupação das 8 secções que constituem o armazém.<br>

### Precarious-Service
Este serviço tem como função armazenar o histórico de vendas da empresa. É também neste serviço que é calculado a margem/prejuizo de cada carro.<br>

### Sales-Service
Este serviço tem como função gerir a venda de peças.<br>

### Hystrix-Service
Dado a arquitetura do projeto, existe um conjunto de serviços que colaboram
entre si, sendo que caso algo falhe pode afetar os restantes ou mesmo colocar o sistema inacessível (por exemplo o Auth Service em caso de falha impossibilita a utilização do projeto ).
Para minimizar estas falhas foi usado o Hystrix. O Hystrix é uma biblioteca que ajuda a
controlar a interação entre serviços fornecendo tolerância a falhas a latência. Quando um serviço falha é isolado dos outros para que estes se mantenham a funcionar.<br>
**Nota de Rodapé** https://github.com/Netflix/Hystrix 

### Gateway-Service
O Spring Cloud Gateway fornece uma biblioteca para construir uma API Gateway sobre o Spring WebFlux. O Spring Cloud Gateway visa fornecer uma maneira simples e eficaz de rotear para Serviços e fornecer preocupações transversais a eles, como por exemplo segurança e 
monitorização.
Todas as comunicações do front-end são realizadas para o Gateway service. Este depois reencaminha-as para o serviço responsável pelo endpoint.<br>
Vantagens de utilização do Gateway:
- Serviço de autorização do sistema centralizado, dado que a comunicação frontend-backend é realizada apenas pelo gateway.
- A porta para comunicação é sempre a mesma não sendo necessário saber exatamente em que porta está a correr cada serviço.
- Possui a funcionalidade de adicionar filtros às rotas. <br>

Desvantagens de utilização do Gateway:
- Em caso de falha sistema fica indisponivel.

### ServiceRegistry
O Eureka Server é dos principais princípios de uma arquitetura baseada em microserviços e contém as informações sobre todos os serviços `clientes`. Cada micro serviço do projeto é registado no servidor Eureka sendo que este irá conhecer a porta e endereço IP em que cada um está a ser executado.
A principal vantagem da utilização do eureka é que os serviços se podem encontrar e comunicar sem existir necessidade de colocar o host ou a porta  de forma hard coded.

## **Comunicações assíncronas entre serviços**
**User-Service** :arrow_right: **Auth-Service** o serviço de utilizadores envia mensagem com os dados de autenticação quando é efetuado o registo de uma nova conta.<br><br>

**Auth-Service** :arrow_right: **User-Service** após receção dos dados para criar uma conta de autenticação envia uma mensagem confirmando o registo da conta ou, caso os dados sejam inválidos, envia uma mensagem para o User-Service remover a conta.  <br> <br>
**User-Service** :arrow_right: **Auth-Service** o serviço de utilizadores envia mensagem para que a conta de autenticação seja desativada quando um utilizador é removido.<br><br>

**Cardisassembly-Service** :arrow_right: **Purchase-Service** o serviço de desmontagem quando recebe uma nova peça para registar envia mensagem para que o serviço de compras verifique se o carro introduzido na criação de uma peça é válido.
<br><br>
**Purchase-Service** :arrow_right: **Cardisassembly-Service** o serviço de compras envia mensagem para  o serviço de desmontagem de peças informando se o carro está ou não registado no sistema.<br><br>


**Cardisassembly-Service** :arrow_right: **Advertising-Service** quando o serviço de desmontagem recebe a mensagem do serviço de compras a indicar se o carro existe ou não verifica-a e, caso a resposta seja válida,  envia uma mensagem para o serviço de anúncios com os dados para criação de um novo anúncio. <br><br>

**Advertising-Service** :arrow_right: **Cardisassembly-Service** assim que um anúncio é criado o serviço de anuncios envia uma mensagem para o serviço de desmontagem indicando que a peça já se encontra anunciada para venda. 

**Cardisassembly-Service** :arrow_right: **Storage-Service** quando o serviço de desmontagem recebe a mensagem do serviço de compras a indicar se o carro existe ou não verifica-a e caso a resposta seja válida,  envia uma mensagem para o serviço de gestão de armazém indicando que existe uma nova peça em stock. <br><br>

**Purchase-Service** :arrow_right: **Cardisassembly-Service** quando o serviço de compras recebe um request Post para criar uma nova venda envia mensagem para o serviço de desmontagem para que este verifique se a peça ainda está disponivel para venda. <br><br>

**Cardisassembly-Service** :arrow_right: **Purchase-Service** quando o serviço de desmontagem recebe uma mensagem do serviço de compras para verificar se a peça está disponivel para venda faz a verificação e envia uma mensagem confirmando ou recusando a venda. <br><br>

**Cardisassembly-Service** :arrow_right: **Storage-Service** quando o serviço de desmontagem recebe uma mensagem do serviço de compras para verificar se a peça está disponivel para venda faz a verificação e, quando a compra pode ser confirmada,  envia uma mensagem ao serviço de gestão de stock informando que a peça deixa de estar em stock. <br><br>

**Cardisassembly-Service** :arrow_right: **Advertising-Service** quando o serviço de desmontagem recebe uma mensagem do serviço de compras para verificar se a peça está disponivel para venda faz a verificação e, quando a compra pode ser confirmada,  envia uma mensagem ao serviço de anúncios para que este proceda à remoção do anúncio. <br><br>

**Cardisassembly-Service** :arrow_right: **Precarious-Service** quando o serviço de desmontagem recebe uma mensagem do serviço de compras para verificar se a peça está disponivel para venda faz a verificação e, quando a compra pode ser confirmada,  envia uma mensagem ao para o serviço do  histórico de preços com o valor, a data e informações da peça vendida. <br><br>

## **Tecnologias Utilizadas**

**Spring Boot**  é uma framework Open Source desenvolvida em JAVA. Foi criada com o objetivo de facilitar o
desenvolvimento de aplicações, explorando, para isso, os conceitos de Inversion of Control e Injeção de Dependências.

**React**  React JS é uma biblioteca JavaScript para a criação de interfaces de utilizador — ou UI (user interface). React é baseado em componentes, o que permite o reaproveitamento de código e facilita a manutenção.


**MySQL**  As bases de dados relacionais usadas para serviços são em MYSQL.

**Docker** consiste numa plataforma de criação de containers Open Source e tem como principal objetivo permitir que
developers criem packages de aplicações dentro de containers, simplificando assim a entrega de
aplicações distribuídas.
Os três tipos de containers utilizados para o projeto foram:
- Containers Spring boot para os serviços;
- Container MySQL para as base de dados;
- Container RabbitMq para o protocolo de comunicações;

**RabbitMQ**É um software de mensagens open source, que implementa o Advance Message Queuing
Protocol (AMQP).Este tem a capacidade de lidar com o tráfego de mensagens de forma rápida
e confiável.
 A escolha desta tecnologia para projeto devesse à simplicidade de implementação que esta oferece e à sua capacidade de realizar comunicações assíncronas entre serviços, armazenando as mensagens em queues.
Link com informação do RabbitMQ (https://en.wikipedia.org/wiki/RabbitMQ) meter em rodapé.

**Kubernetes** Kubernetes é um sistema de orquestração de contêineres open-source que automatiza a implantação, o dimensionamento e a gestão de aplicações em contêineres. 
Link com informação do Kubernetes (https://www.vmware.com/topics/glossary/content/kubernetes.html) meter em rodapé.



## **Setup local**
**JDK:** usar JDK 8, também chamado de 1.8 (é garantido que funciona com a versão 2021.0.5 da cloud que será utilizada) <br>
**Dados de Acesso de acesso gerados automaticamente:** *username*:  `root` *password*: `password` <br>


## **Docker Compose**
Usar compose `docker-compose-single-database.yaml`. `docker-compose.yaml` fica para demonstração de funcionamento de múltiplos containers para as base de dados.




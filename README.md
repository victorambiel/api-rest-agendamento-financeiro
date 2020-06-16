## API REST para um Sistema de Agendamento de Transferências Financeiras

O sistema recebe uma requisição via JSON de agendamento de transferencia financeira contendo as informações:
- Conta de Origem
- Conta de Destino
- Valor da Transferencia
- Data da Transferencia

Com essas informações faz o cálculo do valor da taxa da transferência e retorna um JSON com as informações confirmando o agendamento.

### O foi utilizado:
- Java 11
- Spring Boot 2.3.1
- Maven
- Banco de Dados H2
- JUnit
- [Postman](https://www.postman.com/) (para teste das requisições)
- Docker (opcional)

### Como rodar:
Há três maneiras de rodar o projeto localmente:

 1. O projeto pode ser importado para alguma IDE e executado. 
 2. Fazer o build do projeto com o maven *./mvnw install* e rodar o *.jar* criado dentro da pasta target.
 3. **(Necessário ter o Docker instalado)** Utilizar o script ([build-and-run-docker.sh](https://github.com/victorambiel/api-rest-agendamento-financeiro/blob/master/agendamento/build-and-run-docker.sh)) que contém dentro do projeto. O script faz todo o processo de buildar o projeto, criar uma imagem Docker com o *.jar* e rodar a imagem com o serviço.

Nos três casos, o endereço para fazer as requisições do serviço é http://localhost:8080/agendamento

### Exemplos de Listar Agendamentos
É possível visualizar todos os agendamento realizando uma requisição **GET (Postman)** no endereço http://localhost:8080/agendamento. Sem nenhum filtro irá retornar todos os agendamentos. É possível filtrar por valor da transferência e data da transferência.

**Exemplo 1 - Sem nenhum filtro**

http://localhost:8080/agendamento

**Exemplo 2 - Filtrar por Valor da Transferência**

http://localhost:8080/agendamento?valorDaTransferencia=100

**Exemplo 3 - Filtrar por Data da Transferência**

http://localhost:8080/agendamento?dataDaTransferencia=2020-06-15

**Exemplo 4 - Filtrar por Valor e Data da Transferência**

http://localhost:8080/agendamento?valorDaTransferencia=100&dataDaTransferencia=2020-06-15

### Exemplos de Cadastrar Agendamento
Os agendamentos são realizados fazendo requisições **POST (Postman)** no endereço http://localhost:8080/agendamento com um JSON no padrão dos exemplos abaixo.

**Exemplo 1 - Taxa Tipo A**
JSON de Requisição:

    {
       "contaDeOrigem": 123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "dataDaTransferencia": "2020-07-15"
    }

JSON de Retorno:

    {
       "id":  1,
       "contaDeOrigem":  123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "taxa":  6.0000,
       "dataDaTransferencia":  "2020-07-15",
       "dataDeAgendamento":  "2020-06-15T21:54:18.055917"
    }

**Exemplo 2 - Taxa Tipo B**
JSON de Requisição:

    {
       "contaDeOrigem": 123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "dataDaTransferencia": "2020-06-24"
    }

JSON de Retorno:

    {
       "id":  2,
       "contaDeOrigem":  123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "taxa":  108.0000,
       "dataDaTransferencia":  "2020-06-24",
       "dataDeAgendamento":  "2020-06-15T21:54:18.055917"
    }
**Exemplo 3 - Taxa Tipo C (Até 20 Dias)**
JSON de Requisição:

    {
       "contaDeOrigem": 123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "dataDaTransferencia": "2020-06-24"
    }

JSON de Retorno:

    {
       "id":  3,
       "contaDeOrigem":  123456,
       "contaDeDestino": 117722,
       "valorDaTransferencia": 100.00,
       "taxa":  8.0000,
       "dataDaTransferencia":  "2020-06-26",
       "dataDeAgendamento":  "2020-06-15T21:54:18.055917"
    }

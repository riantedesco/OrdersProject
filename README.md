# OrdersProject

> O projeto está organizado em dois microsserviços que se comunicam entre si, sendo eles msorder e mspayment. Ambos os projetos precisam estar em execução para que todo o fluxo seja acompanhado.

> O msorder possui 4 entidades principais: client, product, order e productOrder. Para testar o fluxo de order (principal funcionalidade do projeto) deve-se seguir as seguintes etapas:
  > Primeiramente é necessário realizar um POST de client;
  > Em seguida, realizar dois POSTs de product;
  > Por fim, é possível realizar um POST de order que contenha o id do client cadastrado e um list de productOrders Nesse list, cada productOrder deve conter o id dos products cadastrados previamente;
  > O msorder possui também outros endpoints simples de update, list, find e delete.

> O mspayment possui apenas a entidade payment. O fluxo principal de payment se dá pelo POST, que é acionado automaticamente quando o serviço consome uma order criada no msorder.

> Ambos os microsserviços possuem testes unitários e integrados.

> A comunicação é feita pelo RabbitMQ: http://localhost:15672/#/
  > Username: guest, password: guest

> Ambos os projetos utilizam o banco Postgres para o armazenamento dos dados.
  > user: postgres
  > password: masterkey

> O envio de payloads e os testes manuais são feitos via Postman, cujo arquivo de collections está na raíz do projeto.

> A documentação dos endpoints foi feita com Swagger
  > Swagger do msorder: http://localhost:7071/swagger-ui.html
  > Swagger do mspayment: http://localhost:7072/swagger-ui.html

> Link do vídeo de apresentação (ANTIGO):
https://www.loom.com/embed/a432993702114ec3b2fc254a8240321b

> Link do vídeo de apresentação (ATUALIZADO):
https://www.loom.com/embed/0d99125988744d2594fd9c03b425edb2

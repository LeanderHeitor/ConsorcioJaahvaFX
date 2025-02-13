# ConsorcioJaahvaFX
# **Gerenciador de Consórcios**

## **Descrição**
Um consórcio é um sistema de compra colaborativa, no qual um grupo de pessoas contribui financeiramente, de forma periódica, para adquirir bens ou serviços. Cada membro do grupo paga mensalidades e, em troca, pode ser contemplado com o direito de usar o valor acumulado para adquirir o bem desejado. A contemplação ocorre por sorteio ou lance, promovendo igualdade de oportunidades entre os participantes.

Este sistema tem como objetivo gerenciar todas as etapas de um consórcio, desde o cadastro de clientes e grupos até o controle de pagamentos e sorteios de contemplação. A aplicação busca oferecer uma solução robusta para simular e acompanhar o funcionamento de consórcios, permitindo que grupos e administradores monitorem pagamentos, contemplações e o saldo financeiro.

O **Gerenciador de Consórcios** permitirá:
- **Organizar grupos de consórcio**: Cada grupo tem um valor total, definido pelo bem ou serviço alvo, um número fixo de participantes, e uma taxa de administração.
- **Acompanhar a situação financeira dos participantes**: Informando saldos devedores, parcelas pagas e status de contemplação.
- **Simular contemplações mensais**: Escolhendo participantes de forma randômica ou por critérios definidos, como o maior número de parcelas pagas (simulação de lance).
- **Emitir relatórios e documentos**: Ajudando os administradores a gerenciar informações e tomar decisões.

O projeto será desenvolvido em **Java Standard Edition**, utilizando conceitos de **POO** como herança, polimorfismo e encapsulamento, além de estruturas como listas para modelagem dos participantes, contratos e pagamentos.

---

## **Requisitos Funcionais**

### **Gerenciamento de Entidades**
- **REQ01**: Gerenciamento de clientes, incluindo informações como nome, CPF, telefone e e-mail.
- **REQ02**: Gerenciamento de grupos de consórcio, com informações como nome do grupo, valor total do consórcio, número de participantes e taxa de administração.
- **REQ03**: Gerenciamento de contratos de participação, vinculando clientes a grupos com informações sobre parcelas pagas, saldo devedor e status (ativo, contemplado ou encerrado).

### **Simulações e Operações**
- **REQ04**: Cálculo do valor das parcelas com base no valor total do consórcio, número de participantes e taxa de administração.
- **REQ05**: Registro e controle dos pagamentos realizados por cada cliente, atualizando automaticamente o saldo devedor.
- **REQ06**: Simulação de sorteios mensais para contemplação, escolhendo aleatoriamente um cliente ativo no grupo.
- **REQ07**: Atualização automática do status do contrato para "contemplado" quando um cliente é sorteado.

### **Consultas e Relatórios**
- **REQ08**: Consulta ao histórico de contemplações de um grupo, incluindo data, cliente contemplado e status do grupo.
- **REQ09**: Relatório detalhado do saldo devedor de todos os clientes de um grupo.
- **REQ10**: Exibição de estatísticas financeiras, como valor total arrecadado e valor pendente em um grupo.

### **Geração de Documentos**
- **REQ11**: Geração de boletos fictícios para pagamento das parcelas (ex.: exportar um arquivo com informações de pagamento).
- **REQ12**: Emissão de relatórios em formato texto simples (ex.: `.txt` ou `.csv`) contendo o resumo das informações de um grupo.

### **Validações e Segurança**
- **REQ13**: Validação automática de dados de entrada, como CPF válido, e-mail correto e valor positivo para parcelas.
- **REQ14**: Bloqueio de operações financeiras (ex.: pagamentos e sorteios) caso o grupo esteja encerrado.

### **Configurações Extras**
- **REQ15**: Permitir alteração da taxa de administração de um grupo com atualização automática das parcelas futuras.

## Possíveis APIs/Bibliotecas a serem usadas
- **Joda-Money**: Para cálculos e manipulação de valores monetários com precisão.
- **iText**: Para geração de documentos PDF, como relatórios financeiros dos participantes e resultados dos sorteios.
- **Google Finance**: Para consulta de índices financeiros em tempo real, como IPCA, INCC ou taxas de câmbio.


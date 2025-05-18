[![Java](https://img.shields.io/badge/Java-23-blue.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.32.0-green.svg)](https://www.selenium.dev/)


Automação de testes para o site automationpractice.pl utilizando Selenium WebDriver com Java.
Foco em testes funcionais essenciais como links externos, formulário de contato e fluxo completo de compra. Visão Geral

🛠 Tecnologias Utilizadas

    Java 23
    Selenium WebDriver 4.32.0
    Maven para gerenciamento de dependências
    Navegadores suportados: Chrome e Firefox
    Padrão Page Object Model (POM) para organização do código

📂 Estrutura do Projeto

├── drivers/ # Factory para criação dos WebDrivers
├── pages/ # Classes Page Objects (LoginPage, ProductPage, ContactUsPage)
├── externalLinks/ # Testes dos links externos do rodapé 
├── contactUs/ # Testes do formulário de contato (positivo e negativo)
├── completePurchase/ # Teste do fluxo completo de compra 
└── pom.xml # Configuração Maven

✅ Como Executar

    Pré-requisitos:
        Java 23 instalado
        Maven instalado
        ChromeDriver e GeckoDriver (Firefox) disponíveis no PATH
    Clonar o repositório:

    bash

    git clone https://github.com/seu-usuario/SeleniumAutomationPractice.git
    cd SeleniumAutomationPractice

    Instalar dependências:

    bash

    mvn clean install

    Executar testes:
        Rodar a classe desejada diretamente pela sua IDE (IntelliJ, Eclipse)
        Ou use o comando Maven (caso configure plugins de execução)

🧪 Casos de Teste Implementados
🔗 Testes de Links Externos (externalLinks/)

    Verifica se os botões de redes sociais no rodapé redirecionam corretamente para:
        Facebook
        Twitter
        RSS
        Blog

📨 Formulário de Contato (contactUs/)

    Teste Positivo: Envio de mensagem com e-mail válido
    Teste Negativo: Envio com e-mail inexistente (espera-se falha, mas o sistema aceita — possível BUG)

🛒 Fluxo de Compra Completo (completePurchase/)

    Login com credenciais válidas
    Adição de produto ao carrinho
    Checkout com aceite de termos
    Escolha do método de envio e pagamento
    Confirmação do pedido

🧰 Organização em Page Objects

As interações com as páginas foram encapsuladas nas seguintes classes:

    LoginPage.java: Login no site
    ContactUsPage.java: Formulário de contato
    ProductPage.java: Ações sobre o produto


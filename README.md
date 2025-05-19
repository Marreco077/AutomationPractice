[![Java](https://img.shields.io/badge/Java-23-blue.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.32.0-green.svg)](https://www.selenium.dev/)


Automação de testes para o site automationpractice.pl utilizando Selenium WebDriver com Java.

 Visão Geral:

 Tecnologias Utilizadas

    Java 23
    Selenium WebDriver 4.32.0
    Maven para gerenciamento de dependências
    Navegadores suportados: Chrome e Firefox
    Padrão Page Object Model (POM) para organização do código

📂 Estrutura do Projeto

AutomationPractice/
├── .idea/                      # Configurações do IntelliJ
├── pom.xml                     # Configuração do Maven
└── src/
├── main/
│   ├── java/
│   │   ├── drivers/         # Factory para criação dos WebDrivers
│   │   ├── pages/           # Classes Page Objects (LoginPage, ProductPage, ContactUsPage)
│   │   └── org.example/     # Classe principal (Main.java), pode ser renomeado para algo mais descritivo
│   └── resources/           # Arquivos de configuração (ex: .properties, .xml, etc.)
└── test/
├── java/
│   └── tests/           # Diretório raiz para os testes automatizados
│       ├── completepurchase/  # Testes do fluxo completo de compra
│       ├── contactus/         # Testes do formulário de contato
│       ├── externallinks/     # Testes dos links externos do rodapé
│       ├── login/             # Testes relacionados ao login
│       ├── products/          # Testes relacionados aos produtos
│       ├── search/            # Testes de busca
│       └── session/           # Testes de sessão/autenticação
└── resources/          # Arquivos de teste (dados, configs, etc.)


 Como Executar

    Pré-requisitos:
        Java 23 instalado
        Maven instalado
        ChromeDriver e GeckoDriver (Firefox) disponíveis no PATH
    Clonar o repositório:

    bash

    git clone https://github.com/Marreci77/AutomationPractice
    cd AutomationPractice

    Instalar dependências:

    bash

    mvn clean install

    Executar testes:
        Rodar a classe desejada diretamente pela sua IDE (IntelliJ, Eclipse)


Perfeito! Aqui está a seção de **"Cenários de Teste"** no estilo BDD, formatada para você adicionar diretamente ao seu `README.md` com consistência visual:

---

## ✅ Cenários de Teste Implementados

### 🔐 Login

* **Cenário: Login válido**
  *Dado* que o usuário esteja na página "automation practice"
  *Quando* o usuário informar email e senha corretos e clicar no botão de login
  *Então* o usuário deve ser redirecionado para a página "my-account"

* **Cenário: Login inválido**
  *Dado* que o usuário esteja na página "automation practice"
  *Quando* o usuário informar email ou senha incorretos
  *Então* o sistema deve exibir "Authentication failed"

* **Cenário: Login com e-mail inexistente (BUG)**
  *Dado* que o usuário informe um e-mail inválido
  *Então* o sistema deveria informar que o e-mail não existe
  *MAS* exibe apenas "Authentication failed"

---

### 🔄 Sessão

* **Cenário: Manter sessão ativa após login e navegação**
  *Dado* que o usuário tenha feito login com credenciais válidas
  *Quando* navegar entre páginas
  *Então* a sessão deve se manter ativa com o botão de logout visível

---

### 🔍 Pesquisa

* **Cenário: Pesquisa de produtos no site**
  *Dado* que o usuário esteja na página principal
  *Quando* digita um termo válido e submete
  *Então* a página deve exibir resultados relacionados

* **Cenário: Pesquisa com string vazia (apenas espaços)**
  *Quando* o usuário submete uma string vazia
  *Então* o sistema deve exibir zero resultados sem travar

* **Cenário: Pesquisa por termo inexistente**
  *Quando* o usuário pesquisa um termo inexistente
  *Então* o sistema deve mostrar zero resultados

* **Cenário: Pesquisa com string longa e caracteres especiais**
  *Quando* o usuário submete uma string extensa e especial
  *Então* o sistema deve tratar corretamente e responder adequadamente

---

### 🛒 Fluxo de Compra

* **Cenário: Adicionar produto ao carrinho**
  *Dado* que o usuário esteja na página de um produto
  *Quando* clicar em "Add to cart"
  *Então* o sistema deve exibir um modal de confirmação

* **Cenário: Fluxo completo de compra no site**
  *Dado* que o usuário esteja logado e na página do produto
  *Quando* adicionar ao carrinho, aceitar termos, escolher envio e pagamento
  *Então* o sistema deve confirmar o pedido com sucesso

---

### 📨 Formulário de Contato

* **Cenário: Envio de mensagem válido**
  *Dado* que o usuário preencha corretamente o formulário
  *Então* deve receber a mensagem "Your message has been successfully sent to our team."

* **Cenário: Envio de mensagem com e-mail inválido (BUG)**
  *Dado* que o usuário utilize um e-mail inexistente
  *Então* o sistema deveria exibir erro
  *MAS* atualmente permite o envio normalmente

---

### 🔗 Links Externos

* **Cenário: Verificar redirecionamento dos links do rodapé**
  *Dado* que o usuário esteja na página inicial
  *Quando* clicar em Facebook, Twitter, RSS, Blog
  *Então* cada link deve redirecionar corretamente

---

Se quiser, posso integrar tudo isso direto ao corpo completo do seu README.md — é só me enviar a versão atual para fazer a fusão.

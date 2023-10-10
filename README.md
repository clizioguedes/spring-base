# Acer Seller Center - Código Base para Microserviços

![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)
[![forthebadge](https://raw.githubusercontent.com/clizioguedes/images/05a25edd2ebdf9df8dd57be1bebe5ff89e68d538/ufrn/acer/sellercenter/badges/spring-badge.svg)
![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)

Este é um código base desenvolvido em Java, utilizando o JDK 17 e o framework Spring na versão 3.1.3. O objetivo deste microserviço é servir como base para os outros microserviços do projeto Acer Seller Center

![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/files.png)

- [Requisitos](#requisitos)
- [Configuração](#configurando-ambiente)
- [Maven](#maven)
- [Intellij IDEA](#usando-no-intellij-idea)
- [VSCode](#usando-no-vscode)
- [Estrutura de Pastas](#estrutura-de-pastas)

## Requisitos

- JDK >= 17 - [Download](https://jdk.java.net/archive/)
- Docker (Opcional)

## Configurando Ambiente

1. ```sh
    git clone https://projetos.imd.ufrn.br/acer/acer-seller-center.git
   ```

2. Abra a pasta clonada com seu editor de texto favorito.

3. Para adicionar o banco de dados local usando o Docker, ao acessar a pasta `/docker` no terminal, execute o comando `docker compose up -d`. [Veja mais sobre os comandos](https://docs.docker.com/engine/reference/commandline/compose_up).

![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/docker-compose.png)

OBS: Caso não use o Docker, basta instalar o PostgreSQL com a configuração (username/password/port) especificada na propriedade datasource do `/src/main/resources/application.properties`

### Maven

- Desconsidere esta etapa se estiver utilizando o IntelliJ IDEA.

- Com exceção do IntelliJ, que já vem com o Maven integrado na configuração do JDK, é necessário verificar se o Maven está configurado para a execução do projeto.

  - Execute o comando `mvn --version`. Caso não tenha um retorno semelhante ao da imagem abaixo, será necessário configurá-lo.
    ![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/maven-version.png)

  - Para configurar o Maven, é recomendado seguir os links abaixo de acordo com o sistema operacional.
    - MacOS
      [Maven no MacOS](https://www.digitalocean.com/community/tutorials/install-maven-mac-os).
    - Windows / Linux
      [Maven no Windows / Linux](https://www.baeldung.com/install-maven-on-windows-linux-mac)

### Usando no IntelliJ IDEA

1. Ao importar o projeto, o próprio IntelliJ deverá detectar a classe principal do projeto, como mostrado na imagem abaixo.
   ![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/exec-intellij.png)

   OBS: Caso o IntelliJ não a encontre automaticamente, basta executar a partir do arquivo da classe principal descrito na imagem abaixo ou criar um arquivo de configuração. [Saiba mais aqui](https://www.jetbrains.com/help/idea/run-debug-configuration-spring-boot.html)

   ![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/exec-class-intellij.png)

2. Agora, você pode executar o projeto clicando no botão de executar ou debug para desenvolvimento.

### Usando no VSCode

1. Com o Maven instalado e configurado, abra o VSCode na pasta raiz do projeto, como descrito na imagem abaixo.
   ![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/vscode-terminal.png)
2. Execute o comando `mvn spring-boot:run` no terminal e verá o resultado com o projeto sendo executado na porta especificada no arquivo de configurações.
   ![image](https://raw.githubusercontent.com/clizioguedes/images/main/ufrn/acer/sellercenter/ms-base/vscode-terminal-executed.png)

## Swagger

- [Link do Swagger]

## Estrutura de Pastas

- `/docker` - armazena as configurações de imagens do Docker, caso o projeto necessite.
- `/controller` - contém classes ou módulos que lidam com a camada de controle da aplicação.
- `/mappers` - contém classes responsáveis por mapear dados entre diferentes formatos ou estruturas.
- `/model` - contém as classes que representam a estrutura de dados do seu aplicativo.
- `/repository` - contém classes ou interfaces que definem o acesso aos dados do banco de dados.
- `/service` - contém a lógica de negócios da aplicação.
- `/utils` - contém classes utilitárias que fornecem funções comuns ou recursos compartilhados em todo o projeto.

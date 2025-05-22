# 🎵 Spotifei 

## 📌 Sobre o Projeto  
**Spotifei** é um sistema desenvolvido para compartilhar **informações** sobre músicas e playlist, inspirado na lógica do **Spotify**.

## 🔧 Tecnologias Utilizadas  
- **Java Swing** → Interface gráfica  
- **JDBC PostgreSQL** → Banco de dados  
- **MVC (Model-View-Controller)** → Arquitetura do sistema  

## 👤 Funcionalidades do Usuário  
✅ Cadastro de usuário  
✅ Login e autenticação  
✅ Busca de músicas por nome, artista ou gênero  
✅ Curtir e descurtir músicas  
✅ Gerenciar playlists (criar, editar, excluir)  
✅ Visualizar histórico de busca
✅ Visualizar músicas curtidas e descurtidas  

## 💾 Banco de Dados  
O projeto utiliza **PostgreSQL** para garantir a persistência dos dados. Todas as operações dos usuários são refletidas diretamente no banco de dados.

## 🚀 **Passo a Passo do Desenvolvimento**  

### 🏗️ **1. Planejamento e Estruturação**
- Definição dos requisitos funcionais e não funcionais.  
- Criação do **diagrama de classes** baseado na arquitetura **MVC**.  
- Configuração inicial do banco de dados **PostgreSQL** com tabelas para usuários, músicas e playlists.  

### 📝 **2. Implementação do Banco de Dados**
- Criação das tabelas necessárias usando **PostgreSQL**.  
- Desenvolvimento de scripts SQL para inserção e consulta de dados.  
- Testes de conexão para garantir a comunicação entre Java e PostgreSQL via **JDBC**.  

### 💻 **3. Desenvolvimento do Backend (Model)**
- Implementação das classes **Modelo** (`Usuario`, `Artista`, `Musica`, `Playlist`).  
- Desenvolvimento do **DAO (Data Access Object)** para manipulação do banco de dados.  
- Métodos CRUD (`Create, Read, Update, Delete`) para gestão de usuários, músicas e playlists.  

### 🎨 **4. Criação da Interface Gráfica (View)**
- Construção das telas utilizando **Java Swing**.  
- Desenvolvimento de formulários de **login, cadastro e buscas**.  
- Implementação da funcionalidade de **curtir/descurtir músicas e gerenciar playlists**.  

### 🔀 **5. Implementação do Controle (Controller)**
- Implementação da lógica de controle para processar as ações do usuário.  
- Conexão entre **Model** e **View** para garantir um fluxo correto de informações.  
- Validação de dados antes da inserção no banco.  

### 🛠️ **6. Gerando o Executável `.jar`**
- Configuração do **NetBeans** para gerar um **Fat JAR** contendo todas as dependências.  
- Ajuste do arquivo `build.xml` para empacotamento correto.  
- Teste da execução do `.jar` via terminal (`java -jar dist/spotifei.jar`).

## Link para o video: https://youtu.be/_nORB3gLQSE


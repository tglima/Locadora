## Version 0.2.0 (Add Model Veiculo e suporte ao PrimeFaces)

Criada as seguintes classes e Enums referentes ao Model Veiculo
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCategorias.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCombustiveis.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCores.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpMarcas.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpSituacao.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**Veiculo.java**

Criada a classe controller responsável pelo cadastro do veículo.
- src/main/java/br/edu/tglima/locadora/controllers/**CadVeic.java**

Criada a classe **FacesUtil**, para facilitar o envio de mensagens ao usuário.
- src/main/java/br/edu/tglima/locadora/util/**FacesUtil.java**

Criada a página XHTML para realizar o cadastro do veículo.
- src/main/webapp/cadastro/**veiculo.xhtml**

Outras melhorias
- Editado o arquivo **style.css** para uma melhor experiência com os inputs.
- Editado o arquivo **pom.xml**. Adicionado o suporte ao **PrimeFaces**.
- Editado o arquivo **content-top.xhtml**. Habilitado para integração com o PrimeFaces
- Editado o arquivo **.gitignore** . Ignorado o arquivo /.tern-project

## Version 0.1.0 (Add Bootstrap e template)

Adicionado arquivos referentes ao Bootstrap
- src/main/webapp/resources/css/**bootstrap.min.css**
- src/main/webapp/resources/js/**bootstrap.min.js**
- src/main/webapp/resources/jquery/**jquery-2.2.4.js**
- src/main/webapp/resources/fonts/**glyphicons-halflings-regular.eot**
- src/main/webapp/resources/fonts/**glyphicons-halflings-regular.svg**
- src/main/webapp/resources/fonts/**glyphicons-halflings-regular.ttf**
- src/main/webapp/resources/fonts/**glyphicons-halflings-regular.woff**
- src/main/webapp/resources/fonts/**glyphicons-halflings-regular.woff2**

Adicionado arquivos referentes ao template:
- src/main/webapp/resources/layout/**base-layout.xhtml**
- src/main/webapp/resources/layout/**navbar-top.xhtml**
- src/main/webapp/resources/layout/**sidebar.xhtml**
- src/main/webapp/resources/layout/**content-top.xhtml**
- src/main/webapp/resources/css/**style.css**

Criada página para testar layout:
- src/main/webapp/**teste.xhtml**

Adicionada imagem para simular foto do usuário.
- src/main/webapp/resources/img/**photo-woman.jpg**

Outras melhorias
- O arquivo CHANGELOG.md está mais simples e claro de ser entendido.
- Adicionada a configuração do arquivo src/main/resources/META-INF/**beans.xml**.


## Version 0.0.1 (Alterado o weld-servlet)

- Alterado da versão 2.4.2.SP1 para 2.2.16.SP1

## Version 0.0.1 (Commit Inicial)

Configurado os arquivos básicos:
- src/main/webapp/WEB-INF/**faces-config**
- src/main/webapp/WEB-INF/**web.xml**
- .gitignore
- Criado o arquivo src/main/resources/META-INF/**beans.xml**


Adicionadas as dependências básicas
- Mojarra
- JSF API
- CDI
- Jandex





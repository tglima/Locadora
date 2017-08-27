## Version 0.4.1 (Refatoração do Model Veiculo)

Refatorada as seguintes classes:
- src/main/java/br/edu/tglima/locadora/models/veiculo/**Veiculo.java**
- src/main/java/br/edu/tglima/locadora/util/**VeiculoUtil.java**
- src/main/java/br/edu/tglima/locadora/controllers/**CadVeic.java**
- src/main/java/br/edu/tglima/locadora/controllers/**EditVeic.java**

Adicionado o seguinte enum:
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpStatus.java**

Removido o seguinte enum:
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpSituacao.java**

Adaptadas as seguintes páginas XHTML:
/cadastro/**veiculo.xhtml**
/edicao/**veiculo.xhtml**

## Version 0.4.0 (Add Páginas de edição)

Criada a classe controller responsável pela edição de clientes.
- src/main/java/br/edu/tglima/locadora/controllers/**EditCliente.java**

Criada a classe controller responsável pela edição de veículos.
- src/main/java/br/edu/tglima/locadora/controllers/**EditVeic.java**

Criada a classe controller responsável pela edição de funcionários.
- src/main/java/br/edu/tglima/locadora/controllers/**EditFunc.java**

Criada a página XHTML para realizar a edição de clientes.
 - src/main/webapp/edicao/**cliente.xhtml**

Criada a página XHTML para realizar a edição de veículos.
 - src/main/webapp/edicao/**veiculo.xhtml**

Criada a página XHTML para realizar a edição de funcionários.
 - src/main/webapp/edicao/**funcionario.xhtml**

Criadas as seguintes classes com métodos utilitários 
- src/main/java/br/edu/tglima/locadora/util/**ClienteUtil.java**
- src/main/java/br/edu/tglima/locadora/util/**FuncUtil.java**
- src/main/java/br/edu/tglima/locadora/util/**TempoUtil.java**
- src/main/java/br/edu/tglima/locadora/util/**VeiculoUtil.java**

Outras melhorias
- Melhorias nas seguintes classes controllers: **CadCliente.java**, **CadFunc.java** e **CadVeic.java**.
- Melhorias nas seguintes páginas xhtml: cadastro/**cliente.xhtml**, cadastro/**veiculo.xhmtl** e **sidebar.xhtml** .


## Version 0.3.0 (Add Páginas de cadastro)

 Criadas as seguintes classes e enums para representar os Models
 - **Superclasse Pessoa** src/main/java/br/edu/tglima/locadora/models/pessoa/**Pessoa.java**
 - **Enum OpGeneros** src/main/java/br/edu/tglima/locadora/models/pessoa/**OpGeneros.java**
 - **Subclasse Cliente** src/main/java/br/edu/tglima/locadora/models/pessoa/**TiposCargo.java**
 - **Subclasse Funcionario** src/main/java/br/edu/tglima/locadora/models/pessoa/**Funcionario.java**
 - **Enum TiposCargo** src/main/java/br/edu/tglima/locadora/models/pessoa/**TiposCargo.java**
 
 Criada a classe controller responsável pelo cadastro de clientes.
 - src/main/java/br/edu/tglima/locadora/controllers/**CadCliente.java**
 
 Criada a classe controller responsável pelo cadastro de funcionários.
 - src/main/java/br/edu/tglima/locadora/controllers/**CadFunc.java**

 Criada a página XHTML para realizar o cadastro de clientes.
 - src/main/webapp/cadastro/**cliente.xhtml**
 
 Criada a página XHTML para realizar o cadastro de funcionários.
 - src/main/webapp/cadastro/**funcionario.xhtml**

Outras melhorias
 - Editado o arquivo **base-layout.xhtml**. Adicionado javascript para impedir conflito entre versões diferentes do jquery.
 - Editado o arquivo **sidebar.xhtml** para que ele aponte para as páginas já criadas.
 - Editado o arquivo **veiculo.xhtml**. Removida a tabela com os veículos recém cadastrados.
 - Editado o arquivo **CadVeic.java**. Removido a função que adiciona os veículos cadastrados em uma lista.
 - Editado o arquivo **web.xml**. Adicionado parâmetro para que a aplicação utilize o mesmo fuso horário do sistema operacional do servidor na qual ela está hospedada.
 

## Version 0.2.0 (Add Model Veiculo e suporte ao PrimeFaces)

Criadas as seguintes classes e Enums referentes ao Model Veiculo
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCategorias.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCombustiveis.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpCores.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpMarcas.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**OpSituacao.java**
- src/main/java/br/edu/tglima/locadora/models/veiculo/**Veiculo.java**

Criada a classe controller responsável pelo cadastro de veículos.
- src/main/java/br/edu/tglima/locadora/controllers/**CadVeic.java**

Criada a classe **FacesUtil**, para facilitar o envio de mensagens ao usuário.
- src/main/java/br/edu/tglima/locadora/util/**FacesUtil.java**

Criada a página XHTML para realizar o cadastro de veículos.
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





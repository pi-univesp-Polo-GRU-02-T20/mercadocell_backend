# Configuração do Ambiente Java para MercadoCell

Este documento contém instruções para configurar o ambiente de desenvolvimento necessário para executar o projeto MercadoCell.

## Pré-requisitos

- Windows 10/11
- PowerShell (já incluído no Windows)
- Acesso de administrador (para instalar software)

## Componentes Instalados

Baseado na análise do `pom.xml`, o script irá instalar:

- **Java 11 (OpenJDK)**: Versão requerida pelo projeto
- **Maven**: Gerenciador de dependências e build
- **MySQL**: Banco de dados (já que o projeto usa mysql-connector-java)
- **Chocolatey**: Gerenciador de pacotes para Windows

## Como Executar

### Opção 1: Execução Direta (Recomendado)

1. Abra o PowerShell como **Administrador**
2. Navegue até o diretório do projeto
3. Execute o comando:

```powershell
.\setup-java-environment.ps1
```

### Opção 2: Execução com Política de Execução

Se encontrar problemas com a política de execução:

```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
.\setup-java-environment.ps1
```

## O que o Script Faz

1. **Verifica/Instala Chocolatey**: Se não estiver instalado, instala automaticamente
2. **Atualiza Chocolatey**: Garante que está na versão mais recente
3. **Instala Java 11**: OpenJDK 11 via Chocolatey
4. **Instala Maven**: Versão mais recente via Chocolatey
5. **Instala MySQL**: Servidor MySQL via Chocolatey
6. **Configura Variáveis de Ambiente**: 
   - `JAVA_HOME`
   - `MAVEN_HOME`
7. **Verifica Instalações**: Confirma se tudo foi instalado corretamente

## Pós-Instalação

Após executar o script:

1. **Reinicie o PowerShell** para aplicar as variáveis de ambiente
2. **Configure o MySQL**:
   ```powershell
   # Iniciar serviço MySQL
   net start mysql
   
   # Ou instalar como serviço
   mysqld --install
   ```

3. **Teste as instalações**:
   ```powershell
   java -version
   mvn -version
   echo $env:JAVA_HOME
   echo $env:MAVEN_HOME
   ```

4. **Compile o projeto**:
   ```powershell
   mvn clean install
   ```

5. **Execute a aplicação**:
   ```powershell
   mvn spring-boot:run
   ```

## Solução de Problemas

### Erro de Política de Execução
```powershell
Set-ExecutionPolicy -ExecutionPolicy Bypass -Scope Process -Force
```

### Java não encontrado
Verifique se o JAVA_HOME está configurado:
```powershell
echo $env:JAVA_HOME
```

### Maven não encontrado
Verifique se o MAVEN_HOME está configurado:
```powershell
echo $env:MAVEN_HOME
```

### MySQL não inicia
```powershell
# Verificar se o serviço está instalado
sc query mysql

# Instalar como serviço
mysqld --install

# Iniciar serviço
net start mysql
```

## Configuração Manual (Alternativa)

Se preferir instalar manualmente:

### Java 11
```powershell
choco install openjdk11 -y
```

### Maven
```powershell
choco install maven -y
```

### MySQL
```powershell
choco install mysql -y
```

## Verificação Final

Execute estes comandos para verificar se tudo está funcionando:

```powershell
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar variáveis de ambiente
echo $env:JAVA_HOME
echo $env:MAVEN_HOME

# Testar compilação do projeto
mvn clean compile
```

## Suporte

Se encontrar problemas:

1. Verifique se executou o PowerShell como administrador
2. Confirme que tem conexão com a internet
3. Verifique se o antivírus não está bloqueando as instalações
4. Consulte os logs do Chocolatey em caso de erro

## Informações do Projeto

- **Versão Java**: 11
- **Framework**: Spring Boot 2.5.8
- **Build Tool**: Maven
- **Banco de Dados**: MySQL
- **Documentação**: Swagger/SpringFox 
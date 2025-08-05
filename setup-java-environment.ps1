# Script para configurar ambiente Java para o projeto MercadoCell
# Baseado na configuração do pom.xml que requer Java 11, Maven e MySQL

Write-Host "=== Configuração do Ambiente Java para MercadoCell ===" -ForegroundColor Green
Write-Host "Este script irá instalar e configurar:" -ForegroundColor Yellow
Write-Host "- Java 11 (OpenJDK)" -ForegroundColor Yellow
Write-Host "- Maven" -ForegroundColor Yellow
Write-Host "- MySQL" -ForegroundColor Yellow
Write-Host "- Chocolatey (se não estiver instalado)" -ForegroundColor Yellow
Write-Host ""

# Verificar se o Chocolatey está instalado
if (!(Get-Command choco -ErrorAction SilentlyContinue)) {
    Write-Host "Chocolatey não encontrado. Instalando..." -ForegroundColor Yellow
    Set-ExecutionPolicy Bypass -Scope Process -Force
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    Write-Host "Chocolatey instalado com sucesso!" -ForegroundColor Green
} else {
    Write-Host "Chocolatey já está instalado." -ForegroundColor Green
}

Write-Host ""
Write-Host "Atualizando Chocolatey..." -ForegroundColor Yellow
choco upgrade all -y

Write-Host ""
Write-Host "Instalando Java 11 (OpenJDK)..." -ForegroundColor Yellow
choco install openjdk11 -y

Write-Host ""
Write-Host "Instalando Maven..." -ForegroundColor Yellow
choco install maven -y

Write-Host ""
Write-Host "Instalando MySQL..." -ForegroundColor Yellow
choco install mysql -y

Write-Host ""
Write-Host "Configurando variáveis de ambiente..." -ForegroundColor Yellow

# Configurar JAVA_HOME
$javaPath = "C:\Program Files\OpenJDK\openjdk-11.0.21_9"
if (Test-Path $javaPath) {
    [Environment]::SetEnvironmentVariable("JAVA_HOME", $javaPath, "Machine")
    Write-Host "JAVA_HOME configurado: $javaPath" -ForegroundColor Green
} else {
    Write-Host "Aviso: Caminho padrão do Java não encontrado. Configure JAVA_HOME manualmente." -ForegroundColor Red
}

# Configurar MAVEN_HOME
$mavenPath = "C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.5"
if (Test-Path $mavenPath) {
    [Environment]::SetEnvironmentVariable("MAVEN_HOME", $mavenPath, "Machine")
    Write-Host "MAVEN_HOME configurado: $mavenPath" -ForegroundColor Green
} else {
    Write-Host "Aviso: Caminho padrão do Maven não encontrado. Configure MAVEN_HOME manualmente." -ForegroundColor Red
}

Write-Host ""
Write-Host "Verificando instalações..." -ForegroundColor Yellow

# Verificar Java
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "Java instalado: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "Erro ao verificar versão do Java" -ForegroundColor Red
}

# Verificar Maven
try {
    $mavenVersion = mvn -version | Select-String "Apache Maven"
    Write-Host "Maven instalado: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "Erro ao verificar versão do Maven" -ForegroundColor Red
}

Write-Host ""
Write-Host "=== Configuração Concluída ===" -ForegroundColor Green
Write-Host ""
Write-Host "Próximos passos:" -ForegroundColor Yellow
Write-Host "1. Reinicie o terminal/PowerShell para aplicar as variáveis de ambiente" -ForegroundColor White
Write-Host "2. Configure o MySQL conforme necessário" -ForegroundColor White
Write-Host "3. Execute 'mvn clean install' no diretório do projeto" -ForegroundColor White
Write-Host "4. Execute 'mvn spring-boot:run' para iniciar a aplicação" -ForegroundColor White
Write-Host ""
Write-Host "Para verificar se tudo está funcionando:" -ForegroundColor Yellow
Write-Host "- java -version" -ForegroundColor White
Write-Host "- mvn -version" -ForegroundColor White
Write-Host "- echo $env:JAVA_HOME" -ForegroundColor White
Write-Host "- echo $env:MAVEN_HOME" -ForegroundColor White 
# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem que já contém o Maven e o JDK 21 para compilar o projeto.
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker
COPY pom.xml .
# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte do projeto
COPY src ./src

# Compila a aplicação e gera o ficheiro .jar
# O -DskipTests acelera o build ao não executar os testes
RUN mvn package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem JRE (Java Runtime Environment) enxuta, otimizada para produção.
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o ficheiro .jar que foi gerado no estágio anterior (builder)
# O nome do .jar deve corresponder ao que está definido no seu pom.xml
COPY --from=builder /app/target/email-service-0.0.2-SNAPSHOT.jar ./app.jar

# Expõe a porta que a aplicação usa (definida no application.yml)
EXPOSE 8081

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usa uma imagem base que já tem o Java instalado.
# A tag "17-jdk-slim" é uma boa opção por ser menor.
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do contêiner.
# É o lugar onde a aplicação será executada.
WORKDIR /app

# Copia o arquivo JAR do seu computador para dentro do contêiner.
# Troque 'sua-aplicacao-0.0.1-SNAPSHOT.jar' pelo nome real do seu arquivo.
# O arquivo deve estar no diretório "target" ou "build/libs".
COPY target/salbum-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação Spring Boot usa.
# O padrão é 8080, mas verifique o seu arquivo application.properties se for diferente.
EXPOSE 8080

# Define o comando que será executado quando o contêiner iniciar.
# O "java -jar app.jar" inicia a aplicação.
ENTRYPOINT ["java", "-jar", "app.jar"]

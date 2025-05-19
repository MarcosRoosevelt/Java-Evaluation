# Java-Evaluation

Requisitos:
- Java JDK 11 ou inferior;
- Apache Tomcat 9 ou inferior;
- PostgreSQL 12 ou superior;
- IDE (recomendável o NetBeans pela integração com o servidor Apache. Em caso de uso do VSCode, utilizar a versão mais recente do Maven, a 3.9.9 através do comando `mvn clean package`);
- PrimeFaces 8.0 ou inferior;

Passo a passo para a execução:
- Criação do banco de dados;
- Execução do arquivo `script.sql`;
- Conectar ao banco PostgreSQL;
- Caso use o NetBeans, apenas importe o projeto e execute na aba "Services" no Server instalado Tomcat;
- Caso use outra IDE com Maven, ao compilar o comando citado anteriormente, uma pasta "target" será gerada. Acesse-a e copie o arquivo .war com o nome do projeto e o coloque no diretório /webapps da pasta do Tomcat;
- Depois, só acessar via navegador

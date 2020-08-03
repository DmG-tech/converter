# Конвертер валют

### Описание:

Конвертер валют с авторизацией пользователя. 
Данные о курсах валют и их список беруться с сайта ЦБРФ http://www.cbr.ru.
Так же доступна история пользовательских конвертаций с фильтром по дате.
После запуска приложение доступно по адресу: http://localhost:8080

#### Используемые технологии:
 - Java 13
 - Maven
 - Spring Boot 2.2.4
 - Spring Security
 - JSP, JSTL
 - PostgreSQL 12
 - HTML5/CSS(Bootstrap)
 - JavaScript

 #### Рекомендации для сборки и запуска проекта:
  - Создать на сервере БД пользователия **user** с паролем **password**
  или в файле **application.properties** заменить учетные данные пользователия
  ```sql
CREATE ROLE user WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'password';
```
  - Создать на сервере БД пустую базу данных **currency_converter**
```sql
CREATE DATABASE currency_converter
    WITH 
    OWNER = user
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```
  - Приложение при запуске автоматически инициирует БД и наполняет ее тестовыми данными. 
  Для отключения этой функции необходимо в файле **application.properties** 
  изменить значение переменной **spring.datasource.initialization-mode** с **always** на **never**
  - Запуск из среды разработки: запустить **CurrencyConverterApplication.java**
  - Запуск из командной строки из корневой папки **./mvnw spring-boot:run** (**mvnw spring-boot:run** для Windows)
  - Создание исполняемого **jar** командой  **./mvnw clean package** (**mvnw clean package** для Windows)
  - Запуск **jar** файла из командной строки:     перейти в папку с **jar** файлом и воспользоваться командой **java -jar currency-converter-0.0.1-SNAPSHOT.jar**
  - Для тестирования приложения можно воспользоваться данными пользователей из файла **src\main\resources\data.sql** или создать нового пользователя
  
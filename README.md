Необходимо реализовать REST API, которое взаимодействует с файловым хранилищем и предоставляет возможность получать доступ к файлам и истории загрузок.<br>
Сущности:<br>
User -> Integer id, String name, List<Event> events<br>
Event -> Integer id, User user, File file<br>
File -> Integer id, String name, String filePath<br>
Требования:<br>
Все CRUD операции для каждой из сущностей<br>
Придерживаться подхода MVC<br>
Для сборки проекта использовать Maven<br>
Для взаимодействия с БД - Hibernate<br>
Для конфигурирования Hibernate - аннотации<br>
Инициализация БД должна быть реализована с помощью flyway<br>
Взаимодействие с пользователем необходимо реализовать с помощью Postman (https://www.getpostman.com/)<br>
Технологии: Java, MySQL, Hibernate, HTTP, Servlets, Maven, Flyway, Swagger.<br>
https://editor.swagger.io/?url=https://raw.githubusercontent.com/crazym8nd/rest-api-mysql-servlet-no-spring-app/master/src/main/resources/openapi.yaml

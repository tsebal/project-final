## [REST API](http://localhost:8080/doc)

## Концепция:
- Spring Modulith
  - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
  - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
  - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```
- Есть 2 общие таблицы, на которых не fk
  - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
  - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем проверять

## Аналоги
- https://java-source.net/open-source/issue-trackers

## Тестирование
- https://habr.com/ru/articles/259055/

## List of completed tasks:
1) Understand the structure of the project (onboarding)...Done.
2) Removed social networks: vk, yandex....................Done.
3) The compromise-sensitive data has been moved to a separate
settings file where data loaded from OS environment variables...Done.
Use the variables to the right of the inequality in the application-secret.yaml file with the same names. 
Add these variables to your OS environment variables. For example:
   key: SPRING_DATASOURCE_USERNAME
   value: admin
4) Tests are written for all public methods of ProfileRestController...Done.
5) To create a Docker image, run from project root directory in the console:
     docker build -t project-final .
   To start the Docker container, run in the console:
     docker run --name jirarush -p 8080:8080 --env-file docker-env.list project-final
   Replace the values of the environment variables in the docker-env.list file, if necessary...Done.
6) Added automatic calculation of how long the task was in operation and testing. With unit tests...Done.
7) Added a docker-compose file to run the server container along with the database and nginx...Done. 
Use the 'docker compose up' command in the root directory of the project from the console to startup.

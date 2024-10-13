# Sonix Spring Boot Application

![Java](https://img.shields.io/badge/Java-17-blue.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)

## Описание

Это Spring Boot приложение, реализующее REST API с использованием HMAC SHA256 для генерации хешей.

### Техническое задание

Реализовать следующий функционал:

1. **REST контроллер**:
    - Принимает на вход:
        - `operationId`, являющийся частью URL запроса.
        - Параметры POST-запроса, полученные с HTML-формы.
    - Возвращает JSON-объект следующего вида:
      ```json
      {
        "status": "success",
        "result": [
          {
            "signature": "signature_value"
          }
        ]
      }
      ```
      Где `signature_value` — это HMAC SHA256 хеш от строки вида `name1=value1&name2=value2...`, где `nameN=valueN` — полученные из запроса имена и значения параметров формы, отсортированных по имени параметра.

2. **Предобработка**:
    - Реализовать проверку наличия HTTP-заголовка `Token`, значение которого должно соответствовать настройке из конфигурации.
    - В случае отсутствия заголовка или другого его значения возвращать код 403 HTTP.

### Документация API

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI документация**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
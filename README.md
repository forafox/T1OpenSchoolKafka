# Задание: Реализация системы мониторинга с использованием Spring Kafka

## Описание:

Создана система мониторинга, которая отслеживает работу различных методов приложения
с помощью Spring Kafka. Эта система включает в себя Producer для отправки метрик,
Consumer для их обработки и анализа, а также REST API для просмотра метрик.

## Стек

- Java
- Spring Boot
- Gradle
- PostgreSQL

## Особенности

- Доступно использование UI for Apache Kafka.
- Документация к API.(Интерактивная и docs/consumer-api-docs.json, docs/producer-api-docs.json)
- Для приложения разработана инструкция по сборке. Сборка выполняется с помощью Docker.
- Присутствует файл .env. Представленные данные являются публичными и используются только! при тестировании.

## Общая архитектура системы

### Producer Service:

1. Создан микросервис "Metrics Producer", который отслеживает и собирает метрики работы приложения
   и отправляет их в Kafka топики. (Данные не асинхронных методов```send-methodData-event```, Данные асинхронных
   методов ```send-asyncMethodData-event```)
2. Реализованы следующие API для взаимодействия с микросервисом:
    - POST /methodData: Отправка метрики данных о работе метода.

```json
{
  "executeDate": "2024-05-04T23:29:18.525Z",
  "methodName": "FilmServiceImpl.create(..)",
  "executeNanoTime": 49,
  "executeMilliTime": 49,
  "annotationType": "ASYNC"
}
```

### Consumer Service:

1. Создан микросервис "Metrics Consumer", который принимает метрики из Kafka топиков ```send-methodData-event```
   и ```send-asyncMethodData-event```.
2. Реализована обработка метрик и сохранение в базу данных для последующего анализа.

## Metrics Consumer API

- Получение списка всех метрик
    - GET /methodData
- Получение списка всех метрик для конкретного метода
    - GET /methodData/{methodName}
- Получение конкретной метрики по ее идентификатору
    - GET /methodData/stat/{methodName}

## Film Api (Перенесено из задания №1)

### Аннотация @Throw

Методы FilmServiceImpl, которые обрабатывают следующие эндпоинты:

- /film/id/{id}
- /film/title/{titleName}
- /film/producer/{producer}
- /film/genre/{genre}
- /film/country/{country}

### Аннотация @TrackTime

Методы FilmServiceImpl, которые обрабатывают следующие эндпоинты:

- /film
- /film/id/{id}
- /film/genre/{genre}
- /film/country/{country}

### Аннотация @TrackAsyncTime

Методы FilmServiceImpl, которые обрабатывают следующие эндпоинты:

- /film
- /film/title/{titleName}
- /film/producer/{producer}

## Ссылки (Доступны во время работы приложения)

- UI for Apache Kafka (http://localhost:8080)
- (Producer) Интерактивная Swagger документация (http://localhost:8081/swagger-ui/index.html#/)
- (Consumer) Интерактивная Swagger документация (http://localhost:8082/swagger-ui/index.html#/)

## Сборка

Для сборки приложения необходимо выполнить следующие действия.

1. Склонировать репозиторий:

   ```bash
   git clone https://github.com/forafox/T1OpenSchoolKafka
   cd T1OpenSchoolKafka
   ```

2. Выполнить build в директории приложения с помощью следующей команды:

   ```bash
   docker compose build 
   ```
3. Для запуска приложения необходимо выполнить следующую команду:

   ```bash
   docker compose up
   ```

- Пункты 3 и 4 **можно** объединить в следующую команду:

    ```bash
    docker compose up --build
    ```

### Критерии оценки работы:

1. Микросервисы должны успешно обмениваться данными через Kafka и обеспечивать сбор и анализ метрик
   работы приложения.
2. API должно быть хорошо спроектировано, документировано и соответствовать принципам RESTful.
3. Система должна быть масштабируемой, надежной и обладать высокой производительностью.
4. Должна быть предоставлена документация, объясняющая архитектуру системы, конфигурацию Kafka,
   а также инструкции по запуску и использованию системы.

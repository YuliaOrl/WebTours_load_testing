## :green_book: Описание проекта

Проект содержит скрипты для проведения нагрузочного тестирования приложения WebTours с использованием инструментов <a target="_blank" href="https://github.com/YuliaOrl/WebTours_load_testing/tree/master/Jmeter/TestPlans">*JMeter*</a> и <a target="_blank" href="https://github.com/YuliaOrl/WebTours_load_testing/tree/master/Gatling/Scenarios">*Gatling*</a>. 

WebTours представляет собой веб-приложение онлайн-сервиса бронирования авиаперелётов.

### :open_file_folder: Структура проекта

```
Documents/
├── Профиль       # Профиль нагрузки
├── МНТ           # Методика нагрузочного тестирования
└── Отчет         # Отчет о проведенных тестах

Gatling/
├── Parameters    # Тестовые данные для сценариев Gatling
├── Results       # html-отчеты выполнения тестов
└── Scenarios     # Проект Gatling со всеми сценариями

Jmeter/
├── Parameters    # Тестовые данные для сценариев 
└── TestPlans     # Планы тестирования в JMeter
```

### :computer: Использованные технологии

- Gatling (Java)
- Apache JMeter
- Мониторинг метрик в Grafana
- Хранение и обработка метрик с использованием Prometheus
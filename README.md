# config manager service

Сервис для получения файлов из git репозитория

## Настройки:

- config.manager.git.username - логин
- config.manager.git.password - пароль
- config.manager.server.git.uri - адрес репозитория в формате *.git
- config.manager.server.workDir - рабочая директория куда будет склонирован репозиторий с конфигами

Пример запроса на получение файла:
http://localhost:8080/api/configs/{ApplicationName}/{Stand}/{Environment}/README
     
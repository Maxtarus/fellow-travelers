# Выпускной проект Java-школы от SberTech "Сервис для автоматизации поиска автомобильных попутчиков":

## Перед запуском проекта на своём ПК необходимо:

### 1. Склонировать репозиторий c помошью команды git clone

```
git clone https://github.com/Maxtarus/fellow-travelers.git
```

### 2. Запустить сервисы, определённые в файле docker-compose.yml, из терминала директории проекта
```
docker-compose up
```
### 3. Открыть в браузере <a href="http://localhost:8080">страницу авторизации<a>. В файле data.sql можно найти информацию о сущестующих пользователях системы.

## Карткое руководство пользователя
В проекте есть три типа ролей - админситратор, пассажир и водитель. Главная страница - страница авторизации. Если пользователя еще нет в системе, есть возможность зарегистрироваться. Если не указывать при регистрациии, что вы - водитель, вы зарегистрируетесь как пассажир, иначе у вас будет две роли в системе - водитель и пассажир. Возможность выбора роли предусмотрена после успешной аутентификации.
1. Администратор в системе всего один. Чтобы зайти как администратор, необходимо ввести email "admin@mail.ru" и пароль "111". Администратор может созадавать новых пользователей, изменять информацию о них (в том числе и информацию о себе) и удалять пользователей.
2. Чтобы зайти как водитель, можно ввести, например, такой email и пароль: "starmax@yandex.ru", "222". Далее необходимо продолжить как водитель. Водитель может создавать поездки, изменять информацию о них и удалять, принимать или отклонять заявки пассажиров на них, просматривать историю поездок, в том числе приблизительный маршрут на карте мира.
3. Чтобы зайти как водитель, можно ввести, например, такой email и пароль: "starmax@yandex.ru", "222". Далее необходимо продолжить как пассажир. Пассажир может подавать заявки на доступные поездки, удалять их, просматривать свои заявки и историю поездок, выставлять оценку водителю за поездку.

Студент: `Старостин Максим Андреевич`

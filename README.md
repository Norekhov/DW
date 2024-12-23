# **1. Содержание проекта:**
- Командная работа, в которой реализована бэкенд-часть сайта для перепродажи вещей.
- Представлет собой веб-приложение, которое предоставляет пользователям возможность создавать, просматривать и управлять объявлениями. Пользователи могут регистрироваться, входить в систему, оставлять комментарии к объявлениям и загружать аватары.

# **2. Стек технологий:**
* Java: Основной язык программирования.
* Spring Boot.
* Spring Data JPA.
* Spring Безопасность
* PostgreSQL.
* Maven.

# **3. Разработчики:**
1. [Евгений Чехович.](https://github.com/Chex4ever)
2. [Никита Орехов](https://github.com/norekhov).

# **4. Установка и запуск приложения:**
1. Копируйте репозиторий: https://github.com/Norekhov/DW
2. В IntelliJ IDEA запустите новый проект, вставив ссылку из п.1.
3. Запустите файл HomeworkApplication.java.
4. Откройте приложение Docker Desktop.
5. Копируйте команду (docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21) и вставьте ее в командную строку.
6. Перейдите по адресу http://localhost:3000.

# **5. Возможности проекта:**
___

# *Регистрация*
* /register
# *Авторизация*
* /login
# *Пользователь*
* /users/me - Получение информации об авторизованном пользователе
* /users/me - Обновление информации об авторизованном пользователе
* /set_password - Обновление пароля
* /avatars/{avatarId} - Добавление аватара
* /me/image - Обновление аватара авторизованного пользователя
# *Объявления*
* /ads - Получение всех объявлений
* /ads - Добавление объявления
* /ads/{id} - Получение информации об объявлении
* /ads/{id} - Обновление информации об объявлении
* /ads/{id} - Удаление объявления
* /ads/me - Получение объявлений авторизованного пользователя
* /images/{adImageFilename} - Добавление изображения в объявление
* /ads/{adId}/image - Обновление изображения объявления
# *Комментарии*
* /ads/{adId}/comments - Добавление комментария к объявлению
* /ads/{adId}/comments - Получение комментариев объявления
* /ads/{adId}/comments/{commentId} - Обновление комментария
* /ads/{adId}/comments/{commentId} - Удаление комментария

# Servlets

## 1. users
 1.1 Реализовать приложения для работы с пользователем
 Стартовая страница - аутентификация(либо обычный пользователь, либо рут). 
 Страница создания пользователя, если его нет в БД. 
 Страница редактирования.
 Переход после на страницу редактирования(просмотра) со своими данными(если рут, то все пользователи из БД доступны)
 1.2 Инструменты:
 - servlets
 - filter
 - session
 - jsp
 - jstl
 - js
 - bootstrap
 - jquery
 - ajax
 - mockito for junit test
 
## 2. json
 1. Создайте сервлет, который будет принимать JSON объект.
 
 Данные нужно хранить в памяти в карте ConcurrencyHashMap.
 
 2. Через JQuery создайте событие на нажатие кнопки, которые будет создавать вызов на сервер.
 
 $.ajax({
    type: "POST",
    url: url,
    data: data,
    success: success,
    dataType: dataType
 });
 
 3. Когда данные пришли на клиент, нужно добавить новую запись в таблицу.

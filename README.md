# E-school

1. Чтобы скачать проект с github запускаем команду:
   ```git clone https://github.com/bagdwr/E-school.git```


2. В application.properties есть url базы данных
   ```jdbc:mysql://localhost:3306/testDB?useUnicode=true&serverTimezone=UTC```

   Вам необходимо запустить xampp/wampp/mampp запустить сервер и в phpmyadmin создать базу данных с названием **testDB**


3. Запускаете программу  


4. Ждём, когда запуститься сервер. После запуска сервера, заходим в браузере по адресу:
   ```http://localhost:8080/login``` 


6. После, как вы перейдете по этой ссылке, у вас выйдет форма авторизации. 
    Здесь вы можете ввести:<br>
    логин: user <br>
    пароль: user <br>
    Или можете пройти регистрацию нажав на кнопку **Зарегистрироваться**

7. Дальше вы проходите на страницу профиля, где вы увидите таблицу студентов. Вы можете создать студента, редактировать и удалить.

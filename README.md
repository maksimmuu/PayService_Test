# PayService_Test
В данном Pet-project имитируются международные денежные переводы.
Это Spring Boot приложение с использование технологий: Maven, Spring Framework, Hibernate, PostgreSQL + реализован unit тест с использованием jUnit, Mockito.
В приложении используются три класса-сущности:
1) Account (table name = "account") - класс описыает аккаунт отправителя/получателя.
2) Log (table name = "transferlogs") - класс для формирования логов денежных переводов и добавления в БД.
3) PermissionToTransfer (table name = "permissionToTransfer") - класс для хранения информации о блокировке аккаунта.

Управление аккаунтами происходит с использованием объекта класса AccountManageService, в который с помощью Spring внедрены бины класса AccountRepository, AccountPermissionRepository.

В классе AccountManageService для управления аккаунтами реализованы следующие методы:

accountManageService.createAccount("Paul", 300.0, "Spain"); // создание аккаунта
accountManageService.deleteAccount(9); // удаление аккаунта
accountManageService.blockAccount(10); // блокировка аккаунта по id
accountManageService.unblockAccount(10); // разблокировка аккаунта по id
accountManageService.getAllAccounts(); // вывод всех аккаунтов
accountManageService.sumSendAllById(2); // общая сумма всех переводов по id
accountManageService.getLogBySenderId(1); // все логи переводов по id

Перевод осуществляется с использованием объекте класса TransferService при помощью метода transferMoney(), помеченным аннотацией @Transactional, в параметрах необходимо указать id отправителя, id получателя, сумму перевода.

Логика работы метода transferMoney():
1) Проверяется есть ли блокировка на аккаунте отправителе, если блокировка есть перевод не осуществляется, запись о невозможности перевода добавляется в таблицу логов transferlogs.
2) Проверяются страны отправителя и получателя, если перевод осуществляется в другую страну то с отправителя дополнительно взымается комиссия 5% от суммы перевода, если перевод внутри одной страны то комиссия не взымается.
3) Все действия логируются в консоль с помощью объекта класса Logger и в таблицу логов transferlogs в БД с помщью объекта класса LogRepository.

Все взаимодействия с БД PostgreSQL происходят с помощью ORM фреймворка Hibernate.


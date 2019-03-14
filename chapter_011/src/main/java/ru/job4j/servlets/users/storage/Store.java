package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;

public interface Store {
    User add(User user);
    User delete(User user);
    User update(User user);
}

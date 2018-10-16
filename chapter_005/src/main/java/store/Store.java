package store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    static class User {
        final int id;
        final String name;
        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append(id)
                    .append(" ")
                    .append(name)
                    .toString();
        }
    }

    public Info diff(List<User> previous, List<User> current) {
        List<User> added = new ArrayList<>();
        List<User> changed = new ArrayList<>();
        List<User> deleted = new ArrayList<>();
        Map<Integer, String> temp = new HashMap<>();
        for (User user : current) {
            temp.put(user.id, user.name);
        }
        for (User user : previous)  {
            if (temp.putIfAbsent(user.id, user.name) == null) {
                deleted.add(new User(user.id, user.name));
            } else {
                if (!user.name.equals(temp.get(user.id))) {
                    changed.add(new User(user.id, user.name));
                }
            }
        }
        temp = new HashMap<>();
        for (User user : previous) {
            temp.put(user.id, user.name);
        }
        for (User user : current) {
            if (temp.putIfAbsent(user.id, user.name) == null) {
                added.add(new User(user.id, user.name));
            }
        }
        return new Info(added, changed, deleted);
    }
}

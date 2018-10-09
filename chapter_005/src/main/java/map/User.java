package map;

import java.util.Calendar;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(final String name, final int children, final Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }
}

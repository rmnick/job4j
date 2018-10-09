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

    public int hashCode() {
        /*int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + Integer.valueOf(children).hashCode();
        result = 31 * result + birthday.hashCode();
        return result;*/
        return super.hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.children == user.getChildren()
                && this.name.equals(user.getName())
                && this.birthday.equals(user.getBirthday());
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

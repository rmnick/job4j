package generic.store;

import generic.SimpleArray;

import java.util.Iterator;

public class UserStore<T extends Base> extends AbstractStore<T> {
    private SimpleArray<User> array;

    public UserStore(int length) {
        array = new SimpleArray<>(length);
    }

    @Override
    public void add(T model) {
       array.add((User) model);
    }

    @Override
     public boolean replace(String id, T model) {
        boolean result = false;
        Iterator<User> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user != null && user.getId().equals(id)) {
                array.set(index, (User) model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        User user = null;
        Iterator<User> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            User some = iterator.next();
            if (some != null && some.getId().equals(id)) {
                user = array.get(index);
                break;
            }
            index++;
        }
        return (T) user;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Iterator<User> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user != null && user.getId().equals(id)) {
                array.delete(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }
}

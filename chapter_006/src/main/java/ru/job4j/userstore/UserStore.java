package ru.job4j.userstore;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private Map<Integer, User> store;

    public UserStore() {
        this.store = new HashMap();
    }

    public UserStore(int capacity) {
        this.store = new HashMap(capacity);
    }

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!store.containsKey(user.getId())) {
            this.store.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
       return this.store.remove(user.getId()) != null ? true : false;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (store.containsKey(user.getId())) {
            this.store.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int inId, int toId, int amount) {
        boolean result = false;
        if (this.store.containsKey(inId) && this.store.containsKey(toId)) {
            User userGiver = this.store.get(inId);
            if (userGiver.getAmount() >= amount) {
                User userTaker = this.store.get(toId);
                userTaker.setAmount(userTaker.getAmount() + amount);
                userGiver.setAmount(userGiver.getAmount() - amount);
                result = true;
            }
        }
        return result;
    }

    public static class User {
        private final int id;
        private int amount;


        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return this.id;
        }

        public int getAmount() {
            return this.amount;
        }

        private void setAmount(int amount) {
            this.amount = amount;
        }
    }

    /**
     * method for testing application
     * @return int
     */
    public synchronized int size() {
        return this.store.size();
    }
}

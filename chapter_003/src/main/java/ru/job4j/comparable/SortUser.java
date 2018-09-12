package ru.job4j.comparable;

import java.util.*;

public class SortUser {
    public Set<User> sort(final List<User> list) {
        return new TreeSet<User>(list);
    }

    public List<User> sortNameLength(final List<User> list) {
        List<User> result = new ArrayList<>();
        result.addAll(list);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return result;
    }

    public List<User> sortByAllFields(final List<User> list) {
        List<User> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        arrayList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result
                        : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return arrayList;
    }
}

package store;

import java.util.List;

public class Info {
    private final List<Store.User> addedUsers;
    private final List<Store.User> changedUsers;
    private final List<Store.User> deletedUsers;

    public Info(List<Store.User> addedUsers, List<Store.User> changedUsers, List<Store.User> deletedUsers) {
        this.addedUsers = addedUsers;
        this.changedUsers = changedUsers;
        this.deletedUsers = deletedUsers;
    }

    public List<Store.User> getAddedUsers() {
        return addedUsers;
    }

    public List<Store.User> getChangedUsers() {
        return changedUsers;
    }

    public List<Store.User> getDeletedUsers() {
        return deletedUsers;
    }
}

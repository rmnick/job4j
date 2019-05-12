package users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.job4j.servlets.users.controller.UsersController;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.storage.DbStore;
import ru.job4j.servlets.users.storage.IStore;

import ru.job4j.servlets.users.storage.StoreStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * using MemoryIStore instead DbStore
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DbStore.class)

public class UserDeleteTest {
    @Test
    public void whenDeleteUserThenRemoveLastOneFromStoreStub() {
        IStore<User> store = new StoreStub();
        PowerMockito.mockStatic(DbStore.class);
        when(DbStore.getInstance()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        User user = new User("test", "test", "test", "test", "test", "test");
        user.setId("0");
        store.add(user);
        user.setId("1");
        store.add(user);

        when(req.getParameter("id")).thenReturn("0");
        new UsersController().doPost(req, resp);

        assertThat(store.getAll().size(), is(1));
    }
}

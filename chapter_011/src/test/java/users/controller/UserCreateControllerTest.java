package users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.job4j.servlets.users.controller.StubSession;
import ru.job4j.servlets.users.controller.UserCreate;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.storage.DbStore;
import ru.job4j.servlets.users.storage.IStore;

import ru.job4j.servlets.users.storage.StoreStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * using MemoryIStore instead DbStore
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DbStore.class)

public class UserCreateControllerTest {
    @Test
    public void whenCreateUserThenAddLastOneToListInStoreStub() {
        IStore<User> store = new StoreStub();
        PowerMockito.mockStatic(DbStore.class);
        when(DbStore.getInstance()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Nick");
        when(req.getParameter("login")).thenReturn("nick");
        when(req.getParameter("email")).thenReturn("mail@ya.ru");
        HttpSession session = new StubSession();
        when(req.getSession(false)).thenReturn(session);
        new UserCreate().doPost(req, resp);

        assertThat(store.getAll().iterator().next().getLogin(), is("nick"));
        assertThat(store.getAll().iterator().next().getEmail(), is("mail@ya.ru"));
        assertThat(store.getAll().iterator().next().getName(), is("Nick"));
    }
}

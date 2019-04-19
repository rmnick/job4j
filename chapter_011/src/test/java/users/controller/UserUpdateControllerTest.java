package users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.users.controller.StubSession;
import ru.job4j.servlets.users.controller.UserCreate;
import ru.job4j.servlets.users.controller.UserUpdate;
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
public class UserUpdateControllerTest {
    @Test
    public void whenUpdateUserThenChangeSomeAttributes() {
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

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("Mike");
        when(req.getParameter("login")).thenReturn("mike");
        when(req.getParameter("email")).thenReturn("mail@ya.ru");
        new UserUpdate().doPost(req, resp);

        assertThat(store.getAll().iterator().next().getLogin(), is("mike"));
        assertThat(store.getAll().iterator().next().getEmail(), is("mail@ya.ru"));
        assertThat(store.getAll().iterator().next().getName(), is("Mike"));
    }
}

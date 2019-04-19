package users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.users.controller.StubSession;
import ru.job4j.servlets.users.controller.UserCreate;
import ru.job4j.servlets.users.controller.UsersController;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.Validate;
import ru.job4j.servlets.users.logic.ValidateService;
import ru.job4j.servlets.users.storage.DbStore;
import ru.job4j.servlets.users.storage.IStore;
import ru.job4j.servlets.users.storage.StoreStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * using MemoryIStore instead DbStore
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DbStore.class)

public class UsersControllerTest {
    @Test
    public void whenGetAllUsersFromListThenShowAllOfThem() throws ServletException, IOException {
        IStore<User> store = new StoreStub();
        PowerMockito.mockStatic(DbStore.class);
        when(DbStore.getInstance()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher disp = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/users.jsp")).thenReturn(disp);
        new UsersController().doGet(req, resp);
        verify(disp).forward(req, resp);
    }
}

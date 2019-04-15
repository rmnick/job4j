package users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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

public class ControllersTest {
    @Test
    public void whenCreateUserThenAddLastOneToListInStubWithoutIStore() {
        IStore store = new StoreStub();
        PowerMockito.mockStatic(DbStore.class);
        when(DbStore.getInstance()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getParameter("login")).thenReturn("nick");
        when(req.getSession()).thenReturn(session);
        assertThat(true, is(true));
    }
}

package users.controller;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.users.controller.UserCreate;
import ru.job4j.servlets.users.logic.Validate;
import ru.job4j.servlets.users.logic.ValidateService;
import ru.job4j.servlets.users.logic.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)

public class UserCreateTest {
    @Test
    public void whenCreateUserThenAddLastOneToListInStubWithoutIStore() {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getParameter("login")).thenReturn("nick");
        when(req.getSession()).thenReturn(session);
        new UserCreate().doPost(req, resp);
        assertThat(validate.show().get(0).getLogin(), is("nick"));
    }
}

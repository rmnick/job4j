package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WiseOracleServerTest {
    public static final String LN = System.lineSeparator();

    public void test(String input, String output) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Socket socket = mock(Socket.class);
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            WiseOracleServer server = new WiseOracleServer(socket);
            server.start();
            assertThat(out.toString(), is(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenByeThenExitWithGoodbye() {
        this.test("bye", String.format("Bye, my friend%s%s", LN, LN));
    }

    @Test
    public void whenHelloAndByeThenGreetingAndExitWithGoodbye() {
        this.test(String.format("Hello, dude%sbye", LN, LN),
                String.format("Hello, dear friend, I'm a oracle.%s%sBye, my friend%s%s", LN, LN, LN, LN));
    }
}

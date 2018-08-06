package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/07.
 */
public class DummyBotTest {
    @Test
    public void whenGreetBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Привет, Бот."), is("Привет, умник."));
    }
    @Test
    public void whenByeBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Пока."), is("До скорой встречи."));
    }
    @Test
    public void whenUnknowBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Как дел?"), is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}

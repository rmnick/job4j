package ru.job4j.condition;

/**
 * DummyBot.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/07.
 */

public class DummyBot {
    /**
     * it answers for questions.
     * @param question Questions from client.
     * @return answer.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}

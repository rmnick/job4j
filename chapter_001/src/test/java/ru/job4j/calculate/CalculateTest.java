package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Rodionov Nick (r.m.nick@yandex.ru)
* @version 1.0
* @since 2018/07/29
*/
public class CalculateTest {
  /**
  * Test echo.
  */ @Test
  public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Nick Rodionov";
    String expect = "Echo, echo, echo : Nick Rodionov"; 
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
  }
 
}
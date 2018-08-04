package ru.job4j.converter;
/**
 * currency converter
 *
 * @author Rodionov Nick (r.m.nick@yandex.ru)
 * @version 1.0
 * @since 2018/08/04
 */
public class Converter {
    /**
     * convert rubles to euros
     * @param value rubles
     * @return euros
     */
    public int rublesToEuros(int value) {
        return value/70;
    }
    /**
     * convert rubles to euros
     * @param value rubles
     * @return dollars
     */
    public int rublesToDollars(int value) {
        return value/60;
    }
    /**
     * convert euros to rubles
     * @param value euros
     * @return rubles
     */
    public int eurosToRubles(int value) {
        return value * 70;
    }
    /**
     * convert dollars to rubles
     * @param value dollars
     * @return rubles
     */
    public int dollarsToRubles(int value) {
        return value * 60;
    }
}

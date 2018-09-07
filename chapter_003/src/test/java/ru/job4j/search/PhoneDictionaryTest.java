package ru.job4j.search;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Nick", "Rodionov", "667676", "Magnitogorsk")
        );
        List<Person> persons = phones.find("Ni");
        assertThat(persons.iterator().next().getSurname(), is("Rodionov"));
    }
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Nick", "Rodionov", "667676", "Magnitogorsk")
        );
        phones.add(
                new Person("Sergei", "Haritonov", "987787", "Moscow")
        );
        List<Person> persons = phones.find("778");
        assertThat(persons.iterator().next().getSurname(), is("Haritonov"));
    }
    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Egor", "Ivanov", "9855687", "Moscow")
        );
        phones.add(
                new Person("Nick", "Rodionov", "667676", "Magnitogorsk")
        );
        phones.add(
                new Person("Sergei", "Haritonov", "987787", "Moscow")
        );
        List<Person> persons = phones.find("sk");
        assertThat(persons.get(0).getName() + " " + persons.get(1).getName() + " " + persons.size(),
                is("Petr Nick 2"));
    }
}

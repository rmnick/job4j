package map;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void printMap() {
        Object dummy = new Object();
        Map<User, Object> hashMap = new HashMap<>();
        Calendar birthDate = new GregorianCalendar(1984, 11, 05);
        User userOne = new User("Sergei", 2, birthDate);
        User userTwo = new User("Sergei", 2, birthDate);
        assertThat(userOne.equals(userTwo), is(true));
        assertThat(userTwo.equals(userOne), is(true));
        hashMap.put(userOne, dummy);
        hashMap.put(userTwo, dummy);
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(true));
        System.out.println(hashMap);
    }
}

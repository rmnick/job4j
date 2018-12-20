package ru.job4j.magnit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

public class XmlUsage {

    @XmlRootElement
    public static class Entries {
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> entry) {
            this.entry = entry;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }

    @XmlRootElement
    public static class Field {
        private int field;

        public Field() {
        }

        public Field(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }


    public static void main(String[] args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(Arrays.asList(new Field(1), new Field(2))),
                System.out
        );
    }
}
package ru.job4j.magnit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
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


    public void createXML(Connection connection, File file) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(createFields(connection)), file
        );
    }

    public List<Field> createFields(Connection connection) {
        List<Field> fields = new ArrayList<>();
        String select = "select field from entries";
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            System.out.println("extract entries from table");
            try (ResultSet rs = st.executeQuery(select)) {
                while (rs.next()) {
                    //System.out.println(rs.getInt(1));
                    fields.add(new Field(rs.getInt(1)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("start marshalling");
        return fields;
    }
}
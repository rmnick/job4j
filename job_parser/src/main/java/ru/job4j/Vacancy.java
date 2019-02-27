package ru.job4j;

import java.time.LocalDateTime;

public class Vacancy {
        private final String name;
        private final String url;
        private final String description;
        private final LocalDateTime date;

        public Vacancy(final String name, final String url, final String description, final LocalDateTime date) {
            this.name = name;
            this.url = url;
            this.description = description;
            this.date = date;
        }

        public String getName() {
            return this.name;
        }

        public String getUrl() {
            return this.url;
        }

        public String getDescription() {
            return this.description;
        }

        public LocalDateTime getDate() {
        return this.date;
        }

    @Override
        public String toString() {
            return String.format("name : %s%surl : %s%sdescription : %s", this.name, System.lineSeparator(), this.url, System.lineSeparator(), description);
        }
}

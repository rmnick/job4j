package ru.job4j;

public class Vacancy {
        private final String name;
        private final String url;
        private final String description;

        public Vacancy(final String name, final String url, final String description) {
            this.name = name;
            this.url = url;
            this.description = description;
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

        @Override
        public String toString() {
            return String.format("name : %s%surl : %s%sdescription : %s", this.name, System.lineSeparator(), this.url, System.lineSeparator(), description);
        }
}

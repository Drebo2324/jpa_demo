package com.jpaTemplate.jpaTraining;

import com.jpaTemplate.jpaTraining.domain.Author;
import com.jpaTemplate.jpaTraining.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}


    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Hugh Mungus")
                .age(69)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("IC Weiner")
                .age(85)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Mc Luvin")
                .age(23)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("123abc").title("Book 1").author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("213abc").title("Book 2").author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("321abc").title("Book 3").author(author)
                .build();
    }
}

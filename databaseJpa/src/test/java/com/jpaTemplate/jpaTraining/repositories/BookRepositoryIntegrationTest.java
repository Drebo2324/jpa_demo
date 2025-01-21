
package com.jpaTemplate.jpaTraining.repositories;

import com.jpaTemplate.jpaTraining.TestDataUtil;
import com.jpaTemplate.jpaTraining.domain.Author;
import com.jpaTemplate.jpaTraining.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testMultipleBooksCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();

        Book book1 = TestDataUtil.createTestBookA(authorA);
        underTest.save(book1);

        Book book2 = TestDataUtil.createTestBookB(authorA);
        underTest.save(book2);

        Book book3 = TestDataUtil.createTestBookC(authorA);
        underTest.save(book3);

        Iterable<Book> result = underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(book1, book2, book3);

    }

    @Test
    public void testBookCanBeUpdated(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(author1);
        underTest.save(bookA);

        bookA.setTitle("If You Don't Hawk Tuah, I Don't Want To Talk To Ya: Volume 1");
        underTest.save(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testBookCanBeDeleted(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Book book1 = TestDataUtil.createTestBookA(author1);

        underTest.save(book1);
        underTest.deleteById(book1.getIsbn());

        Optional<Book> result = underTest.findById(book1.getIsbn());
        assertThat(result).isEmpty();
    }
}


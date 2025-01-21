
package com.jpaTemplate.jpaTraining.repositories;

import com.jpaTemplate.jpaTraining.TestDataUtil;
import com.jpaTemplate.jpaTraining.domain.Author;
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
//Cleans context
//cleaning db so no duplicate
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    //Autowired explicitly needed in test
    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    //in jpa create is implemented as save, and findone implemented as findbyid
    @Test
    public void testAuthorCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);


    }

    @Test
    public void testMultipleAuthorsCreatedAndRecalled() {

        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        authorA.setAge(99);
        underTest.save(authorA);
        //save method used to create and update

        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testAuthorCanBeDeleted(){
        Author author1 = TestDataUtil.createTestAuthorA();
        underTest.save(author1);
        underTest.deleteById(author1.getId());

        Optional<Author> result = underTest.findById(author1.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetAuthorsWithAgeLessThan(){
        Author author1 = TestDataUtil.createTestAuthorA();
        underTest.save(author1);
        Author author2 = TestDataUtil.createTestAuthorB();
        underTest.save(author2);
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author3);

        Iterable<Author>result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(author3);
    }

    @Test
    public void testGetAuthorsWithAgeGreaterThan(){
        Author author1 = TestDataUtil.createTestAuthorA();
        underTest.save(author1);
        Author author2 = TestDataUtil.createTestAuthorB();
        underTest.save(author2);
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author3);

        //using method name that goes against JPA convention
        //findAuthorsAgeGreaterThan
        //Must use HQL if method name not picked up by JPA
        Iterable<Author>result = underTest.findAuthorsAgeGreaterThan(50);
        assertThat(result).containsExactly(author1, author2);
    }
}


package com.jpaTemplate.jpaTraining.repositories;

import com.jpaTemplate.jpaTraining.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    //Query Method Derivation -> Must follow conventions to work
    Iterable<Author> ageLessThan(int age);

    //Custom Query with HQL due to method name not being picked up by JPA
    //For more advanced query
    @Query("SELECT a from Author a where a.age > ?1")
    Iterable<Author> findAuthorsAgeGreaterThan(int age);
}

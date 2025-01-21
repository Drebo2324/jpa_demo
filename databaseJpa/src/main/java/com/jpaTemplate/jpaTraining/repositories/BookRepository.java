package com.jpaTemplate.jpaTraining.repositories;

import com.jpaTemplate.jpaTraining.domain.Author;
import com.jpaTemplate.jpaTraining.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}

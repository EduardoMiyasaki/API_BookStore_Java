package com.bookstore.jpa.repositories;

import com.bookstore.jpa.models.BookStoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookStoreModel , UUID> {

    //<> aqui dentro está o nome da entidade que vai ser pego
    // Repository é um mecanismo que facilita o manipulamento dos dados

    BookStoreModel findBookStoreModelByTitle(String titulo);

    @Query(value = "SELECT * FROM tb_book INNER JOIN tb_publisher WHERE publisher _id = :id" , nativeQuery = true)
    List<BookStoreModel> finBooksByPublisherId(@Param("id") UUID id);
    // @Param serve para ser o aó contrario de PathVariable onde o valor dito no
    // parametro da funcao e jogado dentro da querry

}

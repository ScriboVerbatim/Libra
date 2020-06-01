package com.example.library.repositories;

import com.example.library.models.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType,String> {
}

package com.example.library.repositories;

import com.example.library.models.BookUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookUnitRepository extends JpaRepository<BookUnit,Integer> {

}

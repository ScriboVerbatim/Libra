package com.example.library.controllers;

import com.example.library.exceptions.BookTypeNotFoundException;
import com.example.library.exceptions.BookUnitNotFoundException;
import com.example.library.models.BookType;
import com.example.library.models.BookUnit;
import com.example.library.models.Tag;
import com.example.library.models.dto.BookTypeDTO;
import com.example.library.models.dto.BookUnitDTO;
import com.example.library.repositories.BookTypeRepository;
import com.example.library.repositories.BookUnitRepository;
import com.example.library.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api")
public class BookController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    BookTypeRepository bookTypeRepository;
    @Autowired
    BookUnitRepository bookUnitRepository;
    @Autowired
    TagRepository tagRepository;

    //book api
    @GetMapping("books")
    public List<BookTypeDTO> getAllBooks() {
        LOGGER.info("/books called");
        List<BookTypeDTO> allBooks = new ArrayList<>();
        int ctr=0;
        for (BookType bookType : bookTypeRepository.findAll()) {
            allBooks.add(new BookTypeDTO(bookType));
            ++ctr;
        }
        LOGGER.info("Found "+ ctr +" book types");
        return allBooks;
    }

    @PostMapping("books")
    public void addBooks(@RequestBody List<BookType> bookTypes) {
        LOGGER.info("Received "+bookTypes.size()+" books");
        for(BookType bookType:bookTypes)
        {
            BookType bookType1 = new BookType(bookType.getIsbn(), bookType.getTitle(), bookType.getAuthor(), bookType.getSubject());
            bookTypeRepository.save(bookType1);
            for (BookUnit bookUnit : bookType.getBookUnits()) {
                bookUnit.setBookType(bookType1);
                bookUnitRepository.save(bookUnit);
            }
            for (Tag tag : bookType.getTags()) {
                tag.setTagName(tag.getTagName().toLowerCase());
                tag.getBookTypes().add(bookType1);
                tagRepository.save(tag);
            }
        }
    }

    @DeleteMapping("books/unit/{id}")
    public boolean removeABook(@PathVariable Integer id) {
        BookUnit bookUnit = bookUnitRepository.findById(id).orElseThrow(() -> new BookUnitNotFoundException(id));
        if (!bookUnit.isRemoved()) {
            bookUnit.setRemoved(true);
            bookUnitRepository.save(bookUnit);
            //bookUnit.get().setDateOfRemoval(date);
            return true;
        }
        return false;
    }

    @GetMapping("books/unit/{id}")
    public BookUnitDTO getBookById(@PathVariable Integer id) {
        LOGGER.info("books/unit/{id} called with id: " + id);
        return new BookUnitDTO(bookUnitRepository.findById(id).orElseThrow(() -> new BookUnitNotFoundException(id)));
    }

//    @GetMapping("books/{isbn}")
//    public void addATag(@PathVariable String isbn)
//    {
//        LOGGER.info("books/{isbn} called with isbn: "+isbn);
//
//    }

    //http://localhost:8080/books/{isbn}/addtags?tags=tag1,tag2,tag3
    @GetMapping("books/{isbn}/addtags")
    public void addATag(@PathVariable String isbn, @RequestParam(name = "tags") List<String> tagNames)
    {
        LOGGER.info("books/{isbn} called with isbn: "+isbn);
        BookType bookType = bookTypeRepository.findById(isbn).orElseThrow(()-> new BookTypeNotFoundException(isbn));
        for (String tagName : tagNames)
        {
                Tag tag = new Tag(tagName);
                tag.getBookTypes().add(bookType);
                bookType.getTags().add(tag);
                tagRepository.save(tag);
                bookTypeRepository.save(bookType);
        }
    }

    @GetMapping("books/{isbn}")
    public BookTypeDTO getBookByIsbn(@PathVariable String isbn)
    {
        LOGGER.info("books/{isbn} called with isbn: "+isbn);
        return new BookTypeDTO(bookTypeRepository.findById(isbn).orElseThrow(()->new BookTypeNotFoundException(isbn)));
    }



}
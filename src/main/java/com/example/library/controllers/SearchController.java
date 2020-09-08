package com.example.library.controllers;

import com.example.library.models.BookType;
import com.example.library.models.Tag;
import com.example.library.models.dto.BookTypeDTO;
import com.example.library.repositories.BookTypeRepository;
import com.example.library.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("api")

public class SearchController {
    //search api
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    TagRepository tagRepository;
    @Autowired
    BookTypeRepository bookTypeRepository;

    @GetMapping("search")
    public List<BookTypeDTO> searchBooks(@RequestParam(name = "tag", required = false) String tagName, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String subject)
    {
        List<BookTypeDTO> foundBooks = new ArrayList<>();
        if(tagName!=null) {
            tagName = tagName.toLowerCase();
            Tag tag = tagRepository.findById(tagName).orElse(null);
            if (tag != null) {

                {
                    for (BookType bookType : tag.getBookTypes())
                        foundBooks.add(new BookTypeDTO(bookType));
                }
            }
        }

        if(title!=null)
            title=title.toLowerCase();
        if(author!=null) {
            LOGGER.info("Searching for books with author: " + author);
            author = author.toLowerCase();
        }
        if(subject!=null)
            subject=subject.toLowerCase();

        for(BookType bookType:bookTypeRepository.findAll())
        {
            if (author != null && bookType.getAuthor().toLowerCase().equals(author))
                foundBooks.add(new BookTypeDTO(bookType));
            if (title != null && bookType.getTitle().toLowerCase().equals(title))
                foundBooks.add(new BookTypeDTO(bookType));
            if (subject != null && bookType.getSubject().toLowerCase().equals(subject))
                foundBooks.add(new BookTypeDTO(bookType));
        }
        return foundBooks;
    }
}

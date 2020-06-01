package com.example.library;

import com.example.library.models.*;
import com.example.library.models.dto.BookTypeDTO;
import com.example.library.models.dto.PatronDTO;
import com.example.library.models.dto.TransactionDTO;
import com.example.library.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LibraryController {

    //Repository autowiring
    @Autowired
    BookUnitRepository bookUnitRepository;

    @Autowired
    BookTypeRepository bookTypeRepository;

    @Autowired
    PatronRepository patronRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TransactionRepository transactionRepository;


    //patrons api
//    @GetMapping("patrons")
//    public List<Patron> getAllPatrons()
//    {
//        return patronRepository.findAll();
//    }
//
//    @GetMapping("patrons/{id}")
//    public Optional<Patron> getAPatron(@PathVariable Integer id)
//    {
//        return patronRepository.findById(id);
//    }
//
//    @PostMapping("patrons")
//    public void addAPatron(@RequestBody Patron p)
//    {
//        patronRepository.save(p);
//    }
//


    //books api
    @GetMapping("books")
    public List<BookTypeDTO> getAllBooks()
    {
        List<BookTypeDTO> allBooks = new ArrayList<>();

        for(BookType bookType:bookTypeRepository.findAll())
        {
            allBooks.add(new BookTypeDTO(bookType));
        }
        return allBooks;
    }

    @PostMapping("books")
    public void addABook(@RequestBody BookType bookType)
    {

        BookType bookType1=new BookType(bookType.getIsbn(),bookType.getTitle(),bookType.getAuthor(), bookType.getSubject());
        bookTypeRepository.save(bookType1);
        for(BookUnit bookUnit: bookType.getBookUnits())
        {
            bookUnit.setBookType(bookType1);
            bookUnitRepository.save(bookUnit);
        }
        for(Tag tag:bookType.getTags())
        {
            tag.getBookTypes().add(bookType1);
            tagRepository.save(tag);
        }

    }

    //patrons api
    @GetMapping("patrons")
    public List<PatronDTO> getAllPatrons(){
        List<PatronDTO> allPatrons = new ArrayList<>();
        for(Patron patron:patronRepository.findAll())
        {
            allPatrons.add(new PatronDTO(patron));
        }
        return allPatrons;
    }

    @PostMapping("patrons")
    public void addAPatron(@RequestBody Patron patron)
    {
        patronRepository.save(patron);
    }
    @DeleteMapping("patrons/{id}")
    public boolean deleteAPatron(@PathVariable Integer id)
    {
        if(patronRepository.existsById(id))
        {
            Patron patron = patronRepository.findById(id).get();
            for(Transaction transaction:patron.getTransactionHistory())
                transactionRepository.delete(transaction);
            patronRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    //transactions api
    @PostMapping("transactions")
    public void addATransaction(@RequestBody Transaction transaction){
        transactionRepository.save(transaction);
        BookUnit bookUnit = bookUnitRepository.findById(transaction.getBookUnit().getId()).get();
        bookUnit.setBorrowed(!bookUnit.isBorrowed());
    }

    @GetMapping("transactions")
    public List<TransactionDTO> getAllTransactions()
    {
        List<TransactionDTO> allTransactions = new ArrayList<>();
        for(Transaction transaction:transactionRepository.findAll())
            allTransactions.add(new TransactionDTO(transaction));
        return allTransactions;
    }

    //search api
    @GetMapping("search")
    public List<BookTypeDTO> searchBooks(@RequestParam String q)
    {
        Optional<Tag> tag = tagRepository.findById(q);
        List<BookTypeDTO> foundBooks = new ArrayList<>();
        if(tag!=null)
        {
            for(BookType bookType:tag.get().getBookTypes())
                foundBooks.add(new BookTypeDTO(bookType));
            return foundBooks;
        }
        return null;
    }


}

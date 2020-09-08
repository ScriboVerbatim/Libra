package com.example.library.controllers;

import com.example.library.exceptions.*;
import com.example.library.models.BookType;
import com.example.library.models.BookUnit;
import com.example.library.models.Patron;
import com.example.library.models.Transaction;
import com.example.library.models.dto.PatronDTO;
import com.example.library.models.dto.TransactionDTO;
import com.example.library.repositories.BookTypeRepository;
import com.example.library.repositories.BookUnitRepository;
import com.example.library.repositories.PatronRepository;
import com.example.library.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("api")

public class TransactionController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookUnitRepository bookUnitRepository;

    @Autowired
    PatronRepository patronRepository;

    @Autowired
    BookTypeRepository bookTypeRepository;

    //transactions api
//    @GetMapping("transaction/issue/{isbn}/{userid}")
//    public void issueBookByIsbn(@PathVariable String isbn, @PathVariable int userid){
//        BookType bookType=bookTypeRepository.findById(isbn).orElseThrow(()->new BookTypeNotFoundException(isbn));
//        Patron patron=patronRepository.findById(userid).orElseThrow(()->new PatronNotFoundException(userid));
//        BookUnit temp=null;
//        for(BookUnit bookUnit:bookType.getBookUnits())
//        {
//            if(!bookUnit.isRemoved() && !bookUnit.isBorrowed())
//            {
//                temp=bookUnit;
//                break;
//            }
//        }
//        if(temp==null)
//            throw new NoAvailableCopiesException(isbn);
//        temp.setBorrowed(true);
//        bookUnitRepository.save(temp);
//        Transaction transaction=new Transaction();
//        transaction.setBookUnit(temp);
//        transaction.setPatron(patron);
//
//        //set issue date
//        transactionRepository.save(transaction);
//
//    }

    @PostMapping("transactions/issue")
    public void issueABook(@RequestBody Transaction transaction){
        LOGGER.info("transactions/issue called");
        BookUnit bookUnit = bookUnitRepository.findById(transaction.getBookUnit().getId()).orElseThrow(()-> new BookUnitNotFoundException(transaction.getBookUnit().getId()));
        Patron patron = patronRepository.findById(transaction.getPatron().getId()).orElseThrow(()->new PatronNotFoundException(transaction.getPatron().getId()));
        if(bookUnit.isBorrowed())
            throw new BookUnitBorrowedException(bookUnit.getId());
        transactionRepository.save(transaction);
        bookUnit.setBorrowed(true);
        bookUnit.getTransactionHistory().add(transaction);
        bookUnitRepository.save(bookUnit);
        patron.getTransactionHistory().add(transaction);
        patronRepository.save(patron);
    }

    @PostMapping("transactions/return")
    public void returnABook(@RequestBody Transaction transaction){
        Transaction transaction1 = transactionRepository.findById(transaction.getId()).orElseThrow(()->new TransactionNotFoundException(transaction.getId()));
        BookUnit bookUnit = transaction1.getBookUnit();
        if(!bookUnit.isBorrowed())
            throw new BookReturnedException(bookUnit.getId());
        bookUnit.setBorrowed(false);
        bookUnitRepository.save(bookUnit);
        if(transaction.isGoodReturnCondition()!=null)
            transaction1.setGoodReturnCondition(transaction.isGoodReturnCondition());
        else
            transaction1.setGoodReturnCondition(true);
        transaction1.setReturnDate(transaction.getReturnDate());
        transaction1.setRemarks(transaction.getRemarks());
        //code to calculate delay fine and add the fine within post request body as extra
        transactionRepository.save(transaction1);
    }



//    @GetMapping("transactions")
//    public List<TransactionDTO> getAllTransactions()
//    {
//        List<TransactionDTO> allTransactions = new ArrayList<>();
//        for(Transaction transaction:transactionRepository.findAll())
//            allTransactions.add(new TransactionDTO(transaction));
//        return allTransactions;
//    }

    @GetMapping("transactions/{id}")
    public TransactionDTO getATransaction(@PathVariable Integer id)
    {
        return new TransactionDTO(transactionRepository.findById(id).orElseThrow(()->new TransactionNotFoundException(id)));
    }

    @GetMapping("transactions")
    public List<TransactionDTO> transactionByCondition(@RequestParam(name ="userid", required = false) Integer userId, @RequestParam(name = "bookid", required = false) Integer bookId)
    {
        List<TransactionDTO> foundTransactions = new ArrayList<>();
        if(userId == null && bookId == null)
        {
            for(Transaction transaction:transactionRepository.findAll())
                foundTransactions.add(new TransactionDTO(transaction));
            return foundTransactions;
        }
        if(userId == null)
        {
            for(Transaction transaction:transactionRepository.findAll()) {
                if (transaction.getBookUnit().getId() == bookId)
                    foundTransactions.add(new TransactionDTO(transaction));
            }

            return foundTransactions;
        }
        if(bookId == null)
        {
            for(Transaction transaction:transactionRepository.findAll()) {
                if (transaction.getPatron().getId() == userId)
                    foundTransactions.add(new TransactionDTO(transaction));
            }

            return foundTransactions;
        }

        for(Transaction transaction:transactionRepository.findAll()) {
            if (transaction.getPatron().getId() == userId && transaction.getBookUnit().getId() == bookId)
                foundTransactions.add(new TransactionDTO(transaction));
        }
        return foundTransactions;
    }

    @GetMapping("transactions/active/{userid}")
    public List<TransactionDTO> activeTransactionOfUser(@PathVariable Integer userid)
    {
        LOGGER.info("transactions/active/{userid} with userid: "+userid);
        Patron patron = patronRepository.findById(userid).orElseThrow(()->new PatronNotFoundException(userid));
        List<TransactionDTO> foundTransactions = new ArrayList<>();
        for(Transaction transaction:patron.getTransactionHistory())
        {
            if(transaction.isGoodReturnCondition()==null)
                foundTransactions.add(new TransactionDTO(transaction));
        }
        return foundTransactions;
    }

}

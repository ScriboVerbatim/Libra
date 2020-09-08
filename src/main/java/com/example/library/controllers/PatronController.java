package com.example.library.controllers;

import com.example.library.exceptions.PatronNotFoundException;
import com.example.library.models.Patron;
import com.example.library.models.dto.PatronDTO;
import com.example.library.repositories.PatronRepository;
import com.example.library.repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api")

public class PatronController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    PatronRepository patronRepository;
    @Autowired
    TransactionRepository transactionRepository;

    //patrons api
    @GetMapping("patrons")
    public List<PatronDTO> getAllPatrons(){
        LOGGER.info("/patrons called");
        List<PatronDTO> allPatrons = new ArrayList<>();
        for(Patron patron:patronRepository.findAll())
        {
            if(!patron.isRemoved())
                allPatrons.add(new PatronDTO(patron));
        }
        return allPatrons;
    }

    @GetMapping("patrons/sudo")
    public List<PatronDTO> forceGetAllPatrons(){
        LOGGER.info("/patrons called");
        List<PatronDTO> allPatrons = new ArrayList<>();
        for(Patron patron:patronRepository.findAll())
        {
            allPatrons.add(new PatronDTO(patron));
        }
        return allPatrons;
    }

    @GetMapping("patrons/{id}")
    public PatronDTO getAPatron(@PathVariable Integer id){
        Patron patron= patronRepository.findById(id).orElseThrow(()-> new PatronNotFoundException(id));
        if(patron.isRemoved())
            throw new PatronNotFoundException(id);
        return new PatronDTO(patron);
    }

    @GetMapping("patrons/sudo/{id}")
    public PatronDTO forceGetAPatron(@PathVariable Integer id){
        return new PatronDTO(patronRepository.findById(id).orElseThrow(()-> new PatronNotFoundException(id)));
    }

    @PostMapping("patrons")
    public void addAPatron(@RequestBody List<Patron> patrons)
    {
        int ctr=0;
        for(Patron patron:patrons) {
            patronRepository.save(patron);
            ++ctr;
        }
        LOGGER.info("Added "+ctr+" patrons");
    }

    @DeleteMapping("patrons/{id}")
    public void deleteAPatron(@PathVariable Integer id)
    {
        Patron patron = patronRepository.findById(id).orElseThrow(()->new PatronNotFoundException(id));
        if(!patron.isRemoved())
        {
            patron.setRemoved(true);
            //patron.get().setDateOfExit(date of removal);
            patronRepository.save(patron);
        }
        else
            throw new PatronNotFoundException(id);
    }

    @PatchMapping(path = "patrons/{id}")//, consumes = "application/json-patch+json")
    public void editAPatron(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Patron patron = patronRepository.findById(id).orElseThrow(()->new PatronNotFoundException(id));
        Patron patronPatched = applyPatch(patch,patron);
        patronRepository.save(patronPatched);

    }

    public Patron applyPatch(JsonPatch patch, Patron targetPatron) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPatron, JsonNode.class));
        return objectMapper.treeToValue(patched,Patron.class);
    }

}

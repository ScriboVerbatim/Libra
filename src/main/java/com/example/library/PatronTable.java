package com.example.library;

import com.example.library.models.Patron;

import java.util.ArrayList;


public class PatronTable {
    ArrayList<Patron> table = new ArrayList<>();

    public PatronTable() {
        Patron p=new Patron(1,"Abhilash","abhilash.mishra003", "abcd1234", "asd@gmail.com","7998");
        this.table.add(p);
        p = new Patron(2,"Dibya Mohapatra","djm999", "abcd1234","djm@gmail.com", "8990");
        this.table.add(p);
        System.out.println("Made Patron table");
    }

    public ArrayList<Patron> findAll()
    {
        return table;
    }

    public Patron findById(int id)
    {
        for(Patron p:table)
           if(p.getId()==id)
                return p;
        return null;
    }

    public boolean insertOne(Patron p)
    {
        return table.add(p);
    }
    public boolean deleteOne(String username)
    {
        for(Patron p:table)
            if (p.getUsername().equals(username))
            {
                table.remove(p);
                return true;
            }

        return false;
    }
}

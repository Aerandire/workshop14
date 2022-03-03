package edu.sg.nus.iss.workshop14.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sg.nus.iss.workshop14.model.Contact;

@Repository
public interface RedisRepo {
    public void save(final Contact ctc);
    public Contact findById(final String ContactId);
    public List<Contact> findAll(int startIndex);
    
}

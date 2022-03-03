package edu.sg.nus.iss.workshop14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sg.nus.iss.workshop14.model.Contact;
import edu.sg.nus.iss.workshop14.service.ContactsRepo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AddressbookController {
        private Logger logger = Logger.getLogger(AddressbookController.class.getName());

        @Autowired
        ContactsRepo service;

        @GetMapping("/")
        public String contactForm(Model model){
            logger.log(Level.INFO, "Show the contact form");
            model.addAttribute("contact", new Contact());
            return "contact";
        }

        @GetMapping("/getContact/{contactId}")
        public String getContact(Model model, @PathVariable(value="contactId")
                    String contactId){

                    Contact ctc = service.findById(contactId);
                    model.addAttribute("contact", ctc);

                    return "showContact";
        }

        @GetMapping("/contact")
        public String getAllContact(Model model, @RequestParam(name="startIndex")
                String startIndex){

                    List<Contact> resultFromsvc = service.findAll(Integer.parseInt(startIndex));

                    model.addAttribute("contacts", resultFromsvc);
                    return "listContact";
                }

        @PostMapping("/contact")
        public String contactSubmit(@ModelAttribute Contact contact, Model model, HttpServletResponse httpResponse){
            logger.log(Level.INFO, "ID: " + contact.getId());
            logger.log(Level.INFO, "Name: " + contact.getName());
            logger.log(Level.INFO, "Email: " + contact.getEmail());
            logger.log(Level.INFO, "Phone Number: " + contact.getPhoneNumber());

            Contact persistToRedisCtc = new Contact(
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber()
            );

            service.save(persistToRedisCtc);
            model.addAttribute("contact", persistToRedisCtc);
            return "showContact";
        }
}
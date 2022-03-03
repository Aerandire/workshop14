package edu.sg.nus.iss.workshop14.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Contact Class represent the contact information
 * of the address book
 */

public class Contact implements Serializable{
        //Defensive coding for serialisation for old code
        //private static final long serialVersionUID = 1L;
        private String id;
        private String name;
        private String email;
        private String phoneNumber;

        public Contact(){
            this.id = generateId(8);
        }

        public Contact(String id, String name, String email, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public Contact(String name, String email, String phoneNumber) {
            this.id = generateId(8);
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        private synchronized String generateId(int numChars){
            Random r = new Random();
            StringBuilder strBuilder = new StringBuilder();

            while(strBuilder.length() < numChars){
                strBuilder.append(Integer.toHexString(r.nextInt()));
            }
            return strBuilder.toString().substring(0, numChars);
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
}
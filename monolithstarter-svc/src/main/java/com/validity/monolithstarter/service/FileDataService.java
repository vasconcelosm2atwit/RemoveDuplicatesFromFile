package com.validity.monolithstarter.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.validity.monolithstarter.domain.Person;

import org.springframework.stereotype.Service;

@Service
public class FileDataService {
    // SOLUTION FROM MICHAEL ANDERSON VASCONCELOS
    List<Person> duplicates = new ArrayList<>();

    // Reads file and filter data removing duplicates
    public Set<Person> getData() {
        Map<Integer, String> header = new HashMap<>();
        List<Person> duplicates = new ArrayList<Person>();
        Set<Person> personSet = new LinkedHashSet<Person>();

        // CHANGE FILE PATH TO RUN ON DIFFERENT MACHINE, Relative file path not working
        // on my machine.
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "C:/Users/vasconcelosm2/Desktop/simple-app-starter/monolithstarter-svc/src/main/resources/test-files/advanced.csv"))) {

            String line = "";
            // read file
            line = reader.readLine();
            String[] headerSplitter = line.split(",");
            for (int i = 0; i < headerSplitter.length; i++) {
                header.put(i, headerSplitter[i]);
            }

            while ((line = reader.readLine()) != null) {

                Person newPerson = new Person();
                String temp = "";
                int pos = 0; // id, first_name, last_name, company, email, address1, address2, zip, city,
                             // state_long, state, phone
                boolean qtFound = false;
                Stack<Character> qta = new Stack<Character>();

                // O(n) + file size, O(n^2)
                for (char c : line.toCharArray()) {
                    // add characters until a coma is found, then add word to the map
                    // if \" is found then add everything until the \" is over (Ignore comas for the
                    // duration)

                    // if "," encountered adds to object
                    if ((!qtFound && c == ',')) {

                        switch (header.get(pos)) {
                        case "first_name":
                            newPerson.setFirst_name(temp);
                            break;
                        case "last_name":
                            newPerson.setLast_name(temp);
                            break;
                        case "company":
                            newPerson.setCompany(temp);
                            break;
                        case "email":
                            newPerson.setEmail(temp);
                            break;
                        case "address1":
                            newPerson.setAddress1(temp);
                            break;
                        case "address2":
                            newPerson.setAddress2(temp);
                            break;
                        case "zip":
                            newPerson.setZip(temp);
                            break;
                        case "city":
                            newPerson.setCity(temp);
                            break;
                        case "state_long":
                            newPerson.setState_long(temp);
                            break;
                        case "state":
                            newPerson.setState(temp);
                            break;
                        case "phone":
                            newPerson.setPhone(temp);
                            break;
                        default:
                            newPerson.setId(temp); // if none is found, it must be the id, therefore id is default
                            break;
                        }
                        pos++; // update position
                        temp = "";
                        continue; // move to next iteration
                    }
                    // if " encountered, add to stack and ignore commas until another " is
                    // encountered
                    if (c == '"') {
                        // System.out.println(qta.toString());
                        if (!qta.empty()) {
                            qtFound = false;
                            qta.pop();
                        } else {
                            qtFound = true;
                            qta.push(c);
                        }
                        continue; // move to next iteration
                    }

                    temp += c; // add characters
                }

                // if temp is not empty, its the last string, the phonenumber.
                if (temp != "") {
                    newPerson.setPhone(temp);
                }

                boolean notInSet = personSet.add(newPerson); // treturns true if data is not on set

                if (!notInSet) {
                    duplicates.add(newPerson); // adds to duplicates pile
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        holdDuplicate(duplicates);

        // return new ArrayList<Person>(personSet);
        return personSet;
    }

    public void holdDuplicate(List<Person> list) {
        this.duplicates = new ArrayList<>();
        this.duplicates = list;
    }

    public List<Person> getDuplicates() {
        return this.duplicates;
    }

}

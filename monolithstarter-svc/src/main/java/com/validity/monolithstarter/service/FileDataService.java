package com.validity.monolithstarter.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.validity.monolithstarter.domain.Person;

import org.springframework.stereotype.Service;

@Service
public class FileDataService {
    // SOLUTION FROM MICHAEL ANDERSON VASCONCELOS


    List<Person> duplicates;

    // Reads file and filter data removing duplicates
    public List<Person> getData() {
        List<Person> personList = new ArrayList<Person>();
        List<Person> duplicates = new ArrayList<Person>();

        // CHANGE FILE PATH TO RUN ON DIFFERENT MACHINE, Relative file path not working
        // on my machine.
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "C:/Users/vasconcelosm2/Desktop/simple-app-starter/monolithstarter-svc/src/main/resources/test-files/normal.csv"))) {

            String line = "";
            int lineCount = 0;
            // read file
            while ((line = reader.readLine()) != null) {

                // skips first line
                if (lineCount == 0) {
                    lineCount++;
                    continue;
                }

                lineCount++;
                String[] hold = line.split(","); // splits data
                Person person = new Person(hold); // create new object
                personList.add(person);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // Start Levenshtein distance
        // This algorithm is only filtering a distance of 3, if it only requires only 3
        // changes to become another full name(First+last)
        for (int i = 1; i <= personList.size() - 1; i++) {
            String name1 = personList.get(i).getFullName();
            String name2 = personList.get(i - 1).getFullName();
            int distance = DistanceCalculation(name1, name2); // gets Distance
            if (distance < 4) {
                duplicates.add(personList.get(i));
                personList.remove(i);
            }
        }

        holdDuplicate(duplicates);

        return personList;
    }

    // Levenshtein distance using Dynamic Programming
    // Time Complexity O(M*N) worse case of Min(world1.length, world2.length)
    public static int DistanceCalculation(String w1, String w2) {
        int w1Size = w1.length();
        int w2Size = w2.length();
        int[][] dp = new int[w1Size + 1][w2Size + 1];
        for (int i = 1; i <= w1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= w2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= w1Size; i++) {
            for (int j = 1; j <= w2Size; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[w1Size][w2Size];
    }

    public void holdDuplicate(List<Person> list) {
        this.duplicates = new ArrayList<>();
        this.duplicates = list;
    }

    public List<Person> getDuplicates() {
        return this.duplicates;
    }

}

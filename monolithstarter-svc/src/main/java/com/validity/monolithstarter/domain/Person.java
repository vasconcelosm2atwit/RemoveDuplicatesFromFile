package com.validity.monolithstarter.domain;

public class Person {
    private int id;
    private String first_name;
    private String last_name;
    private String company;
    private String email;
    private String fullAddress;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state_long;
    private String state;
    private String phone;

    public Person() {

    }
    //#region getters, setters and toString
    public String getFullName() {
        return first_name + " " + last_name;
    }

    public String getFullAddress() {
        return address1 + " " + address2 + ", " + city + " " + state + ", " + zip;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "{" + "\nId: " + id + "\nname: " + getFullName() + "\nCompany: " + getCompany() + "\nEmail: "
                + getEmail() + "\nAddress1: " + getAddress1() + "\nAddress2: " + getAddress2() + "\nZip: " + getZip()
                + "\nCity: " + getCity() + "\nState: " + getState() + "\nPhone: " + getPhone() + "\n}";
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_long() {
        return state_long;
    }

    public void setState_long(String state_long) {
        this.state_long = state_long;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //#endregion

    @Override
    public int hashCode() {
        return 0; // returning 0 so Sets gets to use the equals
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Person))
            return false;

        if (obj == this)
            return true;

        return this.checkDistance((Person) obj);
    }

    // check Distance
    public boolean checkDistance(Person other) {
        int dst = distanceCalculation(this.getFullName(), other.getFullName());
        if (dst < 3) {
            return true;
        }

        return false;
    }

    // check distance in O(n)
    public boolean checkDistanceQuicker(Person other) {
        int first = findDistanceBetweenTwoWords(this.getFirst_name(), other.getFirst_name());
        int last = findDistanceBetweenTwoWords(this.getLast_name(), other.getLast_name());
        int dst = first + last;
        if (dst < 3) {
            return true;
        }

        return false;
    }

    // Edit distance between two words, O(n) faster but less accurate than
    // Laventhein method
    public int findDistanceBetweenTwoWords(String name1, String name2) {
        int m = name1.length(); 
        int n = name2.length();
        int dist = Math.abs(m - n); // comparate strings size
        // if letter size is bigger than 2 strings, return distance, cannot be the same 
        if (dist > 2) {
            return dist;
        }

        int i = 0;
        int j = 0;
        String large = name1;
        String small = name2;
        // assign larger and smaller strings
        if (dist != 0) {
            if (m > n) {
                large = name1;
                small = name2;
            } else {
                large = name2;
                small = name1;
            }
        }

        int count = 0; // distance count
        // counts the different variables between two strings
        while (i < large.length()) {
            if (large.charAt(i) != small.charAt(j)) {
                count++;
            }
            if (i < large.length())
                i++;
            if (j < small.length() - 1)
                j++;

        }

        return count;
    }

    // Levenshtein distance using Dynamic Programming
    // Time Complexity O(M*N) worse case of Min(world1.length, world2.length)
    public int distanceCalculation(String w1, String w2) {
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
}

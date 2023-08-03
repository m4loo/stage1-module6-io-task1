package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null;
        int age = 0;
        String email = null;
        long phone = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(": ");
                String key = parts[0];
                String value = parts[1];
                if (parts.length ==2) {
                    switch (key) {
                        case "Name" -> name = value;
                        case "Age" -> {
                            try {
                                age = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Email" -> email = value;
                        case "Phone" -> {
                            try {
                                phone = Long.parseLong(value);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return new Profile(name, age, email, phone);
    }
}

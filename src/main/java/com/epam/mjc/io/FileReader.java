package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String name = null;
        int age = 0;
        String email = null;
        Long phone = null;

        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] parts = line.split(": ");

                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];

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
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return new Profile(name, age, email, phone);
    }
}

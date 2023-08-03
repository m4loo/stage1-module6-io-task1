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

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(": ");
                String key = parts[0];
                String value = parts[1];
                if (parts.length == 2) {
                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            try {
                                age = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            try {
                                phone = Long.parseLong(value);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new Profile(name, age, email, phone);
    }
}
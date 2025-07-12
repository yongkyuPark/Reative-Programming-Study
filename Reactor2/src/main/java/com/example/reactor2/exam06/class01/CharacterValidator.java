package com.example.reactor2.exam06.class01;

import java.util.zip.DataFormatException;

public class CharacterValidator {

    public static void isAlphabeticCharacter(char ch) throws DataFormatException {
        if (!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
    }


}

package me.fyronfd.CaeserCipher;

import java.util.Scanner;

public class Main {
    public static int shift;

    public static void main(String[] args) {
        //Asks User for their message and how many letters to shift by
	    Scanner input = new Scanner(System.in);
	    System.out.print("What is your message? ");
	    String userMessage = input.nextLine();
	    System.out.print("How many letters would you like to shift by? ");
        shift = input.nextInt();

        // Gets the length of the message and turns it into a char array
        int messageLen = userMessage.length();
        char[] letter = userMessage.toCharArray();
        boolean outOfBounds; //Limits to only letters
        for (int i = 0; i < messageLen; i++) {
            outOfBounds = (Character.toLowerCase(letter[i]) > 120 || Character.toLowerCase(letter[i]) < 97);
            if(outOfBounds){
                continue;
            }
            boolean upperCase = Character.isUpperCase(letter[i]);//Evaluates if it is an uppercase letter
            char newLetter = evaluateCharacter(letter[i], upperCase);//Changes letter
            letter[i] = newLetter;
        }
        String encodedMessage = String.valueOf(letter);//Replaces char array with string
        System.out.println("");
        System.out.println("New message: " + encodedMessage);

    }

    public static char evaluateCharacter(char letterAtPosition, boolean upperCase){
        if(upperCase){//Uses Recursion to make the lowercase version of that character change, then places it back in uppercase form
            letterAtPosition = Character.toLowerCase(letterAtPosition);
            char lowerCase = evaluateCharacter(letterAtPosition, false);
            char newChar = Character.toUpperCase(lowerCase);
            return newChar;
        }else{//Shifts the letter based on ascii value
            int ascii = letterAtPosition;
            if (ascii + shift > 122){
                ascii = 96 + (ascii + shift - 122);
            } else {
                ascii = ascii + shift;
            }
            char newChar = (char)ascii;
            return newChar;
        }
    }


}

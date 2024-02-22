package bullscows;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boolscount = 0;
        char[] englishAlphabet = new char[26];
        char letter = 'a';
        for (int i = 0; i < 26; i++) {
            englishAlphabet[i] = letter;
            letter++;
        }
        int lengthOfRand = 0;
        try {
            System.out.println("Please, enter the secret code's length:");
            String userInput = scanner.nextLine();
            lengthOfRand = Integer.parseInt(userInput);
            if(lengthOfRand == 0){
                System.out.println("error");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + e.getMessage() + "\" isn't a valid number.");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int lengthOfSymbols = scanner.nextInt();
        if(lengthOfSymbols>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        if(lengthOfRand>lengthOfSymbols){
            System.out.println("Error: it's not possible to generate a code with a length of "+lengthOfRand+" with "+lengthOfSymbols+" unique symbols.");
            System.exit(0);
        }
        System.out.print("The secret is prepared: ");
        for(int i = 0;i<lengthOfRand;i++){
            System.out.print("*");
        }
        System.out.println(" (0-9, a-" + finalLetter(lengthOfRand,lengthOfSymbols,englishAlphabet)+").");
        System.out.println("Okay, let's start a game!");
        String randomintNumb;
            while (true) {
                randomintNumb = generateRandomString(lengthOfRand,lengthOfSymbols);
                char[] charRandom = randomintNumb.toCharArray();
                if (hasUniqueDigits(charRandom)) {
                    break;
                }
            }
            int i = 1;
            while(true){
                System.out.println("Turn "+i+":");
                if (i == 1) {
                    scanner.nextLine();
                }
              String tryNumb = scanner.nextLine();
                cowsAndBulls(tryNumb ,randomintNumb,boolscount);
                i++;
            }
        }


    private static boolean hasUniqueDigits(char[] charRandom) {
        Set<Character> charSet = new HashSet<>();

        for (char c : charRandom) {
            if (!charSet.add(c)) {
                return false;
            }
        }
        return true;
    }
    public static void cowsAndBulls(String tryNumb , String randomintNumb,int boolscount){
        Scanner scanner = new Scanner(System.in);
        String key = randomintNumb;
        String[] keyArr = key.split("");
        String[] estimatedArr = tryNumb.split("");
        int bools = 0;
        int cows = 0;
        for(int x = 0;keyArr.length > x ; x++){
            if(estimatedArr[x].equals(keyArr[x])){
                bools++;
            }
            for(int y = 0; y < estimatedArr.length;y++){
                if(estimatedArr[y].equals(keyArr[x]) && x != y){
                    cows++;
                }
            }
        }
        if(bools == 0 && cows == 0){
            System.out.println("Grade: None.");
        }
        else if(bools == 0){
            System.out.println("Grade: " + cows + " cow(s).");
            boolscount++;
        }
        else if (bools == keyArr.length || boolscount == keyArr.length) {
            System.out.println("Grade: " + keyArr.length + " bulls");
            System.out.println("Congratulations! You guessed the secret code.");
            System.exit(0);
        }else if (cows == 0) {
            System.out.println("Grade: " + bools + " bull(s).");
            boolscount++;
        } else{
            System.out.println("Grade: " + bools + " bull(s) and " + cows + " cow(s).");
        }
    }
public static char finalLetter( int lengthOfRand,int lengthOfSymbols,char[] englishAlphabet){
        int amountOfLetters = lengthOfSymbols-10;
        if(amountOfLetters == 0){
            return englishAlphabet[0];
        }
        else {return englishAlphabet[amountOfLetters-1];}
}
    public static String generateRandomString(int lengthOfRand,int lengthOfSymbols){
        String characters = "0123456789abcdefghijklmnopqrstuvwxyz";
        char[] charArr= characters.toCharArray();
        char[]charArr2 = new char[lengthOfSymbols];
        for(int i = 0;i<lengthOfSymbols;i++){
            charArr2[i] = charArr[i];
        }
        String characters2 = new String(charArr2);
        SecureRandom random = new SecureRandom();

        StringBuilder stringBuilder = new StringBuilder(lengthOfRand);


        for (int i = 0; i < lengthOfRand; i++) {
            int randomIndex = random.nextInt(characters2.length());
            char randomChar = characters2.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
    }
     


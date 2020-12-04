
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int validPasswords1 = 0;
        int validPasswords2 = 0;

        while(true) {
            try {
                String line = sc.nextLine();
                int left = Integer.parseInt(line.split("-")[0]);
                int right = Integer.parseInt(line.split("-")[1].split("\\s+")[0]);
                char letter =  line.split("\\s+")[1].split(":")[0].charAt(0);
                String passwordLetters = line.split(":")[1].strip();

                int counter = 0;

                for(int i = 0; i < passwordLetters.length(); i++) {
                    if(letter == passwordLetters.charAt(i)) counter++;
                }
                if(counter >= left && counter <= right) {
                    validPasswords1++;
                }
                if(( passwordLetters.charAt(left - 1) == letter || passwordLetters.charAt(right - 1) == letter )
                        && !( passwordLetters.charAt(left - 1) == letter && passwordLetters.charAt(right - 1) == letter )) {
                    validPasswords2++;
                }
            } catch (Exception ex) {
                System.out.println(validPasswords1);
                System.out.println(validPasswords2);
                break;
            }
        }
    }

}

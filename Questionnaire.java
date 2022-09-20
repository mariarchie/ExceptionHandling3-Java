import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Questionnaire {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Surname Name MiddleName BirthDate TelNumber Gender");
        String[] s = scanner.nextLine().split(" ");
        scanner.close();
        
        // System.out.println(CV(s));
        String text = CV(s).toString();
        String surname = text.contains(" ") ? text.split(" ")[0] : text;
        surname = surname.substring(1, surname.length() - 1);

        try(FileWriter writer = new FileWriter(surname + ".txt", true))
        {
            writer.append(text);
            writer.close();
        }
        catch(IOException ex){
             
            System.out.println("Error creating/filling file, pls, try again");
        } 
    }

   
    public static Formatter CV (String[] s) throws Exception{   
        String surname = null;
        String name = null;
        String middleN = null;
        String birthD = null;
        long telN = 0;
        char gender = 0;

        if (s.length > 6) {
            throw new NullPointerException("There should be less parameters, namely, 6!");
        }
        if (s.length < 6) {
            throw new NullPointerException("There should be more parameters, namely, 6!");
        }

        for (int i = 0; i < s.length; i++) {
            if (s[i].matches("[a-zA-Z]+") && s[i].length() > 1) {
                if (s[i+1].matches("[a-zA-Z]+") && s[i+1].length() > 1) {
                    if (s[i+2].matches("[a-zA-Z]+") && s[i+2].length() > 1) {
                        surname = s[i];
                        name = s[i+1];
                        middleN = s[i+2];
                    }
                }
            }
      
            if (s[i].matches("\\d{2}.\\d{2}.\\d{4}")) {
                birthD = s[i];
            }

            if (s[i].matches("[0-9]+") && s[i].length() == 11) {
                telN = Long.parseLong(s[i]);
            }

            if (s[i].equals("f") || s[i].equals("m")) {
                gender = s[i].charAt(0);
            }

        }

        if (surname == null || name == null || middleN == null) {
            throw new NullPointerException("'Name Surname MiddleName' is incorrect");
        }
        if (birthD == null) {
            throw new NullPointerException("BirthDate is incorrect, pls, enter dd.mm.yyyy");
        }
        if (telN == 0) {
            throw new NumberFormatException("TelNumber is incorrect, pls, enter 11 digits");
        }
        if (gender == 0) {
            throw new NullPointerException("Gender is incorrect, pls, enter 'm' or 'f'");
        }

        Formatter f = new Formatter();
        f.format("<%s> <%s> <%s> <%s> <%d> <%c>\n", surname, name, middleN, birthD, telN, gender);
        return f;
    }

}




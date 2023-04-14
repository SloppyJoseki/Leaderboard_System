import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManager
{
    String username;
    String password;
    public boolean isAlphanumeric(String string)
    {
        // Used to check a string contains no special characters that could interfere with the csv format such as a comma
        char[] charArray = string.toCharArray();
        for(char c : charArray)
        {
            if (!Character.isLetterOrDigit(c))
                return true;
        }
        return false;
    }
    public boolean containsALetterAndANumber(String string)
    {
        // Uses a regex expression to check the username has a number both lower and uppercase letters and is between 4 and 15 characters
        Pattern passwordCheck = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,15}$");
        Matcher matcher = passwordCheck.matcher(string);
        return matcher.matches();
    }
    boolean usernameIsAvailable(String username)
    {
        // Checks the username is not already being used by another account
        CsvManager csvManager = new CsvManager();
        String[] csvData = csvManager.readCsvFileToString();
        for (String csvDatum : csvData) {
            if (csvDatum.equals(username)) {
                return false;
            }
        }
        return true;
    }
    void createUsername()
    {
        // Lets the user create a username and verifies it to ensure it does not break the program
        System.out.println("Please enter your desired Username (maximum 15 characters minimum 4 only alphanumeric \n" +
                "and requires one uppercase letter one lowercase letter and one number): ");
        Scanner sc = new Scanner(System.in);
        boolean usernameControl = true;
        do
        {
            username = sc.nextLine();
            if (!usernameIsAvailable(username) || !containsALetterAndANumber(username))
            {
                System.out.println("I'm sorry your username must meet the above requirements");
            }
            else
            {
                System.out.println("Your username is: " + username);
                usernameControl = false;
            }
        } while (usernameControl);
    }
    void createPassword()
    {
        // Lets the user generate a password and ensures it does not break the program
        System.out.println("Please enter a desired password (maximum 15 characters and only alphanumeric): ");
        Scanner sc = new Scanner(System.in);
        int passwordLength;
        boolean passwordControl = true;
        do
        {
            password = sc.nextLine();
            passwordLength = password.length();
            if (passwordLength > 15 || passwordLength == 0)
            {
                System.out.println("Your password cannot be longer than 15 characters or fewer than 1");
            }
            else if (isAlphanumeric(password))
            {
                System.out.println("I'm sorry your password my only contain letters and digits");
            }
            else
            {
                System.out.println("Your password is: " + password);
                passwordControl = false;
            }
        } while (passwordControl);
    }
    void createAccount()
    {
        createUsername();
        createPassword();
    }

    int checkUsernamePosition(String[] csvData, String username)
    {
        // Used to find a specific username within the array of Strings
        for (int i = 0; i < csvData.length; i++)
        {
            if (csvData[i].equals(username))
            {
                return i;
            }
        }
        return -1;
    }
    boolean checkUsername()
    {
        // If the username exists will return true else will return false
        System.out.println("Please enter your Username: ");
        Scanner sc = new Scanner(System.in);
        CsvManager csvManager = new CsvManager();
        String[] csvData = csvManager.readCsvFileToString();

        username = sc.nextLine();
        if (checkUsernamePosition(csvData, username) == -1)
        {
            System.out.println("No such username exists please try again");
            return false;
        }
        else
        {
            System.out.println("You are trying to log in as: " + username);
            return true;
        }
    }
    boolean checkPassword()
    {
        // if the password matches the username will return true else will return false
        Scanner sc = new Scanner(System.in);
        CsvManager csvManager = new CsvManager();
        String[] csvData = csvManager.readCsvFileToString();
        int usernamePosition = checkUsernamePosition(csvData, username);
        String salt = csvData[usernamePosition + 1];
        String targetPassword = csvData[usernamePosition + 2];
        SecurityManager securityManager = new SecurityManager();

        password = sc.nextLine();
        if (!securityManager.doHashing(salt, password).equals(targetPassword))
        {
            System.out.println("Your password is incorrect please try again");
            return false;
        }
        else
        {
            return true;
        }
    }
    boolean userLogin()
    {
        // Simply checks both the username and password are valid and match
        if (checkUsername())
        {
            return checkPassword();
        }
        return false;
    }
}

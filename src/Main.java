import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Main
{
    static String loggedInAs;
    public static void main(String[] args)
    {
        // Creates a new csvFileManager and checks if the user data file exists and if not creates it
        CsvManager csvFileManager = new CsvManager();
        csvFileManager.checkFileExists();
        runLoginMenu();
    }
    static void printMainMenu()
    {
        System.out.println();
        System.out.println("Welcome to Treasure Hunt");
        System.out.println("a. Login");
        System.out.println("b. Create account");
        System.out.println("c. Exit");
    }
    static void runLoginMenu()
    {
        // Runs a looping menu with input verification on user inputs
        boolean loginMenuControl = true;
        Scanner input = new Scanner(System.in);
        printMainMenu();
        do
        {
            String loginMenuInput = input.nextLine();
            if(loginMenuInput.equals("a") || loginMenuInput.equals("b") || loginMenuInput.equals("c"))
            {
                if (loginMenuInput.equals("a"))
                {
                    attemptToLogin();
                }
                else if (loginMenuInput.equals("b"))
                {
                    createNewAccount();
                }
                else
                {
                    System.out.println("Thank you for selecting exit");
                    loginMenuControl = false;
                }
            }
            else
            {
                System.out.println("Please enter either a, b or c");
            }
        } while(loginMenuControl);
    }
    static void attemptToLogin()
    {
        System.out.println("Thank you for selecting login");
        AccountManager userManager = new AccountManager();
        if (userManager.userLogin())
        {
            loggedInAs = userManager.username;
            runLoggedInMenu();
        }
        else
        {
            System.out.println("You have failed to log in sorry");
            printMainMenu();
        }
    }
    static void createNewAccount()
    {
        // Creates and saves all the data required to have an account
        System.out.println("Thank you for selecting create account");
        AccountManager userManager = new AccountManager();
        userManager.createAccount();
        SecurityManager securityManager = new SecurityManager();
        // Creates a salt to ensure the users password is extra secure
        String salt = securityManager.addSalt();
        // Hash the password, so it is not stored in plaintext and is less venerable
        String hashedPassword = securityManager.doHashing(salt, userManager.password);
        // Creates a score for the player
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        String score = Integer.toString(randomNum);
        // Asks the player how they like to enjoy games, so we can create the group leaderboards later based on Bartle
        CheckPlayerTypes check = new CheckPlayerTypes();
        String isSocializer = check.socializer();
        String isExplorer = check.explorer();
        String isAchiever = check.achiever();
        String isKiller = check.killer();

        // Writes all the user data to the file
        CsvManager csvManager = new CsvManager();
        csvManager.writeDataToCsv(userManager.username,
                salt,
                hashedPassword,
                score,
                isSocializer,
                isExplorer,
                isAchiever,
                isKiller);
        printMainMenu();
    }

    static void printLoginMenu()
    {
        System.out.println();
        System.out.println("a. View global leaderboard");
        System.out.println("b. View group leaderboards");
        System.out.println("c. Log out");
    }

    static void runLoggedInMenu()
    {
        // The menu once logged in lets you view the various types of leaderboards
        boolean loggedInMenuControl = true;
        Scanner input = new Scanner(System.in);
        printLoginMenu();

        do
        {
            String LoggedInMenuInput = input.nextLine();
            if(LoggedInMenuInput.equals("a") || LoggedInMenuInput.equals("b") || LoggedInMenuInput.equals("c"))
            {
                if (LoggedInMenuInput.equals("a"))
                {
                    System.out.println("Thank you for selecting to view the global leaderboard");
                    LeaderboardManager leaderboardManager = new LeaderboardManager();
                    leaderboardManager.createGlobalLeaderboard();
                    printLoginMenu();
                }
                else if (LoggedInMenuInput.equals("b")) {
                    System.out.println("Thank you for selecting to view group leaderboards");
                    LeaderboardManager leaderboardManager = new LeaderboardManager();
                    leaderboardManager.createGroupLeaderboards(loggedInAs);
                    printLoginMenu();
                }
                else
                {
                    System.out.println("Thank you for selecting Log out");
                    printMainMenu();
                    loggedInMenuControl = false;
                }
            }
            else
            {
                System.out.println("Please enter either a, b or c");
            }
        } while(loggedInMenuControl);
    }
}
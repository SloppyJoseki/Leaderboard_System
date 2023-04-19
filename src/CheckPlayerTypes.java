import java.util.Scanner;

public class CheckPlayerTypes
{

    // The functions used to check what types of player the user is, so they can be matched for group leaderboards
    String socializer()
    {
        Scanner sc = new Scanner(System.in);
        boolean menuControl = true;
        System.out.println("As a player do you generally prefer the social aspects of a game? \n y or n");
        do
        {
            String input = sc.nextLine();
            if (input.equals("y"))
            {
                menuControl = false;
            }
            else if (input.equals("n"))
            {
                return "0";
            }
            else
            {
                System.out.println("Please enter either y or n");
            }
        } while(menuControl);
        return "1";
    }
    String explorer()
    {
        Scanner sc = new Scanner(System.in);
        boolean menuControl = true;
        System.out.println("As a player do you generally prefer to explore and immerse yourself in the game world?"
                + " \n y or n");
        do
        {
            String input = sc.nextLine();
            if (input.equals("y"))
            {
                menuControl = false;
            }
            else if (input.equals("n"))
            {
                return "0";
            }
            else
            {
                System.out.println("Please enter either y or n");
            }
        } while(menuControl);
        return "1";
    }
    String achiever()
    {
        Scanner sc = new Scanner(System.in);
        boolean menuControl = true;
        System.out.println("As a player do you generally prefer to gain concrete measures of success such as points or" +
                " levels? \n y or n?");
        do
        {
            String input = sc.nextLine();
            if (input.equals("y"))
            {
                menuControl = false;
            }
            else if (input.equals("n"))
            {
                return "0";
            }
            else
            {
                System.out.println("Please enter either y or n");
            }
        } while(menuControl);
        return "1";
    }
    String killer()
    {
        Scanner sc = new Scanner(System.in);
        boolean menuControl = true;
        System.out.println("As a player do you generally prefer to focus on performing better than other players?"
                + "\n y or n");
        do
        {
            String input = sc.nextLine();
            if (input.equals("y"))
            {
                menuControl = false;
            }
            else if (input.equals("n"))
            {
                return "0";
            }
            else
            {
                System.out.println("Please enter either y or n");
            }
        } while(menuControl);
        return "1";
    }
}

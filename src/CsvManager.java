import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CsvManager
{
    final int dataPerUser = 8; // The number of variables saved for each user
    final String filePath = "userInformation.txt";
    void checkFileExists()
    {
        // Used at the start of the program to make sure it can write to or read from a file
        try
        {
            File file = new File(filePath);
            if (file.createNewFile())
            {
                System.out.println("A text file has been created to store the users information");
            }
        }
        catch (IOException e)
        {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }
    String[] readCsvFileToString()
    {
        // Reads the csv file into an array of strings separated by the commas
        StringBuilder data = new StringBuilder();
        try
        {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine())
            {
                data.append(reader.nextLine());
            }
            String csv = data.toString();
            return csv.split(",");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new String[0];
    }
    User[] readCsvStringArrayToArrayOfUsers(String[] csvString)
    // Takes the array of Strings and writes it into an array of Users
    {
        User[] theUsers = new User[(csvString.length / dataPerUser)]; // Size based on
        int dataPointer = 0;

        for (int i = 0; i < (csvString.length / dataPerUser); i++)
        {
            theUsers[i] = new User(csvString[dataPointer],
                    csvString[dataPointer + 1],
                    csvString[dataPointer + 2],
                    Integer.parseInt(csvString[dataPointer + 3]),
                    Integer.parseInt(csvString[dataPointer + 4]),
                    Integer.parseInt(csvString[dataPointer + 5]),
                    Integer.parseInt(csvString[dataPointer + 6]),
                    Integer.parseInt(csvString[dataPointer + 7]));
            dataPointer += 8;
        }
        return theUsers;
    }
    void writeDataToCsv(String username,      // Function used to write data about users into the main csv file
                        String salt,
                        String password,
                        String score,
                        String isSocializer,
                        String isExplorer,
                        String isAchiever,
                        String isKiller)
    {
        try
        {
            // Writes the data separated by commas, so it can be easily split apart later
            FileWriter writer = new FileWriter(filePath, true);
            writer.write("\n");
            writer.write(username +",");
            writer.write(salt + ",");
            writer.write(password + ",");
            writer.write(score + ",");
            writer.write(isSocializer + ",");
            writer.write(isExplorer + ",");
            writer.write(isAchiever + ",");
            writer.write(isKiller + ",");
            writer.close();
        }
        catch (IOException e) // Encase of error reading file
        {
            e.printStackTrace();
        }
    }
}

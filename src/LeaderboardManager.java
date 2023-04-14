public class LeaderboardManager
{
    public int findUserIndex(User[] Users, String loggedInAs)
    {
        for (int i = 0; i < Users.length; i++) {
            if (Users[i].username.equals(loggedInAs)) {
                return i;
            }
        }
        return  0;
    }
    public User[] setUpSortedUserArray()
    {
        // Sets up the users data in an array of objects
        CsvManager csvManager = new CsvManager();
        String[] userData =  csvManager.readCsvFileToString();
        User[] theUsers = csvManager.readCsvStringArrayToArrayOfUsers(userData);

        // Implementing a bubble sort to sort the user array by score
        for (int j = 0; j < theUsers.length - 1; j++)
        {
            for (int q = 0; q < theUsers.length - 1; q++)
            {
                User u = theUsers[q];
                User next = theUsers[q + 1];
                if (u.score < next.score)
                {
                    theUsers[q] = theUsers[q+1];
                    theUsers[q+1] = u;
                }
            }
        }
        return theUsers;
    }
    public void createGlobalLeaderboard()
    {
        User[] theUsers = setUpSortedUserArray();

        int counter = 1;
        for (User user : theUsers)
        {
            System.out.println(counter + ". " + user.username + "\n" + " Score: " + user.score);
            counter++;
        }
    }
    public void createGroupLeaderboards(String loggedInAs)
    {
        User[] theUsers = setUpSortedUserArray();

        int loggedInUserIndex = findUserIndex(theUsers, loggedInAs);


        if (theUsers[loggedInUserIndex].isSocializer == 1)
        {
            System.out.println("\n This is the leaderboard for Socializers: \n ");
            int counter = 1;
            for (User theUser : theUsers) {
                if (theUser.isSocializer == 1) {
                    System.out.println(counter + ". " + theUser.username + "\n" + " Score: " + theUser.score);
                    counter++;
                }
            }
        }
        if (theUsers[loggedInUserIndex].isExplorer == 1)
        {
            System.out.println("\n This is the leaderboard for Explorers: \n ");
            int counter = 1;
            for (User theUser : theUsers) {
                if (theUser.isExplorer == 1) {
                    System.out.println(counter + ". " + theUser.username + "\n" + " Score: " + theUser.score);
                    counter++;
                }
            }
        }
        if (theUsers[loggedInUserIndex].isAchiever == 1)
        {
            System.out.println("\n This is the leaderboard for Achievers: \n ");
            int counter = 1;
            for (User theUser : theUsers) {
                if (theUser.isAchiever == 1) {
                    System.out.println(counter + ". " + theUser.username + "\n" + " Score: " + theUser.score);
                    counter++;
                }
            }
        }
        if (theUsers[loggedInUserIndex].isKiller == 1)
        {
            System.out.println("\n This is the leaderboard for Killers: \n ");
            int counter = 1;
            for (User theUser : theUsers) {
                if (theUser.isKiller == 1) {
                    System.out.println(counter + ". " + theUser.username + "\n" + " Score: " + theUser.score);
                    counter++;
                }
            }
        }
    }
}

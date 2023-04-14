public class User
{
    // Data required for each user starting with information necessary to login then the information for the leaderboards
    String username;
    String salt;
    String password;
    int score;
    int isSocializer;
    int isExplorer;
    int isAchiever;
    int isKiller;

    // Constructor sets up all the values for each user
    User(String name,
         String sodiumChloride,
         String pass,
         int points,
         int playerType1,
         int playerType2,
         int playerType3,
         int playerType4)
    {
        username = name;
        salt = sodiumChloride;
        password = pass;
        score = points;
        isSocializer = playerType1;
        isExplorer = playerType2;
        isAchiever = playerType3;
        isKiller = playerType4;
    }
}

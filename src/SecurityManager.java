import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
public class SecurityManager
{
    public String addSalt()
    {
        // Generates a random string of letters and numbers to append to a users password when hashing
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        StringBuilder completedSalt = new StringBuilder();
        for (byte b : salt)
        {
            completedSalt.append(String.format("%02x", b));
        }
        return completedSalt.toString();
    }
    public String doHashing(String salt, String password)
    {
        // Takes in a users password and a salt to create a hashed password for safer storage
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            password = password + salt;
            messageDigest.update(password.getBytes());
            byte[] byteArray = messageDigest.digest();
            StringBuilder builtString = new StringBuilder();

            for (byte b : byteArray)
            {
                builtString.append(String.format("%02x", b));
            }
            return builtString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}

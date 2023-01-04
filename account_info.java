import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class account_info {

    protected static HashMap<String, Integer> id_info = new HashMap<>();
    protected static HashMap<String, HashMap<String, Integer>> acct_info = new HashMap<>();
    protected static int id;

    protected int check_admin(String username, String password) { // checking for a correct username and password // this is how users login
        if (acct_info.isEmpty() || id_info.isEmpty())
            return 0;
        if (acct_info.get(username).equals(password))
            return acct_info.get(username).get(password);

        return 0;
    }


    private static String create_admin(String firstName, String lastName, String Username, String Password) { // this is initiated when the create account button is clicked
        id = acct_info.size();


        return null;

    }
}





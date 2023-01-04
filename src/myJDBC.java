import java.sql.*;

public class myJDBC {


    public static String check_database(String string) throws SQLException { // Anyone can view reports as an guest it is simplified to
                // but it'll be sent in as the action --> view, insert (objective)
                if(string!="view")
                    string = "insert into staff" +
                                "(first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan)" +
                                "VALUES\n" +
                                "("+string+")";
                else
                    string = "select * from staff";

                //<first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan>
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-wpgui", "root", "Harlem3140!");

                    Statement statement = connection.createStatement();

                    // results returned from the database
                    ResultSet result = statement.executeQuery(string); // hopefully it works correctly

                    switch (string) { // the string will change to the correct return value within this switch statement
                    case "view":
                    while (result.next()) {
                        string += (result.getString("gender") +", "+ result.getString("age")+", "+result.getString("sexuality") +", "+ result.getString("religion")+", "+result.getString("experience_level") +", "+ result.getString("political_office")+", "+result.getString("position") +", "+result.getString("duration_of_employment")+", "+result.getString("annual_salary")+", "+result.getString("hourly_salary")+", "+result.getString("benefit_plan"));
                        string += "\n";
                    }


                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                return string;
         // Finished with View Reports

    }


}

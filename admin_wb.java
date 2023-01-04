import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


// Change employee IDs to ints ***********
public class admin_wb {
    final static int SIZE_ARRAY = 13;
    static double GENDER_RATIO; // Looks at the ratio of gender always !
    static double SEX_RATIO;

     // EXPERIENCE LEVEL : This ratio can be utilized by any company helps with bias, includes education & work exp
    /* NONE = 0 , HS/GED = 1 , BS = 2 , MS = 3 , PHD = 4 , un-related Intern/un-related experience = +0.25 , related Intern / related experience = +0.5*/
    /* Position Rank : Intern, Junior, Intermediate, Senior 1, Senior 2, Board, CEO --> 0 - 6 */
    /*make an array with each office*/
    /*Duration of Employment: 0-3 months, 6-9 months, <1 year, <2 years, <3 years, <5 years, <10 years, <20 years, 20+ years*/
    /*Annual Salary: 20-35, <50, <75, <100, <125, <150, <175, <200, 200+  This would be for a smaller company*/
    /*Hourly Salary: 10-100 hrly*/
    /*Benefit Plan: Based on the company but Basic, Main, Exclusive, Premium, 0-3 */
    // Other companies may include sign on bonus, stocks etc.
    // <first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan>
   public static void calculate_data(){
       String [] GENDER = {"Cisgender Woman","Cisgender Man","Transgender","Non-Binary"}; // These positions will remain recognized by number the i value to keep track
       int AGE;
       String [] SEXUALITY = {"Straight","Bisexual", "Gay/Lesbian", "Asexual", "Pansexual"};
       String [] RELIGION = {"Christian", "Catholic", "Jewish"};
       String [] EXPERIENCE_LEVEL = { "NONE", "High School/GED", "Bachelor's", "Masters", "PHD"};
       double UNRELATEDPOS = 0.25;
       double RELATEDPOS = 0.50;
       String [] POSITION_RANK = {"Intern", "Junior", "Intermediate", "Senior 1", "Senior 2", "Board", "CEO"};
       String [] POLS_OFFICE = {"Democratic", "Republican", "Atheist"};
       String [] DURATION_EMP = {"0-3 months","6-9 months","<1 year","<2 years","<3 years","<5 years","<10 years","<20 years","20+ years"};
       String [] ANNUAL_SALARY = {"20-35","<50","<75","<100","<125","<150","<175","<200","200+"};
       int HOURLY_SALARY; // 10-100
       String [] BENEFIT_PLAN = {"Basic", "Main", "Exclusive", "Premium"};

       HashMap<String,Integer> temp = new HashMap<>(); // Using this Hashmap to Count values






   }



    public static void view_reports() {
        HashMap<String,Integer> employee_amounts = new HashMap<>(); // This will be a simple count based on each field with ranges


    }


    private static void write_employee(String [] data) throws IOException {
        // initialize a string

        try {

            FileWriter fw
                    = new FileWriter("C:\\Users\\ashly\\OneDrive\\Documents\\employee_project\\employee_data.txt");


            fw.write("<");
            for (int i = 0; i < data.length; i++) {
                fw.write(data[i]);

                if (i!=data.length-1) fw.write(",");
            }

            fw.write(">");

            System.out.println("Successfully Added to DataBase"); // This message needs to appear to GUI

            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }


    }

    private static String [] read_employee(String first_name, String last_name) throws IOException{
        String [] data = new String[SIZE_ARRAY];
        int fn_length = first_name.length();
        int ln_length = last_name.length();
        try {


            File file = new File("C:\\Users\\ashly\\OneDrive\\Documents\\employee_project\\employee_data.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            boolean found_data = false;
            String line;

            while ((line = br.readLine()) != null && !found_data) {
                if(br.readLine().substring(1, fn_length+ln_length+2) == first_name+","+last_name) {
                    found_data = true;
                    data = line.split(",");
                    data[0] = data[0].substring(1);
                    data[SIZE_ARRAY-1] = data[SIZE_ARRAY-1].substring(0,data[SIZE_ARRAY-1].length()-1);
                    return data;
                }
            }


        } catch (Exception e) {
            // Hopefully there's no issues right ? // but if there is
            String [] result = {"Error"};
         return result; // Check for error in return
        }

        return data;
    }





}

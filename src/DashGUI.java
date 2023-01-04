import javax.swing.* ;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

// EXPERIENCE LEVEL : This ratio can be utilized by any company helps with bias, includes education & work exp
/* NONE = 0 , HS/GED = 1 , BS = 2 , MS = 3 , PHD = 4 , un-related Intern/un-related experience = +0.25 , related Intern / related experience = +0.5*/
/* Position Rank : Intern, Junior, Intermediate, Senior 1, Senior 2, Board, CEO --> 0 - 6 */
/*make an array with each office*/
/*Duration of Employment: 0-3 months, 6-9 months, 9- 12 months, <2 years, <3 years, <5 years, <10 years, <20 years, 20+ years*/
/*Annual Salary: 20-35, <50, <75, <100, <125, <150, <175, <200, 200+  This would be for a smaller company*/
/*Hourly Salary: 10-100 hrly*/
/*Benefit Plan: Based on the company but Basic, Main, Exclusive, Premium, 0-3 */
// Other companies may include sign on bonus, stocks etc.
// <first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan>
public class DashGUI extends JFrame implements ActionListener, ComponentListener  {
    final int button_WIDTH = 20;
    final int button_HEIGHT = 25;
    final int frame_WIDTH = 500;
    final int frame_HEIGHT = 500;

    static JTextField username;
    static JTextField passwrd;
    static JButton login_button;
    static JPanel panel;
    static JFrame dashboard;

    public JFrame dashboard() { // dashboard design
        dashboard = new JFrame();
        dashboard.setResizable(false);
        dashboard.setLocation(new Point(400,100));
        dashboard.setLayout(new BorderLayout());
        dashboard.setTitle("The DAshBoard");
        dashboard.setSize(frame_WIDTH, frame_HEIGHT);

        // Creating the login panel
        panel = new JPanel(null);
        panel.setBackground(Color.pink);
        panel.setOpaque(true); panel.setPreferredSize(getPreferredSize());
        JLabel login_label = new JLabel("Login to Admin Account"); login_label = FontSize(login_label);
        JLabel user_name = new JLabel("Username");
        JLabel pw_name = new JLabel("Password");
        username = new JTextField("Username");
        passwrd = new JTextField("Password"); // This text field will be shown twice, to reenter password
        login_button = new JButton("Login");
        JButton view_reports = new JButton("View Reports");



        // TextField Layouts

        username = TextLayout(username);
        passwrd = TextLayout(passwrd);
        user_name.setBounds(150, 48, 70, 20);
        panel.add(user_name);
        username.setBounds(150, 67, 193, 28);
        panel.add(username);
        pw_name.setBounds(150, 100, 70, 20);
        panel.add(pw_name);
        passwrd.setBounds(150, 125, 193, 28);
        panel.add(passwrd);

        login_button.setBounds(193, 160, 90, 25);
        login_button.setForeground(Color.WHITE);
        login_button.setBackground(Color.BLACK);
        panel.add(login_button);

        view_reports.setBounds(180, 190, 120, 25);
        view_reports.setForeground(Color.WHITE);
        view_reports.setBackground(Color.BLACK);
        panel.add(view_reports);

        dashboard.add(panel, BorderLayout.CENTER);


        login_button.addActionListener(this);
        view_reports.addActionListener(this);



//<first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan>






        return dashboard;
    }
    public JButton ButtonLayout(JButton bt) {
        bt.setPreferredSize(new Dimension(button_WIDTH, button_HEIGHT));
        bt.setForeground(Color.gray);
        return bt;
    }public JTextField TextLayout(JTextField tf) {
        tf.setPreferredSize(new Dimension(button_WIDTH, button_HEIGHT));
        tf.setForeground(Color.gray);
        return tf;
    } public JLabel FontSize(JLabel jl) {
        jl.setFont(new Font ("Verdana", Font.PLAIN, 18));
        return jl;
    }


    @Override
    public void actionPerformed(ActionEvent e) { // My action listener to add to my panel


        if ( e.getActionCommand().equals("Login")) {
            String Username = username.getText();
            String Password = passwrd.getText();
            account_info checkAcct = new account_info();

            int id = checkAcct.check_admin(Username, Password);
            id = 4;
            if ( id!=0) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                // change the panel to admin design
                //panel = admin_acct();
                JPanel form = admin_acct(); // Get the screen to change
                dashboard.add(form);

            }
            else
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect");

        } else {
            // else its the view reports button
            // the panel will be updated
            myJDBC db = new myJDBC();



        }
    }

    public static JPanel admin_acct(){ // designing what the admin account dashboard looks like looks like
        JPanel form = new JPanel();
        String [] GENDER = {"Gender","Cisgender Woman","Cisgender Man","Transgender","Non-Binary"};
        String [] SEXUALITY = {"Sexuality","Straight","Bisexual", "Gay/Lesbian", "Asexual", "Pansexual","NONE"};
        String [] RELIGION = {"Religion","Christian", "Catholic", "Jewish","NONE"};
        String [] EXPERIENCE_LEVEL = { "Education","NONE", "High School/GED", "Bachelor's", "Masters", "PHD"};
        String [] TOTAL_POS = {"Unrelated Positions","0","1","2","3","4","5"};
        String [] POSITION_RANK = {"Experience","Intern", "Junior", "Intermediate", "Senior 1", "Senior 2", "Board", "CEO"};
        String [] POLS_OFFICE = {"Political Office","Democratic", "Republican", "NONE"};
        String [] DURATION_EMP = {"Duration of Employment","0-3 months","6-9 months","<1 year","<2 years","<3 years","<5 years","<10 years","<20 years","20+ years"};
        String [] ANNUAL_SALARY = {"Annual Salary","20-35","<50","<75","<100","<125","<150","<175","<200","200+"};
        String [] BENEFIT_PLAN = {"Benefit Plan","Basic", "Main", "Exclusive", "Premium"};

        JTextField input_first = new JTextField("First Name");
        JTextField input_last = new JTextField("Last Name");
        JComboBox<String> gender_list = new JComboBox<>(GENDER);
        JTextField age_list = new JTextField("Age");
        JComboBox<String> sex_list = new JComboBox<>(SEXUALITY);
        JComboBox<String> relg_list = new JComboBox<>(RELIGION);
        JComboBox<String> explevel_list = new JComboBox<>(EXPERIENCE_LEVEL);
        JComboBox<String> unrelated_list = new JComboBox<>(TOTAL_POS);
        // I'll need a label here
        TOTAL_POS[0] = "Related Positions";
        JComboBox<String> related_list = new JComboBox<>(TOTAL_POS);
        JComboBox<String> rank_list = new JComboBox<>(POSITION_RANK);
        JComboBox<String> pols_list = new JComboBox<>(POLS_OFFICE);
        JComboBox<String> duremp_list = new JComboBox<>(DURATION_EMP);
        JComboBox<String> annual_list = new JComboBox<>(ANNUAL_SALARY);
        JTextField hourly_list = new JTextField("Hourly Salary");
        JComboBox<String> benefit_list = new JComboBox<>(BENEFIT_PLAN);
        JLabel form_label = new JLabel("Input in Employee Data");
        form.add(input_first);
        form.add(input_last);
        form.add(gender_list);
        form.add(age_list);
        form.add(sex_list);
        form.add(relg_list);
        form.add(explevel_list);
        form.add(unrelated_list);
        form.add(related_list);
        form.add(rank_list);
        form.add(pols_list);
        form.add(duremp_list);
        form.add(annual_list);
        form.add(hourly_list);
        form.add(annual_list);
        form.add(benefit_list);

        return form;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,10);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }







}

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
    final int buttonWIDTH = 20;
    final int buttonHEIGHT = 25;
    final int frameWIDTH = 500;
    final int frameHEIGHT = 500;

    static JTextField usernameText;
    static JPasswordField passwordText;
    static JButton loginButton;
    static JPanel mainPanel;
    static JFrame dashboard;


    public JFrame dashboard() { // dashboard design
        dashboard = new JFrame();
        dashboard.setResizable(false);
        dashboard.setLocation(new Point(400,100));
        dashboard.setLayout(new BorderLayout());
        dashboard.setTitle("The DAshBoard");
        dashboard.setSize(frameWIDTH, frameHEIGHT);

        // general size and color of the login panel // now being created in its own class
        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setOpaque(true); mainPanel.setPreferredSize(getPreferredSize());
        JLabel loginLabel = new JLabel("Login to Admin Account"); loginLabel = FontSize(loginLabel);
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameText = new JTextField(20);
        passwordText = new JPasswordField(); // This text field will be shown twice, to reenter password
        loginButton = new JButton("Login");
        JButton viewReports = new JButton("View Reports");



        // Login
        mainPanel = login_acct(usernameText, passwordText, usernameLabel,passwordLabel,viewReports);
        dashboard.add(mainPanel, BorderLayout.CENTER);


        loginButton.addActionListener(this);
        viewReports.addActionListener(this);








        return dashboard;
    }
    public JButton ButtonLayout(JButton bt) {
        bt.setPreferredSize(new Dimension(buttonWIDTH, buttonHEIGHT));
        bt.setForeground(Color.gray);
        return bt;
    }public JTextField TextLayout(JTextField tf) {
        tf.setPreferredSize(new Dimension(buttonWIDTH, buttonHEIGHT));
        tf.setForeground(Color.gray);
        return tf;
    } public JLabel FontSize(JLabel jl) {
        jl.setFont(new Font ("Verdana", Font.PLAIN, 18));
        return jl;
    }


    @Override
    public void actionPerformed(ActionEvent e) { // My action listener to add to my panel


        if ( e.getActionCommand().equals("Login")) { // once the login button is selected
            account_info checkAcct = new account_info();

            int id = checkAcct.check_admin(usernameText.getText(),passwordText.getText());
            id = 4;
            if ( id!=0) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                // change the panel to admin design
                dashboard.removeAll();
                mainPanel = admin_acct();

//                JPanel form = admin_acct();
//                panel.add(form);
//                dashboard.add(form);

            }
            else
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect");

        } else { // Else the view reports button is selected
            myJDBC db = new myJDBC();




        }
    }

    public static JPanel login_acct(JTextField usernameText, JPasswordField passwordText, JLabel usernameLabel, JLabel passwordLabel,JButton viewReports){
        JPanel loginPanel = new JPanel();
        loginPanel = new JPanel(null);
        loginPanel.setBackground(Color.pink);
        loginPanel.setOpaque(true);
        usernameLabel.setBounds(150, 48, 70, 20);
        loginPanel.add(usernameLabel);
        usernameText.setBounds(150, 67, 193, 28);
        loginPanel.add(usernameText);
        passwordLabel.setBounds(150, 100, 70, 20);
        loginPanel.add(passwordLabel);
        passwordText.setBounds(150, 125, 193, 28);
        loginPanel.add(passwordText);

        loginButton.setBounds(193, 160, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginPanel.add(loginButton);

        viewReports.setBounds(180, 190, 120, 25);
        viewReports.setForeground(Color.WHITE);
        viewReports.setBackground(Color.BLACK);
        loginPanel.add(viewReports);

        return loginPanel;

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
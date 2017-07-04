import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Administrator on 7/3/2017.
 */
public class GraphicInterface extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    JTextField firstName, lastName, birthDate, eMail, password, findResult;
    JButton add, find, remove;


    public GraphicInterface() {
        super("UsersBase");
        final JPanel panel = new JPanel();
        firstName = new JTextField("First Name",70);
        lastName = new JTextField("Last Name",70);
        birthDate = new JTextField("Date of Birth (1000-01-01)",70);
        eMail = new JTextField("eMail",70);
        password = new JTextField("Password",70);
        findResult = new JTextField(70);
        add = new JButton("Add");
        find = new JButton("Find");
        remove = new JButton("Remove");
        DefaultTableModel myModel = new DefaultTableModel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add, FlowLayout.LEFT);
        buttonPanel.add(find, FlowLayout.CENTER);
        buttonPanel.add(remove, FlowLayout.RIGHT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(firstName);
        panel.add(lastName);
        panel.add(birthDate);
        panel.add(eMail);
        panel.add(password);
        panel.add(buttonPanel);
        panel.add(findResult);
        setContentPane(panel);
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringFirstName = "'" + firstName.getText() + "'";
                String stringLastName = "'" + lastName.getText() + "'";
                String stringBirthDate = "'" + birthDate.getText() + "'";
                String stringEMail = "'" + eMail.getText() + "'";
                String stringPassword = "'" + password.getText() + "'";
                String query = "INSERT INTO users (firstname, lastname, birthdate, email, password) VALUES (" + stringFirstName + ", " + stringLastName + ", " + stringBirthDate  + ", " + stringEMail + ", " + stringPassword + ")";
                try {
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
                    statement.execute(query);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                firstName.setText("First Name");
                lastName.setText("Last Name");
                birthDate.setText("Date of Birth (1000-01-01)");
                eMail.setText("eMail");
                password.setText("Password");
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringFirstName = "'" + firstName.getText() + "'";
                String stringLastName = "'" + lastName.getText() + "'";
                String stringBirthDate = "'" + birthDate.getText() + "'";
                String stringEMail = "'" + eMail.getText() + "'";
                String query = "DELETE FROM users WHERE firstname =" + stringFirstName + "and lastname = " + stringLastName + "and birthdate = " + stringBirthDate + "and email = " + stringEMail;
                try {
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
                    statement.execute(query);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                firstName.setText("First Name");
                lastName.setText("Last Name");
                birthDate.setText("Date of Birth (1000-01-01)");
                eMail.setText("eMail");
                password.setText("Password");
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringEMailFinding = "'" + eMail.getText() + "'";
                String query = "SELECT * FROM users WHERE email = " + stringEMailFinding;
                try {
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        String stringFirstName = resultSet.getString(2);
                        String stringLastName = resultSet.getString(3);
                        String stringBirthDate = resultSet.getString(4);
                        String stringEMail = resultSet.getString(5);
                        String stringPassword = resultSet.getString(6);
                        findResult.setText("First Name : " + stringFirstName + " | Last Name : " + stringLastName + " | Date Of Birth :  " + stringBirthDate + " | eMail : " + stringEMail + " | Password : " + stringPassword);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

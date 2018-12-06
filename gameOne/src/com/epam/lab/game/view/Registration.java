package com.epam.lab.game.view;

import com.epam.lab.game.exselUtils.ExcelWriter;
import com.epam.lab.game.exselUtils.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Registration extends JDialog {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JTextField tfEmail;
    private boolean succeeded;

    public Registration(Frame parent) {
        super(parent, "Registration", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();


        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("NAME: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(nameLabel, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        JLabel lbPassword = new JLabel("PASSWORD");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

        JLabel emailLabel=new JLabel("EMAIL");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(emailLabel, cs);

        tfEmail = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfEmail, cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton registerButton = new JButton("REGISTER");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validation()){
                    try {
                        ExcelWriter.parse(new User(getUsername(),getEmail(),getPassword()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(Registration.this,
                            "Hi " + getUsername() + "! Now you can logged in.",
                            "Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();

                }
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tfUsername.setText("");
                pfPassword.setText("");
                tfEmail.setText("");
            }
        });
        JPanel bp = new JPanel();
        bp.add(registerButton);
        bp.add(resetButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }


    private String getUsername() {
        return tfUsername.getText().trim();
    }

    private String getEmail() {
        return tfEmail.getText().trim();
    }

    private String getPassword() {
        return new String(pfPassword.getPassword());
    }


    public boolean validation() {
        if (getUsername().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Name");
            return false;
        } else if (getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Password");
            return false;
        } else if (getEmail().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Email");
            return false;
        }else
            return true;
    }
    public boolean isSucceeded() {
        return succeeded;
    }
}

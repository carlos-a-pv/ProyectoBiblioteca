package controller;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PrestamoViewController {


    public void onClickPrestar() {
        JFrame frame = new JFrame("Capturar Texto");

        JTextField textField = new JTextField(20);
        JButton button = new JButton("Capturar");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String textoIngresado = textField.getText();
                JOptionPane.showMessageDialog(frame, "Texto capturado: " + textoIngresado);
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(textField);
        frame.add(button);

        frame.setSize(300, 100);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

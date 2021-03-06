package CarShop.uis;


import CarShop.model.Client;
import CarShop.Shop;
import sun.util.calendar.JulianCalendar;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewClientUI extends JPanel {
    private JFrame frame;
    Shop shop;
    JFormattedTextField phoneNumber;

    public NewClientUI (Shop shop) {
        this.shop = shop;
        frame = new JFrame("New Client");

        frame.setMinimumSize(new Dimension(500, 200));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(createNewClient());

        frame.pack();
        frame.setVisible(true);
    }

    public JPanel createNewClient () {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());


        JLabel lClient = new JLabel("Client");
        lClient.setFont(new Font ("Serif", Font.PLAIN, 20));
        pan.add(lClient, new GridBagConstraints(1, 0, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,20,0), 50,0));

        JLabel lFullName = new JLabel("Enter Full Name");
        pan.add(lFullName, new GridBagConstraints(0, 1, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        JTextField tFullName = new JTextField();
        tFullName.setColumns(30);
        pan.add(tFullName, new GridBagConstraints(1, 1, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,10,0), 0,0));


        JLabel lAddress = new JLabel("Enter Address");
        pan.add(lAddress, new GridBagConstraints(0, 2, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        JTextField tAddress = new JTextField();
        tAddress.setColumns(30);
        pan.add(tAddress, new GridBagConstraints(1, 2, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,10,0), 0,0));

        JLabel lPhone = new JLabel("Enter Phone Number");
        pan.add(lPhone, new GridBagConstraints(0, 3, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 10,0));

        setFormatForPhoneNumber();
        phoneNumber.setColumns(10);
        pan.add(phoneNumber, new GridBagConstraints(1, 3, 1, 1,0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,220), 0,0));

        JButton cancel = new JButton("Cancel");
        pan.add(cancel, new GridBagConstraints(1, 4,1,1,0,0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,120,0,0), 0,0));

        JButton addCllient = new JButton("Add");
        pan.add(addCllient, new GridBagConstraints(2, 4,1,1,0,0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,-60,0,0), 0,0));

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        addCllient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client c = new Client();
                c.setFullName(tFullName.getText());
                c.setAdress(tAddress.getText());
                c.setPhoneNumber(phoneNumber.getText());

                shop.addToClientBase(c);
//                shop.createClientBase();

                new ShopWindow(shop);

                frame.dispose();
            }
        });

        return pan;
    }
    private void setFormatForPhoneNumber () {
        MaskFormatter formatter1 = null;
        try {
            formatter1 = new MaskFormatter("(+380##)###-##-##");
        } catch (java.text.ParseException exc) {
            exc.printStackTrace();
        }
        phoneNumber = new JFormattedTextField(formatter1);

    }

}

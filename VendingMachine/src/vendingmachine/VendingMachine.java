package vendingmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VendingMachine extends JFrame implements ActionListener {

    // Stock
    int stockCola = 10, stockPepsi = 8, stockSprite = 5, stock7up = 10, stockWater = 20, stockChips = 5;

    // Prices
    final int priceCola = 35, pricePepsi = 30, priceSprite = 25, price7up = 30, priceWater = 20, priceChips = 40;

    JLabel display;

    // Buttons
    JButton colaBtn, pepsiBtn, spriteBtn, upBtn, waterBtn, chipsBtn;

    // Spinners
    JSpinner colaSpinner, pepsiSpinner, spriteSpinner, upSpinner, waterSpinner, chipsSpinner;

    VendingMachine() {
        setTitle("UITS Vending Machine");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        Color darkRed = new Color(139, 0, 0);

        // ---------- MENU BAR ----------
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(darkRed);
        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem stockItem = new JMenuItem("Stock");
        JMenuItem exitItem = new JMenuItem("Exit");

        menu.add(homeItem);
        menu.add(aboutItem);
        menu.add(stockItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        homeItem.addActionListener(e -> display.setText("Select an item"));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Developer: Sabbirul Islam - CSC"));
        exitItem.addActionListener(e -> System.exit(0));
        stockItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Cola: " + stockCola +
                        "\nPepsi: " + stockPepsi +
                        "\nSprite: " + stockSprite +
                        "\n7up: " + stock7up +
                        "\nWater: " + stockWater +
                        "\nChips: " + stockChips));

        // ---------- DISPLAY ----------
        display = new JLabel("Select an item", SwingConstants.CENTER);
        display.setFont(new Font("Arial", Font.BOLD, 22));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // ---------- PRODUCT PANEL ----------
        JPanel productPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        productPanel.setBackground(Color.BLACK);
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Cola ---
        colaBtn = new JButton("Cola Tk.35", resizeIcon("src/images/cola.png"));
        styleButton(colaBtn, darkRed);
        colaBtn.addActionListener(this);
        colaSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stockCola, 1));
        JPanel colaPanel = new JPanel(new BorderLayout());
        colaPanel.setBackground(Color.BLACK);
        colaPanel.add(colaBtn, BorderLayout.CENTER);
        colaPanel.add(colaSpinner, BorderLayout.SOUTH);
        productPanel.add(colaPanel);

        // --- Pepsi ---
        pepsiBtn = new JButton("Pepsi Tk.30", resizeIcon("src/images/pepsi.png"));
        styleButton(pepsiBtn, darkRed);
        pepsiBtn.addActionListener(this);
        pepsiSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stockPepsi, 1));
        JPanel pepsiPanel = new JPanel(new BorderLayout());
        pepsiPanel.setBackground(Color.BLACK);
        pepsiPanel.add(pepsiBtn, BorderLayout.CENTER);
        pepsiPanel.add(pepsiSpinner, BorderLayout.SOUTH);
        productPanel.add(pepsiPanel);

        // --- Sprite ---
        spriteBtn = new JButton("Sprite Tk.25", resizeIcon("src/images/sprite.png"));
        styleButton(spriteBtn, darkRed);
        spriteBtn.addActionListener(this);
        spriteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stockSprite, 1));
        JPanel spritePanel = new JPanel(new BorderLayout());
        spritePanel.setBackground(Color.BLACK);
        spritePanel.add(spriteBtn, BorderLayout.CENTER);
        spritePanel.add(spriteSpinner, BorderLayout.SOUTH);
        productPanel.add(spritePanel);

        // --- 7up ---
        upBtn = new JButton("7up Tk.30", resizeIcon("src/images/7up.png"));
        styleButton(upBtn, darkRed);
        upBtn.addActionListener(this);
        upSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stock7up, 1));
        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setBackground(Color.BLACK);
        upPanel.add(upBtn, BorderLayout.CENTER);
        upPanel.add(upSpinner, BorderLayout.SOUTH);
        productPanel.add(upPanel);

        // --- Water ---
        waterBtn = new JButton("Water Tk.20", resizeIcon("src/images/water.png"));
        styleButton(waterBtn, darkRed);
        waterBtn.addActionListener(this);
        waterSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stockWater, 1));
        JPanel waterPanel = new JPanel(new BorderLayout());
        waterPanel.setBackground(Color.BLACK);
        waterPanel.add(waterBtn, BorderLayout.CENTER);
        waterPanel.add(waterSpinner, BorderLayout.SOUTH);
        productPanel.add(waterPanel);

        // --- Chips ---
        chipsBtn = new JButton("Chips Tk.40", resizeIcon("src/images/chips.png"));
        styleButton(chipsBtn, darkRed);
        chipsBtn.addActionListener(this);
        chipsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, stockChips, 1));
        JPanel chipsPanel = new JPanel(new BorderLayout());
        chipsPanel.setBackground(Color.BLACK);
        chipsPanel.add(chipsBtn, BorderLayout.CENTER);
        chipsPanel.add(chipsSpinner, BorderLayout.SOUTH);
        productPanel.add(chipsPanel);

        add(productPanel, BorderLayout.CENTER);

        // Make sure frame is visible after adding all components
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Style button
    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = "";
        int price = 0;
        int quantity = 1;
        boolean enoughStock = false;

        // Determine product
        if (e.getSource() == colaBtn) {
            productName = "Cola"; price = priceCola; quantity = (int) colaSpinner.getValue(); enoughStock = stockCola >= quantity;
        } else if (e.getSource() == pepsiBtn) {
            productName = "Pepsi"; price = pricePepsi; quantity = (int) pepsiSpinner.getValue(); enoughStock = stockPepsi >= quantity;
        } else if (e.getSource() == spriteBtn) {
            productName = "Sprite"; price = priceSprite; quantity = (int) spriteSpinner.getValue(); enoughStock = stockSprite >= quantity;
        } else if (e.getSource() == upBtn) {
            productName = "7up"; price = price7up; quantity = (int) upSpinner.getValue(); enoughStock = stock7up >= quantity;
        } else if (e.getSource() == waterBtn) {
            productName = "Water"; price = priceWater; quantity = (int) waterSpinner.getValue(); enoughStock = stockWater >= quantity;
        } else if (e.getSource() == chipsBtn) {
            productName = "Chips"; price = priceChips; quantity = (int) chipsSpinner.getValue(); enoughStock = stockChips >= quantity;
        }

        if (!enoughStock) {
            display.setText(productName + " - Not enough stock!");
            return;
        }

        // Ask for payment
        String input = JOptionPane.showInputDialog(this, "Enter Payment for " + productName + " x" + quantity + " (Total Tk. " + (price*quantity) + ")");
        try {
            int payment = Integer.parseInt(input);
            int totalCost = price * quantity;
            if (payment >= totalCost) {
                int change = payment - totalCost;
                // Deduct stock
                switch (productName) {
                    case "Cola": stockCola -= quantity; break;
                    case "Pepsi": stockPepsi -= quantity; break;
                    case "Sprite": stockSprite -= quantity; break;
                    case "7up": stock7up -= quantity; break;
                    case "Water": stockWater -= quantity; break;
                    case "Chips": stockChips -= quantity; break;
                }
                display.setText("Bought " + productName + " x" + quantity + "! Change Tk. " + change);
                resetSpinnerMax(productName);
            } else {
                display.setText("Insufficient money for " + productName + "!");
            }
        } catch (Exception ex) {
            display.setText("Invalid payment!");
        }
    }

    // Update spinner maximum after purchase
    private void resetSpinnerMax(String product) {
        switch (product) {
            case "Cola": ((SpinnerNumberModel)colaSpinner.getModel()).setMaximum(stockCola); break;
            case "Pepsi": ((SpinnerNumberModel)pepsiSpinner.getModel()).setMaximum(stockPepsi); break;
            case "Sprite": ((SpinnerNumberModel)spriteSpinner.getModel()).setMaximum(stockSprite); break;
            case "7up": ((SpinnerNumberModel)upSpinner.getModel()).setMaximum(stock7up); break;
            case "Water": ((SpinnerNumberModel)waterSpinner.getModel()).setMaximum(stockWater); break;
            case "Chips": ((SpinnerNumberModel)chipsSpinner.getModel()).setMaximum(stockChips); break;
        }
    }

    private ImageIcon resizeIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VendingMachine());
    }
}

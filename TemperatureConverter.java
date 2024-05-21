import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter {
    private JFrame frame;
    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JLabel resultLabel;

    public TemperatureConverter() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel temperatureLabel = new JLabel("Temperature:");
        temperatureField = new JTextField(10);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        resultLabel = new JLabel("");

        frame.add(temperatureLabel);
        frame.add(temperatureField);
        frame.add(unitComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.pack();
        frame.setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double temperature = Double.parseDouble(temperatureField.getText());
                String unit = (String) unitComboBox.getSelectedItem();

                double celsius = 0, fahrenheit = 0, kelvin = 0;

                switch (unit) {
                    case "Celsius":
                        celsius = temperature;
                        fahrenheit = celsiusToFahrenheit(celsius);
                        kelvin = celsiusToKelvin(celsius);
                        break;
                    case "Fahrenheit":
                        fahrenheit = temperature;
                        celsius = fahrenheitToCelsius(fahrenheit);
                        kelvin = fahrenheitToKelvin(fahrenheit);
                        break;
                    case "Kelvin":
                        kelvin = temperature;
                        celsius = kelvinToCelsius(kelvin);
                        fahrenheit = kelvinToFahrenheit(kelvin);
                        break;
                }

                String result = String.format("Celsius: %.2f, Fahrenheit: %.2f, Kelvin: %.2f", celsius, fahrenheit, kelvin);
                resultLabel.setText(result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid temperature value");
            }
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
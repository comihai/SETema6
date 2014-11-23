package com.packet.views;

import com.packet.interfaces.IController;
import com.packet.interfaces.IModelListener;
import com.packet.interfaces.IView;
import com.packet.model.WeatherModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mihai on 11/22/2014.
 */
public class WeatherView extends JFrame implements IModelListener, IView {

    private static final long serialVersionUID = -5758555454500685115L;

    /**
     * View components
     */
    private JTextField mTemp = new JTextField(5);
    private JTextField mWind = new JTextField(5);
    private JTextField mTempReal = new JTextField(5);
    private JTextField mWindReal = new JTextField(5);
    private JButton randomUpdate = new JButton("Random Update");
    private JButton realUpdate = new JButton("Real Update");
    String[] cities = new String[]{"Bucharest", "Craiova", "Brasov"};
    private JComboBox<String> mCitiesList = new JComboBox<String>(cities);

    private WeatherModel mModel;

    /**
     * Constructor without params
     */
    public WeatherView() {
        mTemp.setEditable(false);
        mWind.setEditable(false);
        mTempReal.setEditable(false);
        mWindReal.setEditable(false);
        this.setResizable(false);
        /**
         * Layout the components
         */
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(600, 70));
        content.setLayout(new FlowLayout());
        content.add(mCitiesList);
        content.add(randomUpdate);
        content.add(new JLabel("Temperature : "));
        content.add(mTemp);
        content.add(new JLabel("°C      Wind Speed : "));
        content.add(mWind);
        content.add(new JLabel("m/s"));
        content.add(new JLabel("                             "));
        content.add(realUpdate);
        content.add(new JLabel("       Temperature : "));
        content.add(mTempReal);
        content.add(new JLabel("°C      Wind Speed : "));
        content.add(mWindReal);
        content.add(new JLabel("m/s"));


        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather Monitor");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * sets the view's reference to the model - only get operations
     *
     * @param model
     */
    public void addModel(WeatherModel model) {
        mModel = model;
        mTemp.setText(model.getTemp());
        mWind.setText(model.getWindSpeed());
        mTempReal.setText(model.getTempReal());
        mWindReal.setText(model.getWindSpeedReal());
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
        randomUpdate.setActionCommand(IController.ACTION_UPDATE_RANDOM);
        randomUpdate.addActionListener(controller);

        realUpdate.setActionCommand(IController.ACTION_UPDATE_REAL);
        realUpdate.addActionListener(controller);

        mCitiesList.setActionCommand(IController.ACTION_UPDATE_CITY);
        mCitiesList.addActionListener(controller);
    }

    @Override
    public void onUpdate() {
        mTemp.setText(mModel.getTemp());
        mWind.setText(mModel.getWindSpeed());
        mTempReal.setText(mModel.getTempReal());
        mWindReal.setText(mModel.getWindSpeedReal());
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void setResizable(boolean resizable) {
        super.setResizable(resizable);
    }
}

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
public class WeatherView extends JFrame implements IModelListener, IView{

    private static final long serialVersionUID = -5758555454500685115L;

    /**
     * View components
     */
    private JTextField mTemp = new JTextField(5);
    private JTextField mWind = new JTextField(5);
    private JButton randomUpdate = new JButton("Random Update");
    String[] cities = new String[] {"Bucharest","Craiova","Brasov"};
    private JComboBox<String> mCitiesList = new JComboBox<String>(cities);

    private IController mWeatherContr;
    private WeatherModel mModel;

    /**
     * Constructor without params
     */
    public WeatherView() {
        mTemp.setEditable(false);
        mWind.setEditable(false);

        /**
         * Layout the components
         */
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(mCitiesList);
        content.add(randomUpdate);
        content.add(new JLabel("Temperature : "));
        content.add(mTemp);
        content.add(new JLabel("°C       Wind Speed : "));
        content.add(mWind);
        content.add(new JLabel("m/s"));
        //content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather Monitor");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * sets the view's reference to the model - only get operations
     * @param model
     */
    public void addModel(WeatherModel model)
    {
        mModel = model;
        mTemp.setText(model.getTemp());
        mWind.setText(model.getWindSpeed());
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller)
    {
        randomUpdate.setActionCommand(IController.ACTION_UPDATE);
        randomUpdate.addActionListener(controller);
    }

    @Override
    public void onUpdate() {
        mTemp.setText(mModel.getTemp());
        mWind.setText(mModel.getWindSpeed());
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if(isError)
        {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

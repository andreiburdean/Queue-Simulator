package GUI;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class  GUI{
	
	Label nrOfClientsLabel = new Label("Number Of Clients: ", 10, 250, 225, 35, 25);
	TextField nrOfClientsTextField = new TextField(230, 258, 40, 22, 20);
	
	public String getnrOfClientsTextField() {
        return nrOfClientsTextField.getText();
    }
	
	Label nrOfQueuesLabel = new Label("Number Of Queues: ", 10, 280, 235, 35, 25);
	TextField nrOfQueuesTextField = new TextField(240, 288, 40, 22, 20);
	
	public String getnrOfQueuesTextField() {
        return nrOfQueuesTextField.getText();
    }
	
	Label simulationIntervalLabel = new Label("Simulation Interval: ", 10, 310, 225, 35, 25);
	TextField simulationIntervalTextField = new TextField(230, 318, 40, 22, 20);
	
	public String getSimIntervalTextField() {
        return simulationIntervalTextField.getText();
    }
	
	Label minMaxArrivalTime = new Label("Minimum and Maximum Arrival Times: ", 10, 340, 440, 35, 25);
	TextField minArrivalTextField = new TextField(445, 348, 40, 22, 20);
	TextField maxArrivalTextField = new TextField(490, 348, 40, 22, 20);
	
	public String getMinArrivalTimeTextField() {
        return minArrivalTextField.getText();
    }

    public String getMaxArrivalTimeTextField() {
        return maxArrivalTextField.getText();
    }
	
	Label minMaxServiceTime = new Label("Minimum and Maximum Service Times: ", 10, 370, 460, 35, 25);
	TextField minServiceTextField = new TextField(460, 378, 40, 22, 20);
	TextField maxServiceTextField = new TextField(505, 378, 40, 22, 20);
	
	public String getMinServiceTimeTextField() {
        return minServiceTextField.getText();
    }

    public String getMaxServiceTimeTextField() {
        return maxServiceTextField.getText();
    }
	
	Button runButton = new Button("Run Simulation", 250, 415, 140, 35, 15);
	
	public void addButtonListener(ActionListener action) {
        runButton.addActionListener(action);
    }
	
	public TextArea textArea = new TextArea(350,25,300,250);
	
	public GUI() {
		
		JFrame GUIFrame = new JFrame("Assignment2 Queue Management Andrei Burdean");
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIFrame.setSize(700, 500);
		GUIFrame.setResizable(false);
		GUIFrame.setLayout(null);
		
		GUIFrame.add(nrOfClientsLabel);
		GUIFrame.add(nrOfClientsTextField);
		
		GUIFrame.add(nrOfQueuesLabel);
		GUIFrame.add(nrOfQueuesTextField);
		
		GUIFrame.add(simulationIntervalLabel);
		GUIFrame.add(simulationIntervalTextField);
		
		GUIFrame.add(minMaxArrivalTime);
		GUIFrame.add(minArrivalTextField);
		GUIFrame.add(maxArrivalTextField);
		
		GUIFrame.add(minMaxServiceTime);
		GUIFrame.add(minServiceTextField);
		GUIFrame.add(maxServiceTextField);
		
		GUIFrame.add(runButton);
		
		GUIFrame.add(textArea);
		
		GUIFrame.setVisible(true);
	
	}
}

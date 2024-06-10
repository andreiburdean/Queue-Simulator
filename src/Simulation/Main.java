package Simulation;

import GUI.GUI;

public class Main {
	
	private GUI gui;

    public Main(GUI gui) {
        this.gui = gui;
        gui.addButtonListener(e -> {
                SimulationManager simulationManager = new SimulationManager(gui);
                Thread mainThread = new Thread(simulationManager);
                mainThread.start();
        });
    }
	
    public static void main(String[] args) {
        GUI gui = new GUI();
        Main main = new Main(gui);
    }
}

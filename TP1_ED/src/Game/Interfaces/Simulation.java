package Game.Interfaces;

/**
 * The Simulation interface defines the core methods for managing a simulation
 * in the game. It includes functionalities to start the simulation and export
 * relevant data.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public interface Simulation {

    /**
     * Starts the simulation.
     */
    public void start();

    /**
     * Exports data related to the simulation.
     */
    public void exportData();

}

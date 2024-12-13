package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import ClassesAulas.LinkedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Enemy;
import Game.Entitys.Player;
import Game.Items.RecoveryItem;
import Game.Menu.PrintLines;

/**
 * The Map class represents the game's map, implemented as a graph.
 *
 * @param <T> The generic type representing the elements in the graph.
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Map<T> extends Graph<T> {

    private Target target;

    /**
     * Default constructor for the Map class.
     * Initializes the map as an empty graph.
     */
    public Map() {
        super();
    }

    /**
     * Retrieves the target associated with the map.
     *
     * @return The target currently set in the map.
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets the target for the map.
     *
     * @param target The target to be set on the map.
     */
    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * Returns the divisions adjacent to a specified division.
     *
     * @param division The division for which you want to find adjacent divisions.
     * @return A list of adjacent divisions to the specified division.
     * @throws NoSuchElementException If the division is not found in the graph.
     */
    public ArrayUnorderedList<Division> getAdjacentDivisions(Division division) {
        ArrayUnorderedList<Division> adjacentDivisions = new ArrayUnorderedList<>();

        try {
            int divisionIndex = getIndex((T) division);

            for (int i = 0; i < vertices.length; i++) {
                if (adjMatrix[divisionIndex][i] && vertices[i] != null) {
                    adjacentDivisions.addToRear((Division) vertices[i]);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Division not found: " + division);
        }

        return adjacentDivisions;
    }

    /**
     * Finds all divisions within a specified range of a given division, limited by `maxRange`.
     *
     * @param start    The starting division for the search.
     * @param maxRange The maximum distance to search for divisions.
     * @return A list of divisions within the specified range of the starting division.
     * @throws EmptyCollectionException If the search encounters an empty collection.
     */
    public ArrayUnorderedList<Division> getDivisionInaRange(Division start, int maxRange) throws EmptyCollectionException {
        ArrayUnorderedList<Division> divisions = new ArrayUnorderedList<>();
        LinkedQueue<Division> queue = new LinkedQueue<>();
        boolean[] visited = new boolean[vertices.length];

        int startIndex = getIndex((T) start);
        if (startIndex == -1) {
            return divisions;
        }
        visited[startIndex] = true;
        queue.enqueue(start);

        int currentRange = 0;

        //BFS iterator
        while (!queue.isEmpty() && currentRange < maxRange) {
            int size = queue.size();
            currentRange++;

            for (int i = 0; i < size; i++) {
                Division current = queue.dequeue();

                ArrayUnorderedList<Division> adjacentDivisions = getAdjacentDivisions(current);
                for (Division neighbor : adjacentDivisions) {
                    int neighborIndex = getIndex((T) neighbor);

                    if (!visited[neighborIndex]) {
                        visited[neighborIndex] = true;
                        queue.enqueue(neighbor);
                        divisions.addToRear(neighbor);
                    }
                }
            }
        }
        return divisions;
    }

    /**
     * Finds the shortest path from a starting division to a target division.
     *
     * @param start  The starting division.
     * @param target The target division to reach.
     * @return A list representing the shortest path from start to target.
     */
    public ArrayUnorderedList<Division> findShortestPath(Division start, Division target) {
        PrintLines print = new PrintLines();

        LinkedQueue<Division> queue = new LinkedQueue<>();
        int[] antecessor = new int[vertices.length];
        boolean[] visited = new boolean[vertices.length];

        int startIndex = getIndex((T) start);
        int targetIndex = getIndex((T) target);

        if (startIndex == -1 || targetIndex == -1) {
            return new ArrayUnorderedList<>();
        }
        ArrayUnorderedList<Division> path = new ArrayUnorderedList<>();

        // BFS
        queue.enqueue(start);
        visited[startIndex] = true;
        antecessor[startIndex] = -1;

        while (!queue.isEmpty()) {

            try {
                Division current = queue.dequeue();

                int currentIndex = getIndex((T) current);

                if (current.equals(target)) {
                    int step = targetIndex;

                    while (step != -1) {
                        path.addToFront((Division) vertices[step]);
                        step = antecessor[step];
                    }
                    return path;
                }

                ArrayUnorderedList<Division> adjacentDivisions = getDivisionInaRange(current, 1);
                for (Division neighbor : adjacentDivisions) {
                    int neighborIndex = getIndex((T) neighbor);
                    if (!visited[neighborIndex]) {
                        visited[neighborIndex] = true;
                        antecessor[neighborIndex] = currentIndex;
                        queue.enqueue(neighbor);
                    }
                }

            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }

        }

        print.pathNotFound();
        return new ArrayUnorderedList<>();
    }

    /**
     * Returns a string representation of the map's divisions and their connections.
     *
     * @return A formatted string showing divisions and their connections.
     */
    @Override
    public String toString() {

        String str = "";

        str += "==DIVISIONS==\n";
        for (int i = 0; i < vertices.length; i++) {

            if (vertices[i] != null) {

                Division division = (Division) vertices[i];
                str += division.dataToString() + "\n";

                //str += (Division) vertices[i].dataToString() + "\n";

            }
        }

        str += "\n==CONNECTIONS==\n";
        for (int i = 0; i < vertices.length; i++) {

            if (vertices[i] != null) {

                boolean hasEdges = false;

                for (int j = 0; j < vertices.length; j++) {

                    if (adjMatrix[i][j] && vertices[j] != null) {

                        if (!hasEdges) {

                            str += vertices[i].toString() + " -> ";
                            hasEdges = true;

                        }

                        str += "[" + vertices[j].toString() + "] ";
                    }
                }

                if (hasEdges) {
                    str += "\n";
                }
            }
        }

        str += "\n==Target==\n";
        str += target.toString();

        return str;
    }

    /**
     * Returns a string representation of the  graph representations.
     *
     * @return A formatted string showing graph represention
     */
    public String toStringGraphRepresentation() {
        String graphRepresentation = "";

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {

                graphRepresentation += vertices[i].toString();

                boolean hasEdges = false;
                for (int j = 0; j < vertices.length; j++) {

                    if (adjMatrix[i][j] && vertices[j] != null) {
                        if (!hasEdges) {
                            graphRepresentation += " -> ";
                            hasEdges = true;
                        }

                        graphRepresentation += vertices[j].toString() + " ";
                    }
                }
                graphRepresentation += "\n";
            }
        }

        return graphRepresentation;
    }

    /**
     * Retrieves a list of all divisions present in the graph.
     *
     * @return A list containing all divisions in the map.
     */
    public ArrayUnorderedList<Division> getDivisions() {
        ArrayUnorderedList<Division> divisions = new ArrayUnorderedList<>();

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {
                Division division = (Division) vertices[i];
                divisions.addToRear(division);
            }
        }

        return divisions;
    }

    /**
     * Moves an enemy to a new division randomly within a certain range.
     *
     * @param enemy The enemy to be moved.
     * @throws EmptyCollectionException If the collection of adjacent divisions is empty.
     */
    private void moveEnemy(Enemy enemy) throws EmptyCollectionException {
        Random random = new Random();

        if (!enemy.isAlive()) {
            return;
        }

        Division enemyStartDivision = enemy.getDivision();

        ArrayUnorderedList<Division> possibleMoveToDivisions = new ArrayUnorderedList<>();

        for (Division division : getAdjacentDivisions(enemyStartDivision)) {
            possibleMoveToDivisions.addToRear(division);

            for (Division adjDivision : getAdjacentDivisions(division)) {

                if (!possibleMoveToDivisions.contains(adjDivision)) {
                    possibleMoveToDivisions.addToRear(adjDivision);
                }

            }
        }

        if (possibleMoveToDivisions.isEmpty()) {
            return;
        }

        int randomDivisionIndex = random.nextInt(possibleMoveToDivisions.size());
        Division newDivision = null;

        Iterator<Division> iterator = possibleMoveToDivisions.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            Division current = iterator.next();

            if (i == randomDivisionIndex) {
                newDivision = current;
                break;
            }

        }

        if (newDivision != null) {
            enemyStartDivision.removeEnemy(enemy);
            enemy.setDivision(newDivision);
            newDivision.addEnemy(enemy);
        }
    }


    /**
     * Moves all enemies present on the map.
     */
    public void moveEnemies() throws EmptyCollectionException {
        for (Division division : getDivisions()) {

            if (division.hasEnemies()) {
                ArrayUnorderedList<Enemy> enemiesCopy = new ArrayUnorderedList<>();

                for (Enemy enemy : division.getEnemies()) {
                    enemiesCopy.addToRear(enemy);
                }

                for (Enemy enemy : enemiesCopy) {
                    moveEnemy(enemy);
                }

            }
        }
    }


    /**
     * Finds the nearest recovery item to a player by comparing distances across divisions.
     *
     * @param player The player for which recovery items are being searched.
     * @return The closest recovery item available on the map.
     */
    public RecoveryItem getNearestRecoveryItem(Player player) {
        ArrayUnorderedList<RecoveryItem> recoveryItems = new ArrayUnorderedList<>();
        RecoveryItem closestKit = null;

        int minDistance = Integer.MAX_VALUE;
        Division playerDivision = player.getDivision();

        for (RecoveryItem kit : recoveryItems) {
            ArrayUnorderedList<Division> path = findShortestPath(playerDivision, kit.getDivision());
            int distance = path.size();

            if (distance < minDistance) {
                minDistance = distance;
                closestKit = kit;
            }

        }

        if (closestKit != null) {
            return closestKit;
        }

        return null;
    }

}

package Game.Mission;

import ClassesAulas.ArrayUnorderedList;

import java.util.NoSuchElementException;

import ClassesAulas.LinkedQueue;
import ExceptionsAulas.EmptyCollectionException;
import Game.Menu.PrintLines;

/**
 * Classe responsavel por guardar o mapa do jogo (grafo)
 */
public class Map<T> extends Graph<T> {

    private Target target;
    //private Division targetDivision;

    public Map() {
        super();
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

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

    //obter as divisoes à volta de uma determinada divisão num limite
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

        // BFS até o limite de passos maxRange
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

    public ArrayUnorderedList<Division> findShortestPath(Division start, Division target) {
        PrintLines print = new PrintLines();

        LinkedQueue<Division> queue = new LinkedQueue<>();
        int[] predecessors = new int[vertices.length];
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
        predecessors[startIndex] = -1;

        while (!queue.isEmpty()) {

            try {
                Division current = queue.dequeue();

                int currentIndex = getIndex((T) current);

                if (current.equals(target)) {
                    int step = targetIndex;

                    while (step != -1) {
                        path.addToFront((Division) vertices[step]);
                        step = predecessors[step];
                    }
                    return path;
                }

                ArrayUnorderedList<Division> adjacentDivisions = getDivisionInaRange(current, 1);
                for (Division neighbor : adjacentDivisions) {
                    int neighborIndex = getIndex((T) neighbor);
                    if (!visited[neighborIndex]) {
                        visited[neighborIndex] = true;
                        predecessors[neighborIndex] = currentIndex;
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

    public void moveEnemies() {

    }

}

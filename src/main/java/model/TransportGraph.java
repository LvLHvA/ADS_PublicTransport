package model;

import java.util.*;

public class TransportGraph {

    private int numberOfStations;
    private int numberOfConnections;
    private List<Station> stationList;
    private Map<String, Integer> stationIndices;
    private List<Integer>[] adjacencyLists;
    private Connection[][] connections;

    public TransportGraph(int size) {
        this.numberOfStations = size;
        stationList = new ArrayList<>(size);
        stationIndices = new HashMap<>();
        connections = new Connection[size][size];
        adjacencyLists = (List<Integer>[]) new List[size];
        for (int vertex = 0; vertex < size; vertex++) {
            adjacencyLists[vertex] = new LinkedList<>();
        }
    }

    /**
     * @param vertex Station to be added to the stationList
     *               The method also adds the station with it's index to the map stationIndices
     */
    public void addVertex(Station vertex) {
        stationList.add(vertex);
        stationIndices.put(vertex.getStationName(), stationList.size() - 1);
    }


    /**
     * Method to add an edge to a adjancencyList. The indexes of the vertices are used to define the edge.
     * Method also increments the number of edges, that is number of Connections.
     * The grap is bidirected, so edge(to, from) should also be added.
     *
     * @param from
     * @param to
     */
    private void addEdge(int from, int to) {
        adjacencyLists[from].add(to);
        adjacencyLists[to].add(from);
        numberOfConnections++;
    }


    /**
     * Method to add an edge in the form of a connection between stations.
     * The method also adds the edge as an edge of indices by calling addEdge(int from, int to).
     * The method adds the connecion to the connections 2D-array.
     * The method also builds the reverse connection, Connection(To, From) and adds this to the connections 2D-array.
     *
     * @param connection The edge as a connection between stations
     */
    public void addEdge(Connection connection) {

        int fromIndex = stationIndices.get(connection.getFrom().getStationName());
        int toIndex = stationIndices.get(connection.getTo().getStationName());
        addEdge(fromIndex, toIndex);
        connections[fromIndex][toIndex] = connection;
        connections[toIndex][fromIndex] = connection;
    }

    public List<Integer> getAdjacentVertices(int index) {
        return adjacencyLists[index];
    }

    public Connection getConnection(int from, int to) {
        return connections[from][to];
    }

    public int getIndexOfStationByName(String stationName) {
        return stationIndices.get(stationName);
    }

    public Station getStation(int index) {
        return stationList.get(index);
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }


    public List<Station> getStationList() {
        return stationList;
    }

    public int getNumberEdges() {
        return numberOfConnections;
    }


    public Connection[][] getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format("Graph with %d vertices and %d edges: \n", numberOfStations, numberOfConnections));
        for (int indexVertex = 0; indexVertex < numberOfStations; indexVertex++) {
//            resultString.append(stationList.get(indexVertex) + " " + stationList.get(indexVertex).getLocation() + ": ");
            resultString.append(stationList.get(indexVertex) + ": ");
            int loopsize = adjacencyLists[indexVertex].size() - 1;
            for (int indexAdjacent = 0; indexAdjacent < loopsize; indexAdjacent++) {
                resultString.append(stationList.get(adjacencyLists[indexVertex].get(indexAdjacent)).getStationName() + "-");
//                resultString.append("(" + stationList.get(adjacencyLists[indexVertex].get(indexAdjacent)).getStationName() + ")-");
            }
            resultString.append(stationList.get(adjacencyLists[indexVertex].get(loopsize)).getStationName() + "\n");
//            resultString.append("(" + stationList.get(adjacencyLists[indexVertex].get(loopsize)).getStationName() + ")\n");
        }
        return resultString.toString();
    }


    /**
     * A Builder helper class to build a TransportGraph by adding lines and building sets of stations and connections from these lines.
     * Then build the graph from these sets.
     */
    public static class Builder {

        private Set<Station> stationSet;
        private List<Line> lineList;
        private Set<Connection> connectionSet;

        public Builder() {
            lineList = new ArrayList<>();
            stationSet = new HashSet<>();
            connectionSet = new HashSet<>();
        }

        /**
         * Method to add a line to the list of lines and add stations to the line.
         *
         * @param lineDefinition String array that defines the line. The array should start with the name of the line,
         *                       followed by the type of the line and the stations on the line in order.
         * @return
         */
        public Builder addLine(String[] lineDefinition) {
            Line line = new Line(lineDefinition[1], lineDefinition[0]);

            //add all stations to line
            for (int i = 2; i <= lineDefinition.length - 1; i++) {
                //TODO Check if station already exists
                Station station = new Station(lineDefinition[i]);
                line.addStation(station);
            }

            lineList.add(line);
            return this;
        }


        /**
         * Method that reads all the lines and their stations to build a set of stations.
         * Stations that are on more than one line will only appear once in the set.
         *
         * @return
         */
        public Builder buildStationSet() {
            for (Line line : lineList) {
                stationSet.addAll(line.getStationsOnLine());
            }
            return this;
        }

        /**
         * For every station on the set of station add the lines of that station to the lineList in the station
         *
         * @return
         */
        public Builder addLinesToStations() {
            for (Station station : stationSet) {
                for (Line line : lineList) {
                    //If line contains station but station doesn't contain line
                    if (line.getStationsOnLine().contains(station) && !station.hasLine(line)) {
                        station.addLine(line);
                    }
                }
            }
            return this;
        }

        /**
         * Method that uses the list of Lines to build connections from the consecutive stations in the stationList of a line.
         *
         * @return
         */
        public Builder buildConnections() {
            for (Line line : lineList) {
                Station previousStation = null;
                for (Station station : line.getStationsOnLine()) {
                    if (previousStation == null) {
                        previousStation = station;
                    } else {

                        Connection connection = new Connection(previousStation, station);
                        connection.setLine(line);
                        connectionSet.add(connection);
                        previousStation = station;
                    }
                }
            }
            return this;
        }

        public Builder addWeight(String[] lineDefinition, double[] weights) throws ClassNotFoundException {

            Line line = lineList.stream()
                    .filter(line1 -> line1.getName().equals(lineDefinition[0]))
                    .findFirst()
                    .orElseThrow(() -> new ClassNotFoundException("Incorrect Line Definition"));


            int weightIndex = 0;
            Station oldStation = null;
            for (Station station : line.getStationsOnLine()) {

                if (oldStation == null) {
                    oldStation = station;
                    continue;
                }

//                Because the variables used in lambda need to be final
//                Making new variable
                Station finalOldStation = oldStation;
                Connection currentConnection = connectionSet.stream()
                        .filter(connection -> connection.getFrom() == finalOldStation
                                && connection.getTo() == station)
                        .findFirst()
                        .orElseThrow(() -> new ClassNotFoundException("No connection available!"));

                currentConnection.setWeight(weights[weightIndex++]);

                oldStation = station;
            }

            return this;
        }

        public Builder addLocationToStationsForLine(int[] locations, String lineName) throws Exception {
            Line line = lineList.stream()
                    .filter(l -> l.getName().equals(lineName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Line not found!"));

            if (locations.length != line.getStationsOnLine().size()*2) {
                throw new Exception(
                        String.format("Stationlist size: %d -> array size: %d for line %s",
                                line.getStationsOnLine().size(),
                                locations.length,
                                lineName));
            }

            int counter = 0;
            for (int i = 0; i < line.getStationsOnLine().size(); i++) {
                Location location = new Location(locations[counter++], locations[counter++]);
                line.getStationsOnLine().get(i).setLocation(location);

            }

            return this;
        }

        /**
         * Method that builds the graph.
         * All stations of the stationSet are addes as vertices to the graph.
         * All connections of the connectionSet are addes as edges to the graph.
         *
         * @return
         */
        public TransportGraph build() {
            TransportGraph graph = new TransportGraph(stationSet.size());
            for (Station station : stationSet) {
                graph.addVertex(station);
            }
            for (Connection connection : connectionSet) {
                graph.addEdge(connection);
            }
            return graph;
        }

    }
}

package dijkstra;

import java.util.Comparator;

import graph.Vertex;

class GenericComparator<T extends Comparable<T>, S extends Comparable<S>>  implements Comparator<Vertex<T, S>> {
    @Override
    public int compare(Vertex<T, S> element1, Vertex<T, S> element2) {
        if (element1.getDistance() > element2.getDistance()) {
            return 1;
        } else if (element1.getDistance() < element2.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }
}
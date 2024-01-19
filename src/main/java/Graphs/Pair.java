package Graphs;

public class Pair<T> {
    protected Pair<T> previous;
    protected T vertex;
    protected double cost;

    public Pair(Pair<T> previous, T vertex, double cost) {
        this.previous = previous;
        this.vertex = vertex;
        this.cost = cost;
    }

    public Pair<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Pair<T> previous) {
        this.previous = previous;
    }

    public T getVertex() {
        return vertex;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

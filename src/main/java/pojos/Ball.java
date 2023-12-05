package pojos;

public class Ball {
    private volatile int coordinate;
    private boolean onMove;

    public Ball(int coordinate) {
        this.coordinate = coordinate;
        this.onMove = false;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isOnMove() {
        return onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }
}

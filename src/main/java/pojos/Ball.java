package pojos;

public class Ball {
    private volatile int coordinateX;
    private volatile int coordinateY;
    private boolean onMove;

    private volatile int speedX;
    private volatile int speedY;

    public Ball(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.onMove = false;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinate(int coordinateX, int coordinateY) {

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public boolean isOnMove() {
        return onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}

package physic;

import pojos.Ball;

//класс-набор статических физических функций
//public cycle - основная фунцкия, которая вызывается на объекте Ball
// остальные функции private и используются в cycle
public class Physic {
    //начальная позиция центра шара на канвасе
    //равна радиусу шара в пикселях
    //скорость задается в пикселях в секунду
    //100 px = 1m
    //пробная функция/пример который задает РПД
    //сила трения не учитывается
    public final static int startPositionX = 50;
    public final static int startPositionY = 250;

    public final static int endPositionX = 950;
    private static int straightMove(int startPosition, long startTime, int speed){
        //получаем текущее время в мс отнимаем стартовое и делим на 1000, так как скорость
        //в пискелях в секунду, таким образом реализуется функция x(t) = x0 + V*t
        return (int)(startPosition + (speed * ((System.currentTimeMillis() - startTime))/1000));
    }

    private static int getDeltaX(int currentPosition, int speed){
        return (int)(currentPosition + speed * (10 / 1000.0));

    }

    //https://yandex.ru/images/search?img_url=https%3A%2F%2Ffuzeservers.ru%2Fwp-content%2Fuploads%2F0%2F7%2F2%2F072fda88ced15b93f1bab6a97fa85bdd.jpeg&lr=2&pos=2&rpt=simage&source=serp&text=уравнение%20высоты%20шарика%20от%20падения
    private static int getHeight(int y, long startTime, int startSpeed){
        double curTime = (System.currentTimeMillis() - startTime) / 1000.0;
        double dy = startSpeed / 100.0 * Math.sin(3 * Math.PI / 4) * curTime - 9.8 * curTime * curTime / 2;
        return (int)(y - 50 * dy);
    }

    private static int getNewSpeedY(long startTime, int startSpeed){
        double curTime = (System.currentTimeMillis() - startTime) / 1000.0;
        return (int)(startSpeed * Math.sin(3 * Math.PI / 4) - 9.8 * curTime);
    }

    private static int updateSpeed(long startTime, int speed, String form){
        long curTime = (System.currentTimeMillis() - startTime);
        double a = 0.0005;
        if (form.equals("donut")) a = 0.0004;
        if (form.equals("hoop")) a = 0.00021;
        speed -= curTime * a;
        return speed;
    }


    public static void ball_wall_cycle(Ball ball, int speed, String form, Boolean wall_friction){
        long start = System.currentTimeMillis();
        long startY = 0;
        int speedY = 0;
        ball.setOnMove(true);
        ball.setSpeedX(speed);
        ball.setSpeedY(0);
        boolean hit = false;

        //анимация закончиться, когда придет запрос reset или когда мяч удариться в 0 + радиус
        while(ball.isOnMove()){

            if (ball.getCoordinateX() < endPositionX && !hit){
                ball.setSpeedX(updateSpeed(start, ball.getSpeedX(), form));
                int x = straightMove(startPositionX, start, ball.getSpeedX());
                if (ball.getCoordinateX() > x){
                    ball.setSpeedX(0);
                    ball.setOnMove(false);
                    return;
                }
                ball.setCoordinate(x, startPositionY );
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.err.println("Thread was interrupted");
                }
            }else if(!hit){
                ball.setCoordinate(endPositionX, startPositionY);
                hit = true;
                startY = System.currentTimeMillis();
                speedY = ball.getSpeedX();
            }

            if (hit){
                ball.setSpeedX(updateSpeed(start, ball.getSpeedX(), form));

                int x = -straightMove(startPositionX, start, ball.getSpeedX()) + 2 * endPositionX;
                int y = wall_friction ? getHeight(startPositionY, startY, speedY) : startPositionY;
                if (ball.getCoordinateX() < x){
                    ball.setSpeedX(0);
                    x = ball.getCoordinateX();
                }
                if (y > startPositionY){
                    speedY = getNewSpeedY(startY, speedY);
                    startY = System.currentTimeMillis();
                }
                ball.setCoordinate(x, y);
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.err.println("Thread was interrupted");
                }
            }
        }

    }

    public static void ball_ball_cycle(Ball ball1, int speed1, Ball ball2, int speed2, String form1, String form2){
        long start = System.currentTimeMillis();
        ball1.setOnMove(true);
        ball2.setOnMove(true);
        ball1.setSpeedX(speed1 + 100);
        ball1.setSpeedY(0);
        ball2.setSpeedX(speed2);
        ball2.setSpeedY(0);
        long start1Y = 0;
        long start2Y = 0;
        int speed1Y = 0;
        int speed2Y = 0;
        boolean hit = false;
        while (ball1.isOnMove() && ball2.isOnMove()){
            if (ball1.getCoordinateX() + 50 < ball2.getCoordinateX() - 50 && !hit){
                ball1.setSpeedX(updateSpeed(start, ball1.getSpeedX(), form1));
                ball2.setSpeedX(updateSpeed(start, ball2.getSpeedX(), form2));
                int x1 = getDeltaX(ball1.getCoordinateX(), ball1.getSpeedX());
                int x2 = getDeltaX(ball2.getCoordinateX(), -ball2.getSpeedX());
                if (ball1.getCoordinateX() > x1){
                    ball1.setSpeedX(0);
                    x1 = ball1.getCoordinateX();
                }
                if (ball2.getCoordinateX() < x2){
                    ball2.setSpeedX(0);
                    x2 = ball2.getCoordinateX();
                }
                ball1.setCoordinate(x1, startPositionY);
                ball2.setCoordinate(x2, startPositionY);
            }else if (!hit){
                hit = true;
                speed1 = ball1.getSpeedX();
                speed2 = ball2.getSpeedX();
                ball1.setSpeedX(speed2);
                ball2.setSpeedX(speed1);
                start1Y = System.currentTimeMillis();
                start2Y = System.currentTimeMillis();
                speed1Y = ball1.getSpeedX();
                speed2Y = ball2.getSpeedX();
            }

            if (hit){

                ball1.setSpeedX(updateSpeed(start, ball1.getSpeedX(), form1));
                ball2.setSpeedX(updateSpeed(start, ball2.getSpeedX(), form2));
                int x1 = getDeltaX(ball1.getCoordinateX(), -ball1.getSpeedX());
                int x2 = getDeltaX(ball2.getCoordinateX(), ball2.getSpeedX());
                int y1 = getHeight(startPositionY, start1Y, speed1Y);
                int y2 = getHeight(startPositionY, start2Y, speed2Y);
                if (ball1.getCoordinateX() < x1){
                    ball1.setSpeedX(0);
                    x1 = ball1.getCoordinateX();
                }
                if (ball2.getCoordinateX() > x2){
                    ball2.setSpeedX(0);
                    x2 = ball2.getCoordinateX();
                }
                if (y1 > startPositionY){
                    speed1Y = getNewSpeedY(start1Y, speed1Y);
                    start1Y = System.currentTimeMillis();
                }
                if (y2 > startPositionY){
                    speed2Y = getNewSpeedY(start1Y, speed1Y);
                    start2Y = System.currentTimeMillis();
                }
                ball1.setCoordinate(x1, y1);
                ball2.setCoordinate(x2, y2);

            }

            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                System.err.println("Thread was interrupted");
            }
        }
    }
}

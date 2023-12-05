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
    public final static int startPosition = 50;
    public final static int endPosition = 950;
    private static int straightMove(int startPosition, long startTime, int speed){
        //получаем текущее время в мс отнимаем стартовое и делим на 1000, так как скорость
        //в пискелях в секунду, таким образом реализуется функция x(t) = x0 + V*t
        return (int)(startPosition + (speed * ((System.currentTimeMillis() - startTime))/1000));
    }

    public static void ball_wall_cycle(Ball ball, int speed){
        long start = System.currentTimeMillis();
        ball.setOnMove(true);
        boolean hit = false;

        //анимация закончиться, когда придет запрос reset или когда мяч удариться в 0 + радиус
        while(ball.isOnMove()){
            if (ball.getCoordinate() < endPosition && !hit){
                ball.setCoordinate(straightMove(startPosition, start, speed));
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.err.println("Thread was interrupted");
                }
            }else if(!hit){
                ball.setCoordinate(endPosition);
                hit = true;
                speed = -speed;
                start = System.currentTimeMillis();
            }
            if (ball.getCoordinate() > startPosition && hit){
                ball.setCoordinate(straightMove(endPosition, start, speed));
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.err.println("Thread was interrupted");
                }
            } else if (hit) {
                ball.setCoordinate(startPosition);
                ball.setOnMove(false);
            }
        }

    }

    public static void ball_ball_cycle(Ball ball1, int speed1, Ball ball2, int speed2){
        long start = System.currentTimeMillis();
        ball1.setOnMove(true);
        ball2.setOnMove(true);
        boolean hit = false;
        while (ball1.isOnMove() && ball2.isOnMove()){
            if (ball1.getCoordinate() + 50 < ball2.getCoordinate() - 50 && !hit){
                ball1.setCoordinate(straightMove(startPosition, start, speed1));
                ball2.setCoordinate(straightMove(endPosition, start, speed2));
            }else{
                ball1.setOnMove(false);
                ball2.setOnMove(false);
            }
        }
    }
}

package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import physic.Physic;
import pojos.Ball;

import java.io.IOException;

@WebServlet(name = "BallBallResetServlet", value = "/ball-ball-reset")
public class BallBallResetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            HttpSession session = request.getSession();
            Ball ball1 = (Ball)session.getAttribute("ball_1");
            Ball ball2 = (Ball)session.getAttribute("ball_2");
            ball1.setOnMove(false);
            ball2.setOnMove(false);
            ball1.setCoordinate(Physic.startPosition);
            ball2.setCoordinate(Physic.endPosition);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }catch (NullPointerException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

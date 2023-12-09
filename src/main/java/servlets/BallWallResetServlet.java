package servlets;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import physic.Physic;
import pojos.Ball;

@WebServlet(name = "ResetServlet", value = "/ball-wall-reset")
public class BallWallResetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Ball ball = (Ball)session.getAttribute("ball");
            ball.setOnMove(false);
            ball.setCoordinate(Physic.startPositionX, Physic.startPositionY);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }catch (NullPointerException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}

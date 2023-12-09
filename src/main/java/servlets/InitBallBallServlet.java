package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import physic.Physic;
import pojos.Ball;

import java.io.IOException;

@WebServlet(name = "InitBallBallServlet", value = "/ball-ball")
public class InitBallBallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("ball_1") == null){
            session.setAttribute("ball_1", new Ball(Physic.startPositionX, Physic.startPositionY));
            session.setAttribute("ball_2", new Ball(Physic.endPositionX, Physic.startPositionY));
        }
        request.getRequestDispatcher("/ball_ball.jsp").forward(request, response);
    }
}

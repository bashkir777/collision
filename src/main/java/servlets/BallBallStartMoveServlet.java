package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import physic.Physic;
import pojos.Ball;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BallBallStartMoveServlet", value = "/ball-ball-start")
public class BallBallStartMoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try{
            int speed1 = Integer.parseInt(request.getParameter("speed_1"));
            int speed2 = Integer.parseInt(request.getParameter("speed_2"));
            String form1 = request.getParameter("form_1");
            String form2 = request.getParameter("form_2");
            HttpSession session = request.getSession();
            Ball ball1 = (Ball)session.getAttribute("ball_1");
            Ball ball2 = (Ball)session.getAttribute("ball_2");
            Physic.ball_ball_cycle(ball1, speed1, ball2, speed2, form1, form2);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            pw.println("failed to start movement");
            throw e;
        }
    }
}

package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import physic.Physic;
import pojos.Ball;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartMoveServlet", value = "/ball-wall-start")
public class BallWallStartMoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try{
            int speed = Integer.parseInt(request.getParameter("speed"));
            HttpSession session = request.getSession();
            Ball ball = (Ball)session.getAttribute("ball");
            Physic.ball_wall_cycle(ball, speed);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            pw.println("failed to start movement");
            throw e;
        }
    }
}

package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojos.Ball;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BallBallCordServlet", value = "/ball-ball-cord")
public class BallBallCordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Ball ball1 = (Ball) session.getAttribute("ball_1");
        Ball ball2 = (Ball) session.getAttribute("ball_2");
        PrintWriter pw = response.getWriter();
        if(ball1 != null && ball2 != null){
            pw.println(ball1.getCoordinate()+ " " + ball2.getCoordinate());
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.println("ball object have never been initialize for this session");
        }
    }
}

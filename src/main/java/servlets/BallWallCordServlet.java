package servlets;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojos.Ball;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CordServlet", value = "/ball-wall-cord")
public class BallWallCordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Ball ball = (Ball) session.getAttribute("ball");
        PrintWriter pw = response.getWriter();
        if(ball != null){
            pw.println(ball.getCoordinate());
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.println("ball object have never been initialize for this session");
        }
    }
}

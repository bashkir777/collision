package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojos.Ball;

import java.io.IOException;

@WebServlet(name = "InitBallWallServlet", value = "/ball-wall")
public class InitBallWallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("ball") == null){
            session.setAttribute("ball", new Ball(50, 250));
        }
        request.getRequestDispatcher("/ball_wall.jsp").forward(request, response);
    }
}

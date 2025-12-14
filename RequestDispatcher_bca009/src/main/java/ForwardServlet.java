import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // set data in request
        request.setAttribute("msg", "This message is from ForwardServlet");

        // forward request
        RequestDispatcher rd = request.getRequestDispatcher("target");
        rd.forward(request, response);
    }
}

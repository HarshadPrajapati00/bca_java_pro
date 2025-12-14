import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/target")
public class TargetServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String message = (String) request.getAttribute("msg");

        out.println("<h2>Forward Example</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<p>Control transferred using <b>forward()</b></p>");
    }
}

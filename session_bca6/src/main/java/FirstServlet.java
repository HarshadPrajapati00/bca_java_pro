import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Session Tracking Using Hidden Field</h2>");
        out.println("<form action='second'>");

        out.println("Enter Name: <input type='text' name='username'><br><br>");
        out.println("<input type='submit' value='Next'>");

        out.println("</form>");
    }
}

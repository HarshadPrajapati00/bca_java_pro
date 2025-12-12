import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        out.println("<h2>Welcome, " + name + "</h2>");
        out.println("<form action='third'>");

        // Hidden field to maintain session
        out.println("<input type='hidden' name='username' value='" + name + "'>");

        out.println("Enter Email: <input type='text' name='email'><br><br>");
        out.println("<input type='submit' value='Finish'>");

        out.println("</form>");
    }
}

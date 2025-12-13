import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("username");

        out.println("<h2>Second Servlet</h2>");
        out.println("<form action='third'>");

        // URL rewriting using hidden field
        out.println("<input type='hidden' name='username' value='" + name + "'>");

        out.println("Enter Age: <input type='text' name='age'><br><br>");
        out.println("<input type='submit' value='Next'>");
        out.println("</form>");
    }
}

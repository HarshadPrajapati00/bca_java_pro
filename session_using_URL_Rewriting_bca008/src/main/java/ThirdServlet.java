import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("username");
        String age = req.getParameter("age");

        out.println("<h2>Third Servlet</h2>");
        out.println("<h3>Name: " + name + "</h3>");
        out.println("<h3>Age: " + age + "</h3>");
    }
}

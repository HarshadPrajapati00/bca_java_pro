import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        out.println("<h2>First Servlet</h2>");
        out.println("<form action='second'>");
        out.println("Name: <input type='text' name='username'>");
        out.println("<input type='submit' value='Next'>");
        out.println("</form>");
    }
}


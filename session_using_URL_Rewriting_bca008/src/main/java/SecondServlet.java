import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("username");
        
        if(name != ""){
        // REAL URL Rewriting
        out.println("<h2>Second Servlet</h2>");
        out.println("<a href='third?username=" + name + "'>Go to Third Servlet</a>");
        }
        else {
        out.println( "<h2  style = 'color:red'> Value not available please enter again. </h2>");
        out.println("<a href='first'> Go Back </a>");
        
        }

        
    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        // VALIDATION
        if (name == null || name.trim().equals("")) {
            out.println("<h2 style='color:red;'>Please enter value</h2>");
            out.println("<a href='index.html'>Go Back</a>");
            return;   // stop execution
        }

        // Create Cookie ONLY if name is valid
        Cookie ck = new Cookie("user", name);

        // Cookie valid for 30 seconds
        ck.setMaxAge(30);

        response.addCookie(ck);

        out.println("<h2>Cookie Created Successfully</h2>");
        out.println("<a href='second'>Go to Second Servlet</a>");
    }
}

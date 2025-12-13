import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        String userName = null;

        // STEP 1: Check cookies array
        if (cookies != null) {
            for (Cookie c : cookies) {
                // STEP 2: Check correct cookie name AND value
                if ("user".equals(c.getName()) && c.getValue() != null) {
                    userName = c.getValue();
                    break;
                }
            }
        }

        // STEP 3: FINAL DECISION
        if (userName == null || userName.trim().equals("")) {
            // Cookie expired or not available
            out.println("<h2 style='color:red;'>Cookie Expired</h2>");
            out.println("<a href='index.html'>Go to First Page</a>");
        } else {
            // Cookie exists
            out.println("<h2>Welcome, " + userName + "</h2>");
            out.println("<p>Cookie is valid</p>");
        }
    }
}

package uz.pdp.testmaster.web.email;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String verCode = req.getParameter("verCode");

        if (code.equals(verCode)) {
            resp.sendRedirect("/home.jsp");
        }else {
            resp.sendRedirect("/checkCode.jsp");
        }
    }
}

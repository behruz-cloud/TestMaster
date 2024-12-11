package uz.pdp.testmaster.web.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uz.pdp.testmaster.web.email.EmailSender;

import java.io.IOException;
import java.util.Random;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    Random random = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gmail = req.getParameter("gmail");
        String password = req.getParameter("password");
        if (gmail.equals("1") && password.equals("1")) {
            HttpSession session = req.getSession();
            int randomNumber = random.nextInt(10000);
            String code = String.valueOf(randomNumber); // Tasdiqlash kodi
            session.setAttribute("code",code);
            EmailSender.sendVerificationCode("akmaljonxolmatov1979@gmail.com", code);
            resp.sendRedirect("/checkCode.jsp");
        }else {
            resp.sendRedirect("/loginPage.jsp");
        }
    }
}

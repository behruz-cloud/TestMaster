package uz.pdp.testmaster.web.email;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/sendCode")
public class SendEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random random = new Random();
        HttpSession session = request.getSession();
        int randomNumber = random.nextInt(10000);
        String code = String.valueOf(randomNumber); // Tasdiqlash kodi
        session.setAttribute("code",code);
        EmailSender.sendVerificationCode("akmaljonxolmatov1979@gmail.com", code);
        response.sendRedirect("/checkCode.jsp");
    }
}

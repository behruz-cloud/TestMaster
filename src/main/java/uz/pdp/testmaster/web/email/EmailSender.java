package uz.pdp.testmaster.web.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void sendVerificationCode(String recipient, String code) {
        // Gmail sozlamalari
        String host = "smtp.gmail.com";
        final String user = "behruzbekmahmudjonov08@gmail.com"; // Gmail manzilingiz
        final String password = "dpzdpnafjlnyuuuy";    // Gmail parolingiz yoki App Password

        // SMTP sozlamalarini o‘rnatish
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Session yaratish
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // HTML formatidagi xabarni yaratish
            String htmlContent = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;">
                    <div style="max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);">
                        <h2 style="text-align: center; color: #333;">Tasdiqlash kodi</h2>
                        <p style="font-size: 16px; color: #555;">
                            Assalomu alaykum! Siz tizimga kirish jarayonidasiz.
                        </p>
                        <div style="text-align: center; margin: 20px 0;">
                            <span style="display: inline-block; padding: 10px 20px; font-size: 18px; font-weight: bold; color: #fff; background-color: #007BFF; border-radius: 4px;">
                                %s
                            </span>
                        </div>
                        <p style="font-size: 14px; color: #777;">
                            Ushbu kodni hech kimga oshkor qilmang. Kod 5 daqiqa davomida amal qiladi.
                        </p>
                        <p style="text-align: center; font-size: 14px; color: #aaa;">© 2024 QuizCraft. Barcha huquqlar himoyalangan.</p>
                    </div>
                </body>
                </html>
            """.formatted(code);

            // Xabar yaratish
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Behruzning tasdiqlash kodi");
            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Xabarni jo‘natish
            Transport.send(message);
            System.out.println("Xabar muvaffaqiyatli yuborildi!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Xatolik yuz berdi: " + e.getMessage());
        }
    }
}

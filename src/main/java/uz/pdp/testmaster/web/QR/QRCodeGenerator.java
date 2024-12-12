package uz.pdp.testmaster.web.QR;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uz.pdp.testmaster.web.Repo.ExamRepo;
import uz.pdp.testmaster.web.entity.Exam;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

@WebServlet("/qrCodeGenerator")
public class QRCodeGenerator extends HttpServlet {

    private BufferedImage generateQRImage(String content, int width, int height) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 1);

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer examId = Integer.parseInt(req.getParameter("examId"));
    Exam exam = ExamRepo.getById(examId);

    if (exam == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Exam not found.");
        return;
    }

    String qrContent = "https://t.me/behruz_payment_bot?start=" + examId;

    int width = 300;  // QR kodning kengligi
    int height = 300;  // QR kodning balandligi

    try {
        BufferedImage qrImage = generateQRImage(qrContent, width, height);

        String qrFileName = "exam_qr_" + examId + ".png";
        String qrFilePath = getServletContext().getRealPath("/qrCodes/") + qrFileName;

        java.io.File qrDir = new java.io.File(getServletContext().getRealPath("/qrCodes/"));
        if (!qrDir.exists()) {
            qrDir.mkdirs();
        }
        java.io.File qrFile = new java.io.File(qrFilePath);
        javax.imageio.ImageIO.write(qrImage, "PNG", qrFile);
        String qrUrl = req.getContextPath() + "/qrCodes/" + qrFileName;
        req.setAttribute("qrUrl", qrUrl);
        req.getRequestDispatcher("/viewQr.jsp").forward(req, resp);
    } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating QR code.");
        e.printStackTrace();
    }
}

}

package tesler.will.torrent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javafx.scene.paint.Paint;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QR {

    public static final String FILETYPE_PNG = "png";

    public static void main(String[] args) {
        QR.create("10.0.0.1", new File("out/QR.png"), 512);
    }

    public static void create(String msg, File out, int size) {
        String fileType = FILETYPE_PNG;
        try {
            Hashtable<EncodeHintType, Object> hintMap =
                    new Hashtable<EncodeHintType, Object>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintMap.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix =
                    qrCodeWriter.encode(msg, BarcodeFormat.QR_CODE, size, size,
                            hintMap);
            BufferedImage image =
                    new BufferedImage(size, size,
                            BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(new Color(244, 244, 244));
            graphics.fillRect(0, 0, size, size);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, out);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Debug.out(Debug.QR, "You have successfully created QR Code.");
    }
}
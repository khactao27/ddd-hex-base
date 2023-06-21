package tech.ibrave.metabucket.shared.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Base64;
import tech.ibrave.metabucket.shared.totp.TOTPData;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author an.cantuong
 * created 6/21/2023
 */
@UtilityClass
public class QRCodeUtils {

    public static String createGoogleQRCodeAsBase64(String issuer,
                                                    String user,
                                                    String secretKey) throws IOException, WriterException {
        var barCodeUrl = new TOTPData(issuer, user, secretKey.getBytes()).getUrl();
        return createQRCode(barCodeUrl, 400, 400);
    }

    public static void createQRCode(String barCodeData,
                                    String filePath,
                                    int height,
                                    int width) throws WriterException, IOException {
        var matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, width, height);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
    }

    public static void createQRCode(String barCodeData,
                                    OutputStream out,
                                    int height,
                                    int width) throws WriterException, IOException {
        var matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToStream(matrix, "png", out);
    }

    public static String createQRCode(String barCodeData,
                                      int height,
                                      int width) throws WriterException, IOException {
        var byteArrayOps = new ByteArrayOutputStream();
        createQRCode(barCodeData, byteArrayOps, height, width);
        return Base64.encodeBase64String(byteArrayOps.toByteArray());
    }
}

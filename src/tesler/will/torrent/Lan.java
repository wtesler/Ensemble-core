package tesler.will.torrent;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Lan {

    private String mAddress = "";
    private Server mServer;
    private SecretKey mKey;

    public Lan() {

        try {

            mAddress = IpUtils.getIpAddress();
            Debug.out(Debug.LAN, "Local Address: " + mAddress);

            mServer = new Server();
            Debug.out(Debug.LAN, mServer.toString());

            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            mKey = keygen.generateKey();
            String hexKey = ByteUtils.bytesToHex(mKey.getEncoded());
            Debug.out(Debug.LAN, hexKey);

        } catch (NoSuchElementException | IOException | NoSuchAlgorithmException e) {
            Debug.err(Debug.LAN, e.getMessage());
            e.printStackTrace();
        }
    }

    public void generateQR(File out, int size) {
       String hexKey = ByteUtils.bytesToHex(mKey.getEncoded());
       String msg = mAddress + ' ' + mServer.getPort() + ' ' + hexKey;
       QR.create(msg, out, size);
    }

    public static void main(String[] args) {
        new Lan();
    }

}

package tesler.will.torrent;

import java.io.IOException;
import java.util.Arrays;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class Server {

    private SSLServerSocket mServerSocket;

    // A port of 0 tells the ServerSocket to use the next available port.
    private final int PORT = 0;

    Server() {

        try {

            mServerSocket =
                    (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(PORT);

            mServerSocket.close();

        } catch (IOException e) {
            Debug.err(Debug.SERVER, e.getMessage());
            e.printStackTrace();
        }

    }

    public int getPort() {
        return mServerSocket.getLocalPort();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\n" + "___SERVER INFO___" + "\n\n");
        builder.append("Local Port: " + mServerSocket.getLocalPort() + "\n\n");
        builder.append("Enabled Cipher Suites: "
                + Arrays.toString(mServerSocket.getEnabledCipherSuites()) + "\n\n");
        builder.append("Enabled Protocols: " + Arrays.toString(mServerSocket.getEnabledProtocols())
                + "\n");
        return builder.toString();
    }

}

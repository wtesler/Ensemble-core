package tesler.will.torrent;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class IpUtils {

    public static String getIpAddress() throws SocketException,
            NoSuchElementException {

        Enumeration<NetworkInterface> enumNetworkInterfaces =
                NetworkInterface.getNetworkInterfaces();
        while (enumNetworkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
            Enumeration<InetAddress> enumInetAddress =
                    networkInterface.getInetAddresses();
            while (enumInetAddress.hasMoreElements()) {
                InetAddress inetAddress = enumInetAddress.nextElement();

                if (inetAddress.isSiteLocalAddress()) {
                    return inetAddress.getHostAddress();
                }
            }
        }

        throw new NoSuchElementException("No Site Local Address was found.");
    }

}

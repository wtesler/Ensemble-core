package tesler.will.torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class Main {

	public static void main(String[] args) {

		// First, instantiate the Client object.
		Client client;
		try {
			client = new Client(
					// This is the interface the client will listen on (you might need
					// something
					// else than localhost here).
					InetAddress.getLocalHost(),

					// Load the torrent from the torrent file and use the given
					// output directory. Partials downloads are automatically
					// recovered.
					SharedTorrent.fromFile(new File("res/shakespeare.torrent"),
							new File("out")));

			// You can optionally set download/upload rate limits
			// in kB/second. Setting a limit to 0.0 disables rate
			// limits.
			client.setMaxDownloadRate(0.0);
			client.setMaxUploadRate(0.0);

			// At this point, can you either call download() to download the
			// torrent and
			// stop immediately after...
			client.download();

			// Or call client.share(...) with a seed time in seconds:
			// client.share(3600);
			// Which would seed the torrent for an hour after the download is
			// complete.

			// Downloading and seeding is done in background threads.
			// To wait for this process to finish, call:
			client.waitForCompletion();

			// At any time you can call client.stop() to interrupt the download.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

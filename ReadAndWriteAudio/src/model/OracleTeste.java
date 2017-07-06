package model;

import java.io.ByteArrayOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

//URL: https://docs.oracle.com/javase/tutorial/sound/capturing.html

public class OracleTeste {

	public static void main(String[] args) throws LineUnavailableException {
		TargetDataLine line = null;
		AudioFormat format = new AudioFormat(null, 0, 0, 0, 0, 0, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format
																				// is
																				// an
																				// AudioFormat
																				// object
		if (!AudioSystem.isLineSupported(info)) {
			// Handle the error ...

		}
		// Obtain and open the line.
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
		} catch (LineUnavailableException ex) {
			// Handle the error ...
		}

		// Assume that the TargetDataLine, line, has already
		// been obtained and opened.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int numBytesRead;
		byte[] data = new byte[line.getBufferSize() / 5];

		// Begin audio capture.
		line.start();

		// Here, stopped is a global boolean set by another thread.
		boolean stopped = false;
		while (!stopped) {
			// Read the next chunk of data from the TargetDataLine.
			numBytesRead = line.read(data, 0, data.length);
			// Save this chunk of data.
			out.write(data, 0, numBytesRead);
		}
	}
}

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import ij.plugin.frame.*;
import java.util.Arrays;

public class Pasta_Plugin implements PlugIn {
	public static final int MINSIZE = 500;
	public static final int ANGLE = 360 / 10;
	public static final double THRESHOLD = 0.5;// Math.sqrt(3.0 / 5.0);
	public static final int LEN_THRESHOLD = 400;

	public static float media(int v[]) {
		int s = 0;
		for (int i = 0; i < v.length; i++) {
			s += v[i];
		}
		return (float) s / (float) v.length;
	}

	public static int mediana(int v[]) {
		Arrays.sort(v);
		return v[v.length / 2];
	}

	public float variance(int[] v) {

		float sum = 0.0f;
		float avg = media(v);

		for (int i = 0; i < v.length; i++) {

			sum += (v[i] - avg) * (v[i] - avg);

		}

		return sum / (float) v.length;
	}

	public float stdDev(int[] v) {

		return (float) Math.sqrt(variance(v));

	}

	public boolean isUnimodal(int[] v) {
		// IJ.showMessage("" + THRESHOLD);
		// IJ.showMessage("" + ((Math.abs((mediana(v) - media(v))) / stdDev(v))
		// <THRESHOLD));
		// IJ.showMessage("" + (Math.abs((mediana(v) - media(v))) / stdDev(v)));
		return ((Math.abs((mediana(v) - media(v))) / stdDev(v)) < THRESHOLD);
	}

	public boolean isLong(int[] v) {
		return mediana(v) > LEN_THRESHOLD;
	}

	public void run(String arg) {
		ij.measure.ResultsTable table = IJ.getTextPanel().getResultsTable();
		int h[] = new int[11];
		int h2[] = new int[table.size()];

		for (int i = 0; i < table.size(); i++) {
			double major = table.getValue("Major", i);
			double minor = table.getValue("Minor", i);
			if (major * minor > MINSIZE) {
				int idx = (int) table.getValue("Angle", i) / ANGLE;
				h[idx]++;
			}
			h2[i] = (int) major;
		}

		// https://en.wikipedia.org/wiki/Mode_(statistics)
		if (isUnimodal(h) && isLong(h2)) {
			IJ.showMessage("E' intero");
		} else
			IJ.showMessage("E' spezzato");
	}
}

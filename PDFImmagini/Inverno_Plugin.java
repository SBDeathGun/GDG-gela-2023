import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class Inverno_Plugin implements PlugInFilter {
    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB;
    }

    public void run(ImageProcessor ip) {
        TextLearner textLearner = new TextLearner();
        ProbabilityDensityFunction p = null;
        try {
            p = ProbabilityDensityFunction.read("PdfImmagini/Inverno.pd");

            p = textLearner.addPdf((int[]) ip.getPixels, p);

            p.write("PdfImmagini/" + args[0]);

        } catch (Exception e) {

            p = textLearner.createPdf((int[]) ip.getPixels);

            p.write("PdfImmagini/Inverno.pd");
        }
    }

}

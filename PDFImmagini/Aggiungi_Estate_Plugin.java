import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class Aggiungi_Estate_Plugin implements PlugInFilter {
    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB;
    }

    public void run(ImageProcessor ip) {
        Learner.learn("plugins/aiuto/pdfImmagini/estate.pd",(int[]) ip.getPixels());
    }

}

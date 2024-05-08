import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class Aggiungi_Inverno_Plugin implements PlugInFilter {
    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB;
    }
    public void run(ImageProcessor ip) {
        Learner.learn("plugins/aiuto/pdfImmagini/inverno.pd",(int[]) ip.getPixels());
    }

}

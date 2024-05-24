import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*
--------------------------------------------------
Programma fatto da: Ferrera 
--------------------------------------------------
*/

public class classify_plugins implements PlugInFilter {
    ImagePlus imp;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB;
    }

    public void run(ImageProcessor ip) {
        File folder = new File("plugins/aiuto/pdfImmagini");
        File[] listOfFiles = folder.listFiles();
        ProbabilityDensityFunction pdfInput = null, pdfOutput;
        ImageLearner imageLearner = new ImageLearner();
        float corrCoeff = 0, tmp;
        StringBuilder res = null;
        StringBuilder stagione = new StringBuilder();

        try {
            pdfInput = imageLearner.createPdf((int[]) ip.getPixels());

        } catch (Exception e) {
        }
        pdfInput = pdfInput.normalize();
        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                pdfOutput = ProbabilityDensityFunction.read(listOfFiles[i].toPath().toString());
                pdfOutput = pdfOutput.normalize();
                tmp = pdfInput.corrCoeff(pdfOutput);
                System.out.println(listOfFiles[i] + " " + tmp);
                if (tmp > corrCoeff) {
                    stagione.replace(0, stagione.length(), listOfFiles[i].toString());
                    corrCoeff = tmp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stagione.delete(0, 26);
        stagione.delete(stagione.length() - 3, stagione.length());
        IJ.showMessage(stagione.toString());

    }

}

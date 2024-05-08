import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MainLearner {
    public static void main(String[] args) {
        ImageLearner imageLearner = new ImageLearner();
        ProbabilityDensityFunction p = null;
        try {
            p = ProbabilityDensityFunction.read("PdfImmagini/" + args[0]);

            p = imageLearner.addPdf((int[]) ip.getPixels, p);

            p.write("PdfImmagini/" + args[0]);

        } catch (Exception e) {

            p = imageLearner.createPdf((int[]) ip.getPixels);

            p.write("PdfLingue/" + args[0]);
        }

    }

}

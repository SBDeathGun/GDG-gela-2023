import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MainClassify {
    public static void main(String[] args) {
        File folder = new File("PdfLingue");
        File[] listOfFiles = folder.listFiles();
        File testo = new File(args[0]);
        ProbabilityDensityFunction pdfInput = null, pdfOutput;
        TextLearner textLearner = new TextLearner();
        float corrCoeff = 0, tmp;
        StringBuilder lingua = new StringBuilder();

        try {
            pdfInput = textLearner.createPdf(Files.readString(testo.toPath()));

        } catch (Exception e) {
        }
        pdfInput = pdfInput.normalize();
        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                pdfOutput = ProbabilityDensityFunction.read(listOfFiles[i].toPath().toString());
                pdfOutput = pdfOutput.normalize();
                tmp = pdfInput.corrCoeff(pdfOutput);
                System.out.println(
                        listOfFiles[i].toString().substring(10, listOfFiles[i].toString().length() - 3) + " = " + tmp);
                if (tmp > corrCoeff) {
                    lingua.replace(0, lingua.length(), listOfFiles[i].toString());
                    corrCoeff = tmp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("la lingua è " + lingua.substring(10, lingua.length() - 3));
    }
}

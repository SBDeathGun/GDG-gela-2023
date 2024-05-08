import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class MainLerner {
    public static void main(String[] args) {
        File folder = new File(args[1]);
        File[] listOfFiles = folder.listFiles();
        TextLearner textLearner = new TextLearner();
        ProbabilityDensityFunction p = null;
        try {
            p = ProbabilityDensityFunction.read("PdfLingue/" + args[0]);
            for (int i = 0; i < listOfFiles.length; i++) {

                p = textLearner.addPdf(Files.readString(listOfFiles[i].toPath()), p);

            }
            p.write("PdfLingue/" + args[0]);

        } catch (Exception e) {
            try {
                p = textLearner.createPdf(Files.readString(listOfFiles[0].toPath()));
                if (listOfFiles.length > 1) {
                    for (int i = 1; i < listOfFiles.length; i++) {
                        p = textLearner.addPdf(Files.readString(listOfFiles[i].toPath()), p);
                    }
                }
                p.write("PdfLingue/" + args[0]);
            } catch (IOException e1) {
            }

        }

    }
}

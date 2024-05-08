import java.io.FileNotFoundException;

public class Learner {

    public static void learn(String file, int[] pix) {
        System.out.println("avvio");
        ImageLearner imageLearner = new ImageLearner();
        ProbabilityDensityFunction p = null;
        try {
            System.out.println("primo try");
            p = ProbabilityDensityFunction.read(file);
            System.out.println("read");
            p = imageLearner.addPdf((int[]) pix, p);
            System.out.println("primo write");
            p.write(file);
            System.out.println("ho finito 1");

        } catch (Exception e) {
            System.out.println("catch");
            p = imageLearner.createPdf((int[]) pix);

            try {
                System.out.println("write");
                p.write(file);
                System.out.println("ho finito");
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }
}

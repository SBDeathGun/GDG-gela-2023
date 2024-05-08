import java.io.IOException;

public class MainTest {

    public static void main(String[] args) {

        try {
            System.out.println(ProbabilityDensityFunction.read(args[0]));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
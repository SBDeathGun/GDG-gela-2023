import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ProbabilityDensityFunction implements Serializable {

    float[] pdf;

    public ProbabilityDensityFunction(int size) {

        pdf = new float[size];

    }

    public void increment(int bin) {

        pdf[bin]++;

    }

    public ProbabilityDensityFunction normalize() {

        float sum = 0.0f;

        for (int i = 0; i < pdf.length; i++) {

            sum += pdf[i];

        }

        for (int i = 0; i < pdf.length; i++) {

            pdf[i] /= sum;

        }

        return this;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pdf.length; i++) {

            sb.append((char) ('a' + i)).append(": ").append(pdf[i]).append('\n');
        }

        return sb.toString();
    }

    public float mean() {

        float sum = 0.0f;

        for (int i = 0; i < pdf.length; i++) {

            sum += pdf[i];

        }

        return sum / (float) pdf.length;
    }

    public float variance() {

        float sum = 0.0f;
        float avg = mean();

        for (int i = 0; i < pdf.length; i++) {

            sum += (pdf[i] - avg) * (pdf[i] - avg);

        }

        return sum / (float) pdf.length;
    }

    public float stdDev() {

        return (float) Math.sqrt(variance());

    }

    public float coVariance(ProbabilityDensityFunction y) {

        float sum = 0.0f;
        float avg = mean();
        float avgy = y.mean();

        for (int i = 0; i < pdf.length; i++) {

            sum += (pdf[i] - avg) * (y.pdf[i] - avgy);

        }

        return sum / (float) pdf.length;

    }

    public float corrCoeff(ProbabilityDensityFunction y) {
        return coVariance(y) / (stdDev() * y.stdDev());

    }

    public void write(String fileName) throws FileNotFoundException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(this);
            outputStream.close();

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public static ProbabilityDensityFunction read(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(fileName)));
        return (ProbabilityDensityFunction) oi.readObject();
    }

}

public class TextLearner {
    private ProbabilityDensityFunction p;

    // TextLearner(String s) {
    // createPdf(s);
    // p = new ProbabilityDensityFunction(26);
    // }

    TextLearner() {

    }

    /*
     * public ProbabilityDensityFunction getPfd() {
     * return p;
     * }
     */

    public ProbabilityDensityFunction createPdf(String s) {
        p = new ProbabilityDensityFunction(26);

        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 97 && s.charAt(i) <= 123)
                this.p.increment(s.charAt(i) - 97);
        }
        return p;

    }

    public ProbabilityDensityFunction addPdf(String s, ProbabilityDensityFunction p) {

        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 97 && s.charAt(i) <= 123)
                p.increment(s.charAt(i) - 97);
        }
        return p;
    }

}

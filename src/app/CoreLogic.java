package app;

public class CoreLogic {

    public double convertCToF(double c) {
        return (c * 1.8) + 32;
    }

    public double convertCToK(double c) {
        return c + 273.15;
    }

    public double convertFToC(double f) {
        return (f - 32) / 1.8;
    }

    public double convertFToK(double f) {
        return (((f - 32) * 5) / 9) + 273.15;
    }

}

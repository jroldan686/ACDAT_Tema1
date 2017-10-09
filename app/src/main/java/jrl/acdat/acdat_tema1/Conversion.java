package jrl.acdat.acdat_tema1;

/**
 * Created by usuario on 29/09/15.
 */
public class Conversion {

    private double cambio;

    public Conversion()
    {
        this.cambio = 0.89;
    }

    public Conversion(double cambio)
    {
        this.cambio = cambio;
    }

    public String convertirADolares(String cantidad) {
        double valor = Double.parseDouble(cantidad) / cambio;
        return String.format("%.2f", valor);
    }

    public String convertirAEuros(String cantidad) {
        double valor = Double.parseDouble(cantidad) * cambio;
        return String.format("%.2f", valor);
    }
}

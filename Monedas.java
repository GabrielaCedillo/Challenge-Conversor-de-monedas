public class Monedas {
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidadConvertida;

    public Monedas(String monedaOrigen, String monedaDestino, double cantidadConvertida) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadConvertida = cantidadConvertida;
    }

    @Override
    public String toString() {
        return cantidadConvertida + " " + monedaDestino;
    }
}

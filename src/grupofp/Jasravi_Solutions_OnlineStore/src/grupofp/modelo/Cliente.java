package grupofp.modelo;

public abstract class Cliente {
    /**
     * incremental?
     */
    private int codigoCliente;
    private String nombre;
    private String domicilio;
    private String nif;
    private String email;
    private boolean premium;


    /**
     * Creamos el constructor de la clase
     * @param codigoCliente int
     * @param nombre String
     * @param domicilio String
     * @param nif String
     * @param email String
     */
    public Cliente(int codigoCliente, String nombre, String domicilio, String nif, String email) {
        this.codigoCliente = codigoCliente;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    /**
     * Creamos los setters & getters
     */
    /**
     *
     * @return
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     *
     * @param codigoCliente
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * Obtener el nombre cliente
     * @return nombre (String)
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Intriducir el nombre cliente
     * @param nombre (String)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener el domicilio cliente
     * @return domicilio (String)
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Introducir el domicilio cliente
     * @param domicilio (String)
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     *
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Creamos el toString
     * @return Devuelve los atributos de nombre, domicilio, nif y email.
     */

    @Override
    public String toString() {
        return "CLIENTE{" +
                "nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

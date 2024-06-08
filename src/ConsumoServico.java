import java.time.LocalDateTime;

public class ConsumoServico {
    private Servico servico;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private LocalDateTime dataConsumo;

    /* CONSTRUTOR */
    public ConsumoServico(Servico servico, Reserva reserva, int quantidadeSolicitada, LocalDateTime dataConsumo) {
        this.servico = servico;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataConsumo = dataConsumo;
    }

    /* GETTERS & SETTERS */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
}

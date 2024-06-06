import java.time.LocalDateTime;

public class Reserva {
    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private LocalDateTime dataEntradaReserva;
    private LocalDateTime dataSaidaReserva;
    private LocalDateTime dataCheckin;
    private LocalDateTime dataCheckout;
    private double valorReserva;
    private double valorPago;

    public Reserva(int codigo, Hospede hospede, Quarto quarto, Funcionario funcionarioReserva, Funcionario funcionarioFechamento, LocalDateTime dataEntradaReserva, LocalDateTime dataSaidaReserva, LocalDateTime dataCheckin, LocalDateTime dataCheckout, double valorReserva, double valorPago) {
        this.codigo = codigo;
        this.hospede = hospede;
        this.quarto = quarto;
        this.funcionarioReserva = funcionarioReserva;
        this.funcionarioFechamento = funcionarioFechamento;
        this.dataEntradaReserva = dataEntradaReserva;
        this.dataSaidaReserva = dataSaidaReserva;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.valorReserva = valorReserva;
        this.valorPago = valorPago;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setFuncionarioReserva(Funcionario funcionarioReserva) {
        this.funcionarioReserva = funcionarioReserva;
    }

    public Funcionario getFuncionarioReserva() {
        return funcionarioReserva;
    }

    public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
        this.funcionarioFechamento = funcionarioFechamento;
    }

    public Funcionario getFuncionarioFechamento() {
        return funcionarioFechamento;
    }

    public void setDataEntradaReserva(LocalDateTime dataEntradaReserva) {
        this.dataEntradaReserva = dataEntradaReserva;
    }

    public LocalDateTime getDataEntradaReserva() {
        return dataEntradaReserva;
    } 

    public void setDataSaidaReserva(LocalDateTime dataSaidaReserva) {
        this.dataSaidaReserva = dataSaidaReserva;
    }

    public LocalDateTime getDataSaidaReserva() {
        return dataSaidaReserva;
    }

    public void setDataCheckin(LocalDateTime dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDateTime getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckout(LocalDateTime dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public LocalDateTime getDataCheckout() {
        return dataCheckout;
    }

    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }

    public double valorReserva() {
        return valorReserva;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double valorPago() {
        return valorPago;
    }
}

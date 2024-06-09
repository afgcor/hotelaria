import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm:ss");

    /* CONSTRUTOR */
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

    /* GETTERS & SETTERS */
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

    public double getValorReserva() {
        return valorReserva;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorPago() {
        return valorPago;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarReserva(Scanner scan) throws IOException {
        String codigo = "";
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR RESERVA ***");
            System.out.print("Insira o código da reserva: ");
            codigo = scan.nextLine();

            Reserva reserva = identificarReserva(Integer.parseInt(codigo));

            if (reserva != null) {
                System.out.println("");
                System.out.println("ERRO: Já existe uma reserva registrada com este código!");
                System.out.println("");
                System.out.println("CÓDIGO: " + reserva.getCodigo() + " | HÓSPEDE: " + reserva.getHospede().getCPF()); 
                System.out.println("FUNCIONÁRIO (RESERVA): " + reserva.getFuncionarioReserva().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ") | FUNCIONÁRIO (FECHAMENTO): " + reserva.getFuncionarioFechamento().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ")");
                System.out.println("DATA DE ENTRADA DA RESERVA: " + reserva.getDataEntradaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataEntradaReserva().format(dtf) + " | DATA DE SAÍDA DA RESERVA: " + reserva.getDataSaidaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataSaidaReserva().format(dtf));
                System.out.println("DATA DO CHECK-IN: " + reserva.getDataCheckin().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckin().format(dtf) + " | DATA DO CHECK-OUT: " + reserva.getDataCheckout().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckout().format(dtf));
                System.out.printf("VALOR TOTAL: R$ %.2f | VALOR PAGO: R$ %.2f%n", reserva.getValorReserva(), reserva.getValorPago());
                throw new NullPointerException();
            }

            System.out.print("Insira o CPF do hóspede: ");
            String cpfHospede = scan.nextLine();

            Hospede hospede = Hospede.identificarHospede(cpfHospede);

            if (hospede == null) {
                System.out.println("");
                System.out.println("ERRO: O CPF informado não corresponde a um hóspede registrado!");
                throw new Exception();
            }

            System.out.print("Insira o código do quarto: ");
            String codigoQuarto = scan.nextLine();

            Quarto quarto = Quarto.identificarQuarto(Integer.parseInt(codigoQuarto));

            if (quarto == null) {
                System.out.println("");
                System.out.println("ERRO: O código informado não corresponde a um quarto registrado!");
                throw new NoSuchFieldException();
            }

            System.out.print("Insira o CPF do funcionário responsável pela abertura da reserva: ");
            String cpfFuncionarioReserva = scan.nextLine();
            String cpf = cpfFuncionarioReserva;

            Funcionario funcionarioReserva = Funcionario.identificarFuncionario(cpf);

            if (funcionarioReserva == null) {
                System.out.println("");
                System.out.println("ERRO: O CPF informado não corresponde a um funcionário registrado!");
                throw new InterruptedException();
            }

            System.out.print("Insira o CPF do funcionário responsável pelo fechamento da reserva: ");
            String cpfFuncionarioFechamento = scan.nextLine();
            cpf = cpfFuncionarioFechamento;

            Funcionario funcionarioFechamento = Funcionario.identificarFuncionario(cpf);

            if (funcionarioFechamento == null) {
                System.out.println("");
                System.out.println("ERRO: O CPF informado não corresponde a um funcionário registrado!");
                throw new InterruptedException();
            }

            System.out.print("Insira a data de abertura (dd/MM/yyy - HH:mm:ss): ");
            String dataEntrada = scan.nextLine();

            System.out.print("Insira a data de fechamento (dd/MM/yyy - HH:mm:ss): ");
            String dataSaida = scan.nextLine();

            System.out.print("Insira a data do check-in (dd/MM/yyy - HH:mm:ss): ");
            String dataCheckin = scan.nextLine();

            System.out.print("Insira a data do check-out (dd/MM/yyy - HH:mm:ss): ");
            String dataCheckout = scan.nextLine();

            String valorReserva = "0.0";
            String valorPago = "0.0";

            if (codigo.isEmpty() || cpfHospede.isEmpty() || codigoQuarto.isEmpty() || cpfFuncionarioReserva.isEmpty() || cpfFuncionarioFechamento.isEmpty() || dataEntrada.isEmpty() || dataSaida.isEmpty() || dataCheckin.isEmpty() || dataCheckout.isEmpty() || valorReserva.isEmpty() || valorPago.isEmpty()) {
                System.out.println("");
                System.out.println("ERRO: Entrada inválida (campos em branco)!");
                System.out.println("Por favor, insira todos os dados da reserva ou encerre a operação.");
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/reservas.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(Integer.parseInt(codigo) + " ; " + hospede.getCPF() + " ; " + quarto.getCodigo() + " ; " + funcionarioReserva.getCPF() + " ; " + funcionarioFechamento.getCPF() + " ; " + dataEntrada + " ; " + dataSaida + " ; " + dataCheckin + " ; " + dataCheckout + " ; " + valorReserva + " ; " + valorPago);
            bw.newLine();
            bw.close();


            System.out.println("RESERVA CADASTRADA!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.print("Continuar cadastro (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarReserva(scan);
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (NoSuchFieldException e) {
            System.out.println("");
            System.out.print("Deseja cadastrar o quarto agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Quarto.cadastrarQuarto(scan);
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (InterruptedException e) {
            System.out.println("");
            System.out.print("Deseja cadastrar o funcionário agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Funcionario.cadastrarFuncionario(scan);
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo reservas.txt!");
        } catch (Exception e) {
            System.out.println("");
            System.out.print("Deseja cadastrar o hóspede agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Hospede.cadastrarHospede(scan);
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        }

        return true;
    }

    public static boolean editarReserva(Scanner scan) throws IOException {
        List<Reserva> listaReservas = new ArrayList<>();
        File reservas = new File("./arquivos/reservas.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(reservas))) {
            if (!reservas.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 11) {
                    Hospede hospede = Hospede.identificarHospede(dados[1]);
                    Quarto quarto = Quarto.identificarQuarto(Integer.parseInt(dados[2]));
                    Funcionario funcionarioReserva = Funcionario.identificarFuncionario(dados[3]);
                    Funcionario funcionarioFechamento = Funcionario.identificarFuncionario(dados[4]);
                    Reserva reserva = new Reserva(Integer.parseInt(dados[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, LocalDateTime.parse(dados[5], dtf), LocalDateTime.parse(dados[6], dtf), LocalDateTime.parse(dados[7], dtf), LocalDateTime.parse(dados[8], dtf), Double.parseDouble(dados[9]), Double.parseDouble(dados[10]));
                    listaReservas.add(reserva);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR RESERVA ***");
        System.out.print("Digite o código da reserva que será editada: ");
        String codigo = scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigo() == Integer.parseInt(codigo)) {
                cadastrado = true;
                System.out.println("Reserva identificada!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Código atual: " + reserva.getCodigo());
                System.out.print("Novo código: ");
                String codigoAtualizadoS = scan.nextLine();
                int codigoAtualizado;
                if (codigoAtualizadoS.isEmpty()) {
                    codigoAtualizado = reserva.getCodigo();
                } else {
                    codigoAtualizado = Integer.parseInt(codigoAtualizadoS);
                }
                System.out.println("Hóspede atual: " + reserva.getHospede().getNome() + " (CPF " + reserva.getHospede().getCPF() + ")");
                System.out.print("Novo hóspede: ");
                String hospedeAtualizado = scan.nextLine();
                if (hospedeAtualizado.isEmpty()) {
                    hospedeAtualizado = reserva.getHospede().getCPF();
                }
                System.out.println("Quarto atual: " + reserva.getQuarto().getCodigo());
                System.out.print("Novo quarto: ");
                String quartoAtualizadoS = scan.nextLine();
                int quartoAtualizado;
                if (quartoAtualizadoS.isEmpty()) {
                    quartoAtualizado = reserva.getQuarto().getCodigo();
                } else {
                    quartoAtualizado = Integer.parseInt(quartoAtualizadoS);
                }
                System.out.println("Funcionário (reserva) atual: " + reserva.getFuncionarioReserva().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ")");
                System.out.print("Novo funcionário (reserva): ");
                String reservaAtualizado = scan.nextLine();
                if (reservaAtualizado.isEmpty()) {
                    reservaAtualizado = reserva.getFuncionarioReserva().getCPF();
                }
                System.out.println("Funcionário (fechamento) atual: " + reserva.getFuncionarioFechamento().getNome() + " (CPF " + reserva.getFuncionarioFechamento().getCPF() + ")");
                System.out.print("Novo funcionário (fechamento): ");
                String fechamentoAtualizado = scan.nextLine();
                if (fechamentoAtualizado.isEmpty()) {
                    fechamentoAtualizado = reserva.getFuncionarioFechamento().getCPF();
                }
                System.out.println("Data de entrada da reserva atual: " + reserva.getDataEntradaReserva().format(dtf));
                System.out.print("Nova data de entrada da reserva atual (dd/MM/yyy - HH:mm:ss): ");
                String dataEntradaAtualizadaS = scan.nextLine();
                LocalDateTime dataEntradaAtualizada;
                if (dataEntradaAtualizadaS.isEmpty()) {
                    dataEntradaAtualizada = reserva.getDataEntradaReserva();
                } else {
                    dataEntradaAtualizada = LocalDateTime.parse(dataEntradaAtualizadaS, dtf);
                }
                System.out.println("Data de saída da reserva atual: " + reserva.getDataSaidaReserva().format(dtf));
                System.out.print("Nova data de saída da reserva atual (dd/MM/yyy - HH:mm:ss): ");
                String dataSaidaAtualizadaS = scan.nextLine();
                LocalDateTime dataSaidaAtualizada;
                if (dataSaidaAtualizadaS.isEmpty()) {
                    dataSaidaAtualizada = reserva.getDataSaidaReserva();
                } else {
                    dataSaidaAtualizada = LocalDateTime.parse(dataSaidaAtualizadaS, dtf);
                }
                System.out.println("Data de check-in da reserva atual: " + reserva.getDataCheckin().format(dtf));
                System.out.print("Nova data de check-in da reserva atual (dd/MM/yyy - HH:mm:ss): ");
                String dataCheckinAtualizadaS = scan.nextLine();
                LocalDateTime dataCheckinAtualizada;
                if (dataCheckinAtualizadaS.isEmpty()) {
                    dataCheckinAtualizada = reserva.getDataCheckin();
                } else {
                    dataCheckinAtualizada = LocalDateTime.parse(dataCheckinAtualizadaS, dtf);
                }
                System.out.println("Data de check-out da reserva atual: " + reserva.getDataCheckout().format(dtf));
                System.out.print("Nova data de check-out da reserva atual (dd/MM/yyy - HH:mm:ss): ");
                String dataCheckoutAtualizadaS = scan.nextLine();
                LocalDateTime dataCheckoutAtualizada;
                if (dataCheckoutAtualizadaS.isEmpty()) {
                    dataCheckoutAtualizada = reserva.getDataCheckout();
                } else {
                    dataCheckoutAtualizada = LocalDateTime.parse(dataCheckoutAtualizadaS, dtf);
                }
                System.out.printf("Valor da reserva atual: R$ %.2f%n", reserva.getValorReserva());
                System.out.print("Novo valor da reserva: ");
                String valorReservaAtualizadoS = scan.nextLine();
                double valorReservaAtualizado;
                if (valorReservaAtualizadoS.isEmpty()) {
                    valorReservaAtualizado = reserva.getValorReserva();
                } else {
                    valorReservaAtualizado = Double.parseDouble(valorReservaAtualizadoS);
                }
                System.out.printf("Valor pago atual: R$ %.2f%n", reserva.getValorPago());
                System.out.print("Novo valor pago: ");
                String valorPagoAtualizadoS = scan.nextLine();
                double valorPagoAtualizado;
                if (valorPagoAtualizadoS.isEmpty()) {
                    valorPagoAtualizado = reserva.getValorReserva();
                } else {
                    valorPagoAtualizado = Double.parseDouble(valorPagoAtualizadoS);
                }
                String linhaAtualizada = (codigoAtualizado + " ; " + hospedeAtualizado + " ; " + quartoAtualizado + " ; " + reservaAtualizado + " ; " + fechamentoAtualizado + " ; " + dataEntradaAtualizada.format(dtf) + " ; " + dataSaidaAtualizada.format(dtf) + " ; " + dataCheckinAtualizada.format(dtf) + " ; " + dataCheckoutAtualizada.format(dtf) + " ; " + valorReservaAtualizado + " ; " + valorPagoAtualizado);
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(reserva.getCodigo() + " ; " + reserva.getHospede().getCPF() + " ; " + reserva.getFuncionarioReserva().getCPF() + " ; " + reserva.getFuncionarioFechamento().getCPF() + " ; " + reserva.getDataEntradaReserva() + " ; " + reserva.getDataSaidaReserva() + " ; " + reserva.getDataCheckin() + " ; " + reserva.getDataCheckout() + " ; " + reserva.getValorReserva() + " ; " + reserva.getValorPago());
            }
        }

        if (!cadastrado) {
            System.out.println("Reserva com código " + codigo + " não encontrada.");
            return false;
        }

        FileWriter fw = new FileWriter(reservas);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo reservas.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo reservas.txt!");
        }

        return true;
    }

    public static Reserva consultarReserva(Scanner scan) {
        List<Reserva> listaReservas = new ArrayList<>();
        File reservas = new File("./arquivos/reservas.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(reservas))) {
            if (!reservas.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 11) {
                    Hospede hospede = Hospede.identificarHospede(dados[1]);
                    Quarto quarto = Quarto.identificarQuarto(Integer.parseInt(dados[2]));
                    Funcionario funcionarioReserva = Funcionario.identificarFuncionario(dados[3]);
                    Funcionario funcionarioFechamento = Funcionario.identificarFuncionario(dados[4]);
                    Reserva reserva = new Reserva(Integer.parseInt(dados[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, LocalDateTime.parse(dados[5], dtf), LocalDateTime.parse(dados[6], dtf), LocalDateTime.parse(dados[7], dtf), LocalDateTime.parse(dados[8], dtf), Double.parseDouble(dados[9]), Double.parseDouble(dados[10]));
                    listaReservas.add(reserva);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo reservas.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo reservas.txt!");
        }

        System.out.println("");
        System.out.println("*** CONSULTAR RESERVA ***");
        System.out.print("Digite o código da reserva que será consultada: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;

        for (int i = 0; i < listaReservas.size(); i++) {
            if (listaReservas.get(i).getCodigo() == codigo) {
                cadastrado = true;
                Reserva reserva = listaReservas.get(i);
                System.out.println("Categoria (item) identificada!");
                System.out.println("");
                System.out.println("CÓDIGO: " + reserva.getCodigo() + " | HÓSPEDE: " + reserva.getHospede().getCPF()); 
                System.out.println("FUNCIONÁRIO (RESERVA): " + reserva.getFuncionarioReserva().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ") | FUNCIONÁRIO (FECHAMENTO): " + reserva.getFuncionarioFechamento().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ")");
                System.out.println("DATA DE ENTRADA DA RESERVA: " + reserva.getDataEntradaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataEntradaReserva().format(dtf) + " | DATA DE SAÍDA DA RESERVA: " + reserva.getDataSaidaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataSaidaReserva().format(dtf));
                System.out.println("DATA DO CHECK-IN: " + reserva.getDataCheckin().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckin().format(dtf) + " | DATA DO CHECK-OUT: " + reserva.getDataCheckout().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckout().format(dtf));
                System.out.printf("VALOR TOTAL: R$ %.2f | VALOR PAGO: R$ %.2f%n", reserva.getValorReserva(), reserva.getValorPago());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Reserva com código " + codigo + " não encontrada.");
        }

        return null;
    }

    public static List<Reserva> listarReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        File reservas = new File("./arquivos/reservas.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(reservas))) {

            if (!reservas.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 11) {
                    Hospede hospede = Hospede.identificarHospede(dados[1]);
                    Quarto quarto = Quarto.identificarQuarto(Integer.parseInt(dados[2]));
                    Funcionario funcionarioReserva = Funcionario.identificarFuncionario(dados[3]);
                    Funcionario funcionarioFechamento = Funcionario.identificarFuncionario(dados[4]);
                    Reserva reserva = new Reserva(Integer.parseInt(dados[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, LocalDateTime.parse(dados[5], dtf), LocalDateTime.parse(dados[6], dtf), LocalDateTime.parse(dados[7], dtf), LocalDateTime.parse(dados[8], dtf), Double.parseDouble(dados[9]), Double.parseDouble(dados[10]));
                    listaReservas.add(reserva);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE RESERVAS ***");
            for (Reserva reserva : listaReservas) {
                System.out.println("");
                System.out.println("RESERVA " + (listaReservas.indexOf(reserva) + 1));
                System.out.println("CÓDIGO: " + reserva.getCodigo() + " | HÓSPEDE: " + reserva.getHospede().getCPF()); 
                System.out.println("FUNCIONÁRIO (RESERVA): " + reserva.getFuncionarioReserva().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ") | FUNCIONÁRIO (FECHAMENTO): " + reserva.getFuncionarioFechamento().getNome() + " (CPF " + reserva.getFuncionarioReserva().getCPF() + ")");
                System.out.println("DATA DE ENTRADA DA RESERVA: " + reserva.getDataEntradaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataEntradaReserva().format(dtf) + " | DATA DE SAÍDA DA RESERVA: " + reserva.getDataSaidaReserva().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataSaidaReserva().format(dtf));
                System.out.println("DATA DO CHECK-IN: " + reserva.getDataCheckin().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckin().format(dtf) + " | DATA DO CHECK-OUT: " + reserva.getDataCheckout().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + reserva.getDataCheckout().format(dtf));
                System.out.printf("VALOR TOTAL: R$ %.2f | VALOR PAGO: R$ %.2f%n", reserva.getValorReserva(), reserva.getValorPago());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo reservas.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo reservas.txt!");
        }
        System.out.println("");
        return listaReservas;
    }

    public static List<Reserva> leituraReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        File reservas = new File("./arquivos/reservas.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(reservas))) {
    
            if (!reservas.exists()) {
                throw new FileNotFoundException();
            }
    
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 11) {
                    Hospede hospede = Hospede.identificarHospede(dados[1]);
                    Quarto quarto = Quarto.identificarQuarto(Integer.parseInt(dados[2]));
                    Funcionario funcionarioReserva = Funcionario.identificarFuncionario(dados[3]);
                    Funcionario funcionarioFechamento = Funcionario.identificarFuncionario(dados[4]);
                    Reserva reserva = new Reserva(Integer.parseInt(dados[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, LocalDateTime.parse(dados[5], dtf), LocalDateTime.parse(dados[6], dtf), LocalDateTime.parse(dados[7], dtf), LocalDateTime.parse(dados[8], dtf), Double.parseDouble(dados[9]), Double.parseDouble(dados[10]));
                    listaReservas.add(reserva);
                }
            }            
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaReservas;
    }

    public static Reserva identificarReserva(int codigo) {
        List<Reserva> listaReservas = leituraReservas();
        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigo() == codigo) {
                return reserva;
            }
        }
        return null;
    }

    public static double valorConsumoServicos(int idReserva) {
        double total = 0;
        List<ConsumoServico> listaConsumosServicos = ConsumoServico.leituraConsumosServicos();
        File consumosservicos = new File("./arquivos/consumosservicos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(consumosservicos))) {
            if (!consumosservicos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Servico servico = Servico.identificarServico(Integer.parseInt(dados[0]));
                    Reserva reserva = Reserva.identificarReserva(Integer.parseInt(dados[1]));
                    if (reserva != null) {
                        ConsumoServico consumoServico = new ConsumoServico(servico, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumosServicos.add(consumoServico);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumosservicos.txt!");
        }

        for (ConsumoServico consumoServico : listaConsumosServicos) {
            if (consumoServico.getReserva().getCodigo() == idReserva) {
                total += consumoServico.getServico().getValor() * consumoServico.getQuantidadeSolicitada();
            }
        }

        return total;
    }

    public static double valorConsumoItens(int idReserva) {
        double total = 0;
        List<Consumo> listaConsumos = Consumo.leituraConsumos();
        File consumos = new File("./arquivos/consumos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(consumos))) {
            if (!consumos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Item item = Item.identificarItem(Integer.parseInt(dados[0]));
                    Reserva reserva = Reserva.identificarReserva(Integer.parseInt(dados[1]));
                    if (reserva != null) {
                        Consumo consumo = new Consumo(item, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumos.add(consumo);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumos.txt!");
        }

        for (Consumo consumo : listaConsumos) {
            if (consumo.getReserva().getCodigo() == idReserva) {
                total += consumo.getItem().getValor() * consumo.getQuantidadeSolicitada();
            }
        }

        return total;
    }

    public static double valorTotalConsumos(Scanner scan, String idReserva) {
        double valorTotal = 0.0;
        valorTotal = valorConsumoServicos(Integer.parseInt(idReserva)) + valorConsumoItens(Integer.parseInt(idReserva));
        return valorTotal;
    }

    public static void pagarReserva(Scanner scan) {
        File reservas = new File("./arquivos/reservas.txt");
    
        System.out.println("");
        System.out.print("Insira o código da reserva: ");
        String codigo = scan.nextLine();
    
        if (!codigo.isEmpty()) {
            Reserva reservaConsulta = identificarReserva(Integer.parseInt(codigo));
            int quantiaDiarias = reservaConsulta.getDataCheckout().getDayOfMonth() - reservaConsulta.getDataCheckin().getDayOfMonth();
            if (reservaConsulta.getDataCheckout().getHour() > 12) {
                quantiaDiarias++;
            }
            double valorTotalDiarias = (quantiaDiarias * reservaConsulta.getQuarto().getCategoria().getValor());
            double valorConsumos = valorTotalConsumos(scan, codigo);
            double valorFinal = valorTotalDiarias + valorConsumos;
    
            System.out.println("");
            System.out.println("Total (diárias): R$ " + valorTotalDiarias);
            System.out.println("Total (itens e serviços): R$ " + valorConsumos);
            System.out.println("---------------------------------------");
            System.out.println("TOTAL: R$ " + valorFinal);
            System.out.println("");
            System.out.print("Receber pagamento pela reserva (S/N)? ");
            String opcao = scan.nextLine();
    
            switch (opcao.toUpperCase()) {
                case "S":
                    System.out.println("");
                    System.out.print("Insira o valor do pagamento: ");
                    String valorPagamento = scan.nextLine();
                    reservaConsulta.setValorReserva(valorFinal);
                    reservaConsulta.setValorPago(Double.parseDouble(valorPagamento));
    
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(reservas));
                        List<String> listaLinhas = new ArrayList<>();
                        String linha;
    
                        while ((linha = br.readLine()) != null) {
                            String[] partes = linha.split(" ; ");
                            if (partes.length >= 11 && partes[0].equals(codigo)) {
                                linha = codigo + " ; " + reservaConsulta.getHospede().getCPF() + " ; " + reservaConsulta.getQuarto().getCodigo() + " ; " +
                                        reservaConsulta.getFuncionarioReserva().getCPF() + " ; " + reservaConsulta.getFuncionarioFechamento().getCPF() + " ; " +
                                        reservaConsulta.getDataEntradaReserva().format(dtf) + " ; " + reservaConsulta.getDataSaidaReserva().format(dtf) + " ; " +
                                        reservaConsulta.getDataCheckin().format(dtf) + " ; " + reservaConsulta.getDataCheckout().format(dtf) + " ; " +
                                        valorFinal + " ; " + Double.parseDouble(valorPagamento);
                            }
                            listaLinhas.add(linha);
                        }
                        br.close();
    
                        BufferedWriter bw = new BufferedWriter(new FileWriter(reservas));
                        for (String linhaAtualizada : listaLinhas) {
                            bw.write(linhaAtualizada);
                            bw.newLine();
                        }
                        bw.close();
    
                        System.out.println("");
                        System.out.println("Pagamento recebido!");
                        System.out.println("Valor pendente: " + (valorFinal - Double.parseDouble(valorPagamento)));
                    } catch (IOException e) {
                        System.out.println("Erro ao gravar no arquivo reservas.txt");
                    }
                    break;
                case "N":
                    System.out.println("ERRO: O pagamento da reserva " + reservaConsulta.getCodigo() + " ainda não foi quitado!");
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
            }
        } else {
            System.out.println("ERRO: O código da reserva não pode estar vazio!");
        }
    }    

    @Override
    public String toString() {
        return "Reserva{codigo='" + getCodigo() + "', hospede='" + getHospede().getNome() + "(" + getHospede().getCPF() + ")', quarto='" + getQuarto().getCodigo() + "(" + getQuarto().getCategoria().getDescricao() + ")', funcionarioReserva='" + getFuncionarioReserva().getNome() + "(" + getFuncionarioReserva().getCPF() +  ")', funcionarioReserva='" + getFuncionarioFechamento().getNome() + "(" + getFuncionarioFechamento().getCPF() + ")', dataEntradaReserva='" + getDataEntradaReserva().format(dtf) + "', dataSaidaReserva='" + getDataSaidaReserva().format(dtf) + "', dataCheckin='" + getDataCheckin().format(dtf) + "', dataCheckout='" + getDataCheckout().format(dtf) + "', valorReserva='" + getValorReserva() + "', valorPago='" + getValorPago() + "'}";
    }
}
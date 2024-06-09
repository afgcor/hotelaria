import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsumoServico {
    private Servico servico;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private LocalDateTime dataConsumo;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm:ss");

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
    public static boolean cadastrarConsumoServico() throws IOException {
        Scanner scan = new Scanner(System.in);
        String quantidadeSolicitada = "";
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR CONSUMO (SERVIÇO) ***");
            System.out.print("Insira o código do serviço: ");
            String idServico = scan.nextLine();

            Servico servico = Servico.identificarServico(Integer.parseInt(idServico));

            if (servico == null) {
                System.out.println("");
                System.out.println("ERRO: O código informado não corresponde a um serviço registrado!");
                throw new InterruptedException();
            }
            
            System.out.print("Insira o código da reserva: ");
            String idReserva = scan.nextLine();

            Reserva reserva = Reserva.identificarReserva(Integer.parseInt(idReserva));

            if (reserva == null) {
                System.out.println("");
                System.out.println("ERRO: O código informado não corresponde a uma reserva registrada!");
                throw new Exception();
            }

            System.out.print("Insira a quantidade solicitada: ");
            quantidadeSolicitada = scan.nextLine();

            System.out.print("Insira a data do consumo (dd/MM/yyy - HH:mm:ss): ");
            String dataConsumo = scan.nextLine();
            
            if (idServico.isEmpty() || idReserva.isEmpty() || quantidadeSolicitada.isEmpty() || dataConsumo.isEmpty()) {
                System.out.println("");
                System.out.println("ERRO: Entrada inválida (campos em branco)!");
                System.out.println("Por favor, insira todos os dados do consumo ou encerre a operação.");
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/consumosservicos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(servico.getCodigo() + " ; " + reserva.getCodigo() + " ; " + quantidadeSolicitada + " ; " + dataConsumo);
            bw.newLine();
            bw.close();

            System.out.println("CONSUMO (SERVIÇO) CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.print("Continuar cadastro (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarConsumoServico();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo consumosservicos.txt!");
        } catch (InterruptedException e) {
            System.out.println("");
            System.out.print("Deseja cadastrar o serviço agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Servico.cadastrarServico();
                    break;
                case "N":
                    break;
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.print("Deseja cadastrar a reserva agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Reserva.cadastrarReserva();
                    break;
                case "N":
                    break;
            }
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarConsumoServico() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<ConsumoServico> listaConsumosServicos = new ArrayList<>();
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
                    if (servico != null && reserva != null) {
                        ConsumoServico consumoServico = new ConsumoServico(servico, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumosServicos.add(consumoServico);
                    }
                }
            }
    
            System.out.println("");
            System.out.println("*** EDITAR CONSUMO (SERVIÇO) ***");
            System.out.print("Digite o código da reserva cujo consumo será editado: ");
            String codigoReserva = scan.nextLine();
            System.out.println("");
    
            boolean reservaEncontrada = false;
            for (ConsumoServico consumoServico : listaConsumosServicos) {
                if (consumoServico.getReserva().getCodigo() == Integer.parseInt(codigoReserva)) {
                    reservaEncontrada = true;
                    System.out.println("Reserva identificada!");
                    System.out.println("Consumos identificados nesta reserva:");
    
                    List<ConsumoServico> consumosEncontrados = new ArrayList<>();
                    for (ConsumoServico consultaConsumos : listaConsumosServicos) {
                        if (consultaConsumos.getReserva().getCodigo() == Integer.parseInt(codigoReserva)) {
                            consumosEncontrados.add(consultaConsumos);
                            System.out.println("DATA: " + consultaConsumos.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaConsumos.getDataConsumo().format(dtf) + " | SERVIÇO: " + consultaConsumos.getServico().getDescricao() + ", Cód. " + consultaConsumos.getServico().getCodigo());
                        }
                    }
    
                    System.out.println("");
                    System.out.print("Insira a data do consumo que será editado (dd-MM-yyyy, HH:mm:ss): ");
                    String dataEscolhida = scan.nextLine();
                    LocalDateTime dataEscolhidaLDT = LocalDateTime.parse(dataEscolhida, dtf);
    
                    boolean consumoEncontrado = false;
                    for (ConsumoServico consultaConsumos : consumosEncontrados) {
                        if (consultaConsumos.getDataConsumo().equals(dataEscolhidaLDT)) {
                            consumoEncontrado = true;
                            System.out.println("Serviço identificado!");
                            System.out.println("");
    
                            System.out.println("*** ALTERAR DADOS ***");
                            System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                            System.out.println("");
                            System.out.println("Serviço atual: " + consultaConsumos.getServico().getDescricao() + ", Cód. " + consultaConsumos.getServico().getCodigo());
                            System.out.print("Novo serviço (código): ");
                            String codigoServicoAtualizadoS = scan.nextLine();
                            int codigoServicoAtualizado;
                            if (codigoServicoAtualizadoS.isEmpty()) {
                                codigoServicoAtualizado = consultaConsumos.getServico().getCodigo();
                            } else {
                                codigoServicoAtualizado = Integer.parseInt(codigoServicoAtualizadoS);
                            }
    
                            System.out.println("Reserva atual: " + consultaConsumos.getReserva().getCodigo() + ", Quarto " + consultaConsumos.getReserva().getQuarto().getCodigo());
                            System.out.print("Nova reserva (código): ");
                            String codigoReservaAtualizadaS = scan.nextLine();
                            int codigoReservaAtualizada;
                            if (codigoReservaAtualizadaS.isEmpty()) {
                                codigoReservaAtualizada = consultaConsumos.getReserva().getCodigo();
                            } else {
                                codigoReservaAtualizada = Integer.parseInt(codigoReservaAtualizadaS);
                            }
    
                            System.out.println("Quantidade atual: " + consultaConsumos.getQuantidadeSolicitada());
                            System.out.print("Nova quantidade: ");
                            String quantidadeAtualizadaS = scan.nextLine();
                            int quantidadeAtualizada;
                            if (quantidadeAtualizadaS.isEmpty()) {
                                quantidadeAtualizada = consultaConsumos.getQuantidadeSolicitada();
                            } else {
                                quantidadeAtualizada = Integer.parseInt(quantidadeAtualizadaS);
                            }
    
                            System.out.println("Data do consumo atual: " + consultaConsumos.getDataConsumo().format(dtf));
                            System.out.print("Nova data do consumo: ");
                            String dataAtualizadaS = scan.nextLine();
                            LocalDateTime dataAtualizada;
                            if (dataAtualizadaS.isEmpty()) {
                                dataAtualizada = consultaConsumos.getDataConsumo();
                            } else {
                                dataAtualizada = LocalDateTime.parse(dataAtualizadaS, dtf);
                            }
    
                            consultaConsumos.setServico(Servico.identificarServico(codigoServicoAtualizado));
                            consultaConsumos.setReserva(Reserva.identificarReserva(codigoReservaAtualizada));
                            consultaConsumos.setQuantidadeSolicitada(quantidadeAtualizada);
                            consultaConsumos.setDataConsumo(dataAtualizada);
    
                            System.out.println("");
                            System.out.println("Dados atualizados com sucesso!");
                            break;
                        }
                    }
    
                    if (!consumoEncontrado) {
                        System.out.println("ERRO: Consumo com a data especificada não encontrado!");
                        return false;
                    }
                    break;
                }
            }
    
            if (!reservaEncontrada) {
                System.out.println("Reserva com código " + codigoReserva + " não encontrada.");
                return false;
            }
    
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(consumosservicos))) {
                for (ConsumoServico consultaConsumos : listaConsumosServicos) {
                    String linhaAtualizada = (consultaConsumos.getServico().getCodigo() + " ; " + consultaConsumos.getReserva().getCodigo() + " ; " + consultaConsumos.getQuantidadeSolicitada() + " ; " + consultaConsumos.getDataConsumo().format(dtf));
                    bw.write(linhaAtualizada);
                    bw.newLine();
                }
            }
    
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumosservicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo consumosservicos.txt!");
        } finally {
            scan.close();
        }
    
        return true;
    }

    public static ConsumoServico consultarConsumoServico() {
        List<ConsumoServico> listaConsumosServicos = new ArrayList<>();
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
                    if (servico != null && reserva != null) {
                        ConsumoServico consumoServico = new ConsumoServico(servico, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumosServicos.add(consumoServico);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumosservicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumosservicos.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR CONSUMO (SERVIÇO) ***");
        System.out.println("");
        System.out.print("Deseja consultar os serviços de uma reserva específica (S/N)? ");
        String opcao = scan.nextLine();
        System.out.println("");
        boolean cadastrado = false;
        int codigo = 0;

        if (opcao.equalsIgnoreCase("S")) {
            System.out.print("Digite a reserva cujos consumos serão consultados: ");
            codigo = scan.nextInt();
            scan.nextLine();
            System.out.println("");
            List<ConsumoServico> consumosEncontrados = new ArrayList<>();
            for (ConsumoServico consultaReserva : listaConsumosServicos) {
                if (consultaReserva.getReserva().getCodigo() == (codigo)) {
                cadastrado = true;
                consumosEncontrados.add(consultaReserva);
                System.out.println("DATA: " + consultaReserva.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaReserva.getDataConsumo().format(dtf) + " | SERVIÇO: " + consultaReserva.getServico().getDescricao() + ", Cód. " + consultaReserva.getServico().getCodigo());
                }
            }
        } else if (opcao.equalsIgnoreCase("N")) {
            System.out.println("Consumo (serviço) identificado!");
            for (ConsumoServico consultaGeral : listaConsumosServicos) {
                cadastrado = true;
                System.out.println("");
                System.out.println("CONSUMO (SERVIÇO) " + (listaConsumosServicos.indexOf(consultaGeral) + 1));
                System.out.println("RESERVA: " + consultaGeral.getReserva().getCodigo() + ", Quarto " + consultaGeral.getReserva().getQuarto().getCodigo() + " | DATA: " + consultaGeral.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaGeral.getDataConsumo().format(dtf) + " | SERVIÇO: " + consultaGeral.getServico().getDescricao() + ", Cód. " + consultaGeral.getServico().getCodigo() + " | QUANTIDADE: " + consultaGeral.getQuantidadeSolicitada());
            }
        } else {
            System.out.println("ERRO: Opção inválida!");
            System.out.println("");
            scan.close();
            return null;
        }

        System.out.println("");
        scan.close();

        if (!cadastrado) {
            System.out.println("Consumo (serviço) com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<ConsumoServico> listarConsumosServicos() {
        List<ConsumoServico> listaConsumosServicos = new ArrayList<>();
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
                    if (servico != null && reserva != null) {
                        ConsumoServico consumoServico = new ConsumoServico(servico, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumosServicos.add(consumoServico);
                    }
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE CONSUMOS (SERVIÇOS) ***");
            for (ConsumoServico consumoServico : listaConsumosServicos) {
                System.out.println("");
                System.out.println("CONSUMO (SERVIÇO) " + (listaConsumosServicos.indexOf(consumoServico) + 1));
                System.out.println("DATA: " + consumoServico.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consumoServico.getDataConsumo().format(dtf) + " | SERVIÇO: " + consumoServico.getServico().getDescricao() + ", Cód. " + consumoServico.getServico().getCodigo());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumosservicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumosservicos.txt!");
        }
        System.out.println("");
        return listaConsumosServicos;
    }

    public static List<ConsumoServico> leituraConsumosServicos() {
        List<ConsumoServico> listaConsumosServicos = new ArrayList<>();
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
                    ConsumoServico consumoServico = new ConsumoServico(servico, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                    listaConsumosServicos.add(consumoServico);
                }
            }            
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaConsumosServicos;
    }

    public static ConsumoServico identificarConsumoServico(int idReserva) {
        List<ConsumoServico> listaConsumosServicos = leituraConsumosServicos();
        for (ConsumoServico consumoServico : listaConsumosServicos) {
            if (consumoServico.getReserva().getCodigo() == idReserva) {
                return consumoServico;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ConsumoServico{servico='" + getServico().getDescricao() + "(" + getServico().getCodigo() + ")', reserva='" + getReserva().getCodigo() + "', quantidadeSolicitada='" + getQuantidadeSolicitada() + "', dataConsumo='" + getDataConsumo().format(dtf) + "'}";
    }
}

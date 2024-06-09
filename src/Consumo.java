import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Consumo {
    private Item item;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private LocalDateTime dataConsumo;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm:ss");

    /* CONSTRUTOR */
    public Consumo(Item item, Reserva reserva, int quantidadeSolicitada, LocalDateTime dataConsumo) {
        this.item = item;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataConsumo = dataConsumo;
    }

    /* GETTERS & SETTERS */
    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
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
    public static boolean cadastrarConsumo() throws IOException {
        Scanner scan = new Scanner(System.in);
        String quantidadeSolicitada = "";
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR CONSUMO ***");
            System.out.print("Insira o código do item: ");
            String idItem = scan.nextLine();

            Item item = Item.identificarItem(Integer.parseInt(idItem));

            if (item == null) {
                System.out.println("");
                System.out.println("ERRO: O código informado não corresponde a um item registrado!");
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
            
            if (idItem.isEmpty() || idReserva.isEmpty() || quantidadeSolicitada.isEmpty() || dataConsumo.isEmpty()) {
                System.out.println("");
                System.out.println("ERRO: Entrada inválida (campos em branco)!");
                System.out.println("Por favor, insira todos os dados do consumo ou encerre a operação.");
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/consumos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(item.getCodigo() + " ; " + reserva.getCodigo() + " ; " + quantidadeSolicitada + " ; " + dataConsumo);
            bw.newLine();
            bw.close();

            System.out.println("CONSUMO CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.print("Continuar cadastro (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarConsumo();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo consumos.txt!");
        } catch (InterruptedException e) {
            System.out.println("");
            System.out.print("Deseja cadastrar o item agora (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    Item.cadastrarItem();
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

    public static boolean editarConsumo() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Consumo> listaConsumos = new ArrayList<>();
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
                    if (item != null && reserva != null) {
                        Consumo consumo = new Consumo(item, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumos.add(consumo);
                    }
                }
            }
    
            System.out.println("");
            System.out.println("*** EDITAR CONSUMO ***");
            System.out.print("Digite o código da reserva cujo consumo será editado: ");
            String codigoReserva = scan.nextLine();
            System.out.println("");
    
            boolean reservaEncontrada = false;
            for (Consumo consumo : listaConsumos) {
                if (consumo.getReserva().getCodigo() == Integer.parseInt(codigoReserva)) {
                    reservaEncontrada = true;
                    System.out.println("Reserva identificada!");
                    System.out.println("Consumos identificados nesta reserva:");
    
                    List<Consumo> consumosEncontrados = new ArrayList<>();
                    for (Consumo consultaConsumos : listaConsumos) {
                        if (consultaConsumos.getReserva().getCodigo() == Integer.parseInt(codigoReserva)) {
                            consumosEncontrados.add(consultaConsumos);
                            System.out.println("ITEM: " + consultaConsumos.getItem().getDescricao() + ", Cód. " + consultaConsumos.getItem().getCodigo() + " | VALOR: " + consultaConsumos.getItem().getValor() + " | QUANTIDADE: " + consultaConsumos.getQuantidadeSolicitada() + "DATA: " + consultaConsumos.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaConsumos.getDataConsumo().format(dtf));
                        }
                    }
    
                    System.out.println("");
                    System.out.print("Insira a data do consumo que será editado (dd-MM-yyyy, HH:mm:ss): ");
                    String dataEscolhida = scan.nextLine();
                    LocalDateTime dataEscolhidaLDT = LocalDateTime.parse(dataEscolhida, dtf);
    
                    boolean consumoEncontrado = false;
                    for (Consumo consultaConsumos : consumosEncontrados) {
                        if (consultaConsumos.getDataConsumo().equals(dataEscolhidaLDT)) {
                            consumoEncontrado = true;
                            System.out.println("Consumo identificado!");
                            System.out.println("");
    
                            System.out.println("*** ALTERAR DADOS ***");
                            System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                            System.out.println("");
                            System.out.println("Item atual: " + consultaConsumos.getItem().getDescricao() + ", Cód. " + consultaConsumos.getItem().getCodigo());
                            System.out.print("Novo item (código): ");
                            String codigoItemAtualizadoS = scan.nextLine();
                            int codigoItemAtualizado;
                            if (codigoItemAtualizadoS.isEmpty()) {
                                codigoItemAtualizado = consultaConsumos.getItem().getCodigo();
                            } else {
                                codigoItemAtualizado = Integer.parseInt(codigoItemAtualizadoS);
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
    
                            consultaConsumos.setItem(Item.identificarItem(codigoItemAtualizado));
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
    
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(consumos))) {
                for (Consumo consultaConsumos : listaConsumos) {
                    String linhaAtualizada = (consultaConsumos.getItem().getCodigo() + " ; " + consultaConsumos.getReserva().getCodigo() + " ; " + consultaConsumos.getQuantidadeSolicitada() + " ; " + consultaConsumos.getDataConsumo().format(dtf));
                    bw.write(linhaAtualizada);
                    bw.newLine();
                }
            }
    
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo consumos.txt!");
        } finally {
            scan.close();
        }
    
        return true;
    }

    public static Consumo consultarConsumo() {
        List<Consumo> listaConsumos = new ArrayList<>();
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
                    if (item != null && reserva != null) {
                        Consumo consumo = new Consumo(item, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumos.add(consumo);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumos.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR CONSUMO ***");
        System.out.println("");
        System.out.print("Deseja consultar os itens de uma reserva específica (S/N)? ");
        String opcao = scan.nextLine();
        System.out.println("");
        boolean cadastrado = false;
        int codigo = 0;

        if (opcao.equalsIgnoreCase("S")) {
            System.out.print("Digite a reserva cujos consumos serão consultados: ");
            codigo = scan.nextInt();
            scan.nextLine();
            System.out.println("");
            List<Consumo> consumosEncontrados = new ArrayList<>();
            for (Consumo consultaReserva : listaConsumos) {
                if (consultaReserva.getReserva().getCodigo() == (codigo)) {
                cadastrado = true;
                consumosEncontrados.add(consultaReserva);
                System.out.println("ITEM: " + consultaReserva.getItem().getDescricao() + ", Cód. " + consultaReserva.getItem().getCodigo() + " | VALOR: " + consultaReserva.getItem().getValor() + " | QUANTIDADE: " + consultaReserva.getQuantidadeSolicitada() + " | DATA: " + consultaReserva.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaReserva.getDataConsumo().format(dtf));
                }
            }
        } else if (opcao.equalsIgnoreCase("N")) {
            System.out.println("Consumo identificado!");
            for (Consumo consultaGeral : listaConsumos) {
                cadastrado = true;
                System.out.println("");
                System.out.println("CONSUMO " + (listaConsumos.indexOf(consultaGeral) + 1));
                System.out.println("ITEM: " + consultaGeral.getItem().getDescricao() + ", Cód. " + consultaGeral.getItem().getCodigo() + " | VALOR: " + consultaGeral.getItem().getValor() + " | QUANTIDADE: " + consultaGeral.getQuantidadeSolicitada() + " | DATA: " + consultaGeral.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consultaGeral.getDataConsumo().format(dtf));
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
            System.out.println("Consumo com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<Consumo> listarConsumos() {
        List<Consumo> listaConsumos = new ArrayList<>();
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
                    if (item != null && reserva != null) {
                        Consumo consumo = new Consumo(item, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                        listaConsumos.add(consumo);
                    }
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE CONSUMOS ***");
            for (Consumo consumo : listaConsumos) {
                System.out.println("");
                System.out.println("CONSUMO " + (listaConsumos.indexOf(consumo) + 1));
                System.out.println("ITEM: " + consumo.getItem().getDescricao() + ", Cód. " + consumo.getItem().getCodigo() + " | VALOR: " + consumo.getItem().getValor() + " | QUANTIDADE: " + consumo.getQuantidadeSolicitada() + "DATA: " + consumo.getDataConsumo().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + consumo.getDataConsumo().format(dtf));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo consumos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo consumos.txt!");
        }
        System.out.println("");
        return listaConsumos;
    }

    public static List<Consumo> leituraConsumos() {
        List<Consumo> listaConsumos = new ArrayList<>();
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
                    Consumo consumo = new Consumo(item, reserva, Integer.parseInt(dados[2]), LocalDateTime.parse(dados[3], dtf));
                    listaConsumos.add(consumo);
                }
            }            
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaConsumos;
    }

    public static Consumo identificarConsumo(int idReserva) {
        List<Consumo> listaConsumos = leituraConsumos();
        for (Consumo consumo : listaConsumos) {
            if (consumo.getReserva().getCodigo() == idReserva) {
                return consumo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Consumo{item='" + getItem().getDescricao() + "(" + getItem().getCodigo() + ")', reserva='" + getReserva().getCodigo() + "', quantidadeSolicitada='" + getQuantidadeSolicitada() + "', dataConsumo='" + getDataConsumo().format(dtf) + "'}";
    }
}

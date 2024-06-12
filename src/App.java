import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        menuPrincipal(scan);
        scan.close();
    }

    public static void menuPrincipal(Scanner scan) {
        String selecao = "";
        while (!selecao.equals("2")) {
            System.out.println("");
            System.out.println("*** MENU ***");
            System.out.println("1. Exibir opções");
            System.out.println("2. Sair");
            System.out.print("INSIRA A OPÇÃO DESEJADA: ");
            selecao = scan.nextLine();
            switch (selecao) {
                case "1":
                    menuOpcoes(scan);
                    break;
                case "2":
                    System.out.println("Encerrando.");
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        }
    }

    public static void menuOpcoes(Scanner scan) {
        System.out.println("");
        System.out.println("*** OPÇÕES ***");
        System.out.println("1. Hóspedes");
        System.out.println("2. Funcionários");
        System.out.println("3. Quartos");
        System.out.println("4. Reservas");
        System.out.println("5. Serviços");
        System.out.println("6. Categorias");
        System.out.println("7. Itens");
        System.out.println("8. Categorias (itens)");
        System.out.println("9. Consumos");
        System.out.println("10. Consumos (serviços)");
        System.out.println("11. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        switch (selecao) {
            case "1":
                menuHospede(scan);
                break;
            case "2":
                menuFuncionario(scan);
                break;
            case "3":
                menuQuarto(scan);
                break;
            case "4":
                menuReserva(scan);
                break;
            case "5":
                menuServico(scan);
                break;
            case "6":
                menuCategoria(scan);
                break;
            case "7":
                menuItem(scan);
                break;
            case "8":
                menuCategoriaItem(scan);
                break;
            case "9":
                menuConsumo(scan);
                break;
            case "10":
                menuConsumoServico(scan);
                break;
            case "11":
                break;
            default:
                System.out.println("Opção inválida, por favor tente novamente.");
        }
    }

    public static void menuHospede(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar hóspede");
        System.out.println("2. Editar hóspede");
        System.out.println("3. Consultar hóspede");
        System.out.println("4. Listar hóspedes");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Hospede.cadastrarHospede(scan);
                    break;
                case "2":
                    Hospede.editarHospede(scan);
                    break;
                case "3":
                    Hospede.consultarHospede(scan);
                    break;
                case "4":
                    Hospede.listarHospedes();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Hospede.identificarHospede("111.111.111-11").toString());
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuFuncionario(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar funcionário");
        System.out.println("2. Editar funcionário");
        System.out.println("3. Consultar funcionário");
        System.out.println("4. Listar funcionários");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Funcionario.cadastrarFuncionario(scan);
                    break;
                case "2":
                    Funcionario.editarFuncionario(scan);
                    break;
                case "3":
                    Funcionario.consultarFuncionario(scan);
                    break;
                case "4":
                    Funcionario.listarFuncionarios();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Funcionario.identificarFuncionario("999.999.999-99").toString());
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuQuarto(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar quarto");
        System.out.println("2. Editar quarto");
        System.out.println("3. Consultar quarto");
        System.out.println("4. Listar quartos");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Quarto.cadastrarQuarto(scan);
                    break;
                case "2":
                    Quarto.editarQuarto(scan);
                    break;
                case "3":
                    Quarto.consultarQuarto(scan);
                    break;
                case "4":
                    Quarto.listarQuartos();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Quarto.identificarQuarto(104));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
            if (selecao.equals("6")) {
                System.out.println("Encerrando.");
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuReserva(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar reserva");
        System.out.println("2. Editar reserva");
        System.out.println("3. Consultar reserva");
        System.out.println("4. Listar reservas");
        System.out.println("5. Pagar reserva");
        System.out.println("6. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Reserva.cadastrarReserva(scan);
                    break;
                case "2":
                    Reserva.editarReserva(scan);
                    break;
                case "3":
                    Reserva.consultarReserva(scan);
                    break;
                case "4":
                    Reserva.listarReservas();
                    break;
                case "5":
                    Reserva.pagarReserva(scan);
                    break;
                case "6":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Reserva.identificarReserva(1));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuServico(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar serviço");
        System.out.println("2. Editar serviço");
        System.out.println("3. Consultar serviço");
        System.out.println("4. Listar serviços");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Servico.cadastrarServico(scan);
                    break;
                case "2":
                    Servico.editarServico(scan);
                    break;
                case "3":
                    Servico.consultarServico(scan);
                    break;
                case "4":
                    Servico.listarServicos();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Servico.identificarServico(1));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuCategoria(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar categoria");
        System.out.println("2. Editar categoria");
        System.out.println("3. Consultar categoria");
        System.out.println("4. Listar categorias");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Categoria.cadastrarCategoria(scan);
                    break;
                case "2":
                    Categoria.editarCategoria(scan);
                    break;
                case "3":
                    Categoria.consultarCategoria(scan);
                    break;
                case "4":
                    Categoria.listarCategorias();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Categoria.identificarCategoria(1));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuItem(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar item");
        System.out.println("2. Editar item");
        System.out.println("3. Consultar item");
        System.out.println("4. Listar itens");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Item.cadastrarItem(scan);
                    break;
                case "2":
                    Item.editarItem(scan);
                    break;
                case "3":
                    Item.consultarItem(scan);
                    break;
                case "4":
                    Item.listarItens();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Item.identificarItem(10));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuCategoriaItem(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar categoria (item)");
        System.out.println("2. Editar categoria (item)");
        System.out.println("3. Consultar categoria (item)");
        System.out.println("4. Listar categorias (itens)");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    CategoriaItem.cadastrarCategoriaItem(scan);
                    break;
                case "2":
                    CategoriaItem.editarCategoriaItem(scan);
                    break;
                case "3":
                    CategoriaItem.consultarCategoriaItem(scan);
                    break;
                case "4":
                    CategoriaItem.listarCategoriasItens();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(CategoriaItem.identificarCategoriaItem(12));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuConsumo(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar consumo");
        System.out.println("2. Editar consumo");
        System.out.println("3. Consultar consumo");
        System.out.println("4. Listar consumos");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    Consumo.cadastrarConsumo(scan);
                    break;
                case "2":
                    Consumo.editarConsumo(scan);
                    break;
                case "3":
                    Consumo.consultarConsumo(scan);
                    break;
                case "4":
                    Consumo.listarConsumos();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(Consumo.identificarConsumo(1));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }

    public static void menuConsumoServico(Scanner scan) {
        System.out.println("");
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar consumo (serviço)");
        System.out.println("2. Editar consumo (serviço)");
        System.out.println("3. Consultar consumo (serviço)");
        System.out.println("4. Listar consumos (serviços)");
        System.out.println("5. Voltar ao menu anterior");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String selecao = scan.nextLine();
        try {
            switch (selecao) {
                case "1":
                    ConsumoServico.cadastrarConsumoServico(scan);
                    break;
                case "2":
                    ConsumoServico.editarConsumoServico(scan);
                    break;
                case "3":
                    ConsumoServico.consultarConsumoServico(scan);
                    break;
                case "4":
                    ConsumoServico.listarConsumosServicos();
                    break;
                case "5":
                    menuOpcoes(scan);
                    break;
                case "0": /* teste tostring */
                    System.out.println(ConsumoServico.identificarConsumoServico(1));
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
    }
}

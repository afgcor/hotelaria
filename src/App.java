import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Exibir opções");
        System.out.println("2. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            case "1": 
                menuOpcoes();
                break;
            case "2": 
                break; 
        }
        if (opcao == "2") {
            System.out.println("Encerrando.");
            System.out.println("");
        }
        scan.close();
    }

    public static void menuOpcoes() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** OPÇÕES ***");
        System.out.println("1. Hóspedes");
        System.out.println("2. Funcionários");
        System.out.println("3. Quartos");
        System.out.println("4. Reservas");
        System.out.println("5. Serviços");
        System.out.println("6. Categorias");
        System.out.println("7. Itens");
        System.out.println("8. Categorias de itens");
        System.out.println("9. Consumo");
        System.out.println("10. Consumo de serviços");
        System.out.println("11. Voltar ao menu anterior");
        System.out.println("12. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            case "1": 
                menuHospede();
                break;
            case "2":
                menuFuncionario();
                break;
            case "3": 
                menuQuarto();
                break;
            case "4":
                menuReserva();
                break;
            case "5":
                menuServico();
                break;
            case "6": 
                menuCategoria();
                break;
            case "7": 
                menuItem();
                break;
            case "8":
                menuCategoriaItem();
                break;
            case "9": 
                menuConsumo();
                break;
            case "10": 
                menuConsumoServico();
                break;
            case "11": 
                menuPrincipal();
                break;
            case "12": 
                break;
        }
        if (opcao == "12") {
            System.out.println("Encerrando.");
        }
        scan.close();
    }

    public static void menuHospede() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar hóspede");
        System.out.println("2. Editar hóspede");
        System.out.println("3. Consultar hóspede");
        System.out.println("4. Listar hóspedes");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Hospede.cadastrarHospede();
                    break;
                case "2":
                    Hospede.editarHospede();
                    break;
                case "3":
                    Hospede.consultarHospede();
                    break;
                case "4":
                    Hospede.listarHospedes();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
            if (opcao == "6") {
                System.out.println("Encerrando.");
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuFuncionario() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar funcionário");
        System.out.println("2. Editar funcionário");
        System.out.println("3. Consultar funcionário");
        System.out.println("4. Listar funcionários");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Funcionario.cadastrarFuncionario();
                    break;
                case "2":
                    Funcionario.editarFuncionario();
                    break;
                case "3":
                    Funcionario.consultarFuncionario();
                    break;
                case "4":
                    Funcionario.listarFuncionarios();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
            if (opcao == "6") {
                System.out.println("Encerrando.");
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuQuarto() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar quarto");
        System.out.println("2. Editar quarto");
        System.out.println("3. Consultar quarto");
        System.out.println("4. Listar quartos");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Quarto.cadastrarQuarto();
                    break;
                case "2":
                    Quarto.editarQuarto();
                    break;
                case "3":
                    Quarto.consultarQuarto();
                    break;
                case "4":
                    Quarto.listarQuartos();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuReserva() { /* AINDA NÃO ESTÁ 100% FUNCIONAL */
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar reserva");
        System.out.println("2. Editar reserva");
        System.out.println("3. Consultar reserva");
        System.out.println("4. Listar reservas");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            /* case 1: cadastrarReserva();
            case 2: editarReserva();
            case 3: consultarReserva();
            case 4: listarReservas(); */
            case "5": menuOpcoes();
            case "6": break; 
        }
        if (opcao == "6") {
            System.out.println("Encerrando.");
        }
        scan.close();
    }

    public static void menuServico() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar serviço");
        System.out.println("2. Editar serviço");
        System.out.println("3. Consultar serviço");
        System.out.println("4. Listar serviços");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Servico.cadastrarServico();
                    break;
                case "2":
                    Servico.editarServico();
                    break;
                case "3":
                    Servico.consultarServico();
                    break;
                case "4":
                    Servico.listarServicos();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
            if (opcao == "6") {
                System.out.println("Encerrando.");
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuCategoria() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar categoria");
        System.out.println("2. Editar categoria");
        System.out.println("3. Consultar categoria");
        System.out.println("4. Listar categorias");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Categoria.cadastrarCategoria();
                    break;
                case "2":
                    Categoria.editarCategoria();
                    break;
                case "3":
                    Categoria.consultarCategoria();
                    break;
                case "4":
                    Categoria.listarCategorias();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
            if (opcao == "6") {
                System.out.println("Encerrando.");
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuItem() {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar item");
        System.out.println("2. Editar item");
        System.out.println("3. Consultar item");
        System.out.println("4. Listar itens");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        try {
            switch (opcao) {
                case "1": 
                    Item.cadastrarItem();
                    break;
                case "2":
                    Item.editarItem();
                    break;
                case "3":
                    Item.consultarItem();
                    break;
                case "4":
                    Item.listarItens();
                    break;
                case "5":
                    menuOpcoes();
                    break;
                case "6": 
                    break; 
            }
            if (opcao == "6") {
                System.out.println("Encerrando.");
            }
        }
        catch (IOException e) {
            System.out.println("ERRO: Falha operacional!");
        }
        scan.close();
    }

    public static void menuCategoriaItem() { /* AINDA NÃO ESTÁ 100% FUNCIONAL */
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar categoria de item");
        System.out.println("2. Editar categoria de item");
        System.out.println("3. Consultar categoria de item");
        System.out.println("4. Listar categorias de itens");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            /* case 1: cadastrarCategoriaItem();
            case 2: editarCategoriaItem();
            case 3: consultarCategoriaItem();
            case 4: listarCategoriasItens(); */ /* CORRIGIR */
            case "5": menuOpcoes();
            case "6": break; 
        }
        if (opcao == "6") {
            System.out.println("Encerrando.");
        }
        scan.close();
    }

    public static void menuConsumo() { /* AINDA NÃO ESTÁ 100% FUNCIONAL */
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar consumo");
        System.out.println("2. Editar consumo");
        System.out.println("3. Consultar consumo");
        System.out.println("4. Listar consumos");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            /* case 1: cadastrarConsumo();
            case 2: editarConsumo();
            case 3: consultarConsumo();
            case 4: listarConsumos(); */
            case "5": menuOpcoes();
            case "6": break; 
        }
        if (opcao == "6") {
            System.out.println("Encerrando.");
        }
        scan.close();
    }

    public static void menuConsumoServico() { /* AINDA NÃO ESTÁ 100% FUNCIONAL */
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        System.out.println("*** MENU ***");
        System.out.println("1. Cadastrar consumo de serviço");
        System.out.println("2. Editar consumo de serviço");
        System.out.println("3. Consultar consumo de serviço");
        System.out.println("4. Listar consumos de serviços");
        System.out.println("5. Voltar ao menu anterior");
        System.out.println("6. Sair");
        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            /* case 1: cadastrarItem();
            case 2: editarItem();
            case 3: consultarItem();
            case 4: listarItens(); */
            case "5": menuOpcoes();
            case "6": break; 
        }
        if (opcao == "6") {
            System.out.println("Encerrando.");
        }
        scan.close();
    }
}
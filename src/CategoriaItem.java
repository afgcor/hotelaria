import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoriaItem {
    private Item item;
    private Categoria categoria;
    private int quantidade;

    /* CONSTRUTOR */
    public CategoriaItem(Item item, Categoria categoria, int quantidade) {
        this.item = item;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
    
    /* GETTERS & SETTERS */
    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarCategoriaItem() throws IOException {
        Scanner scan = new Scanner(System.in);    
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR CATEGORIA (ITEM) ***");

            System.out.print("Insira o código do item: ");
            String idItem = scan.nextLine();
            
            System.out.print("Insira o código da categoria: ");
            String idCategoria = scan.nextLine();

            System.out.print("Insira a quantidade: ");
            String quantidade = scan.nextLine();

            Item item = identificarItem(Integer.parseInt(idItem));
            Categoria categoria = identificarCategoria(Integer.parseInt(idCategoria));
            if (idItem.isEmpty() || idCategoria.isEmpty() || quantidade.isEmpty()) {
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/categoriasitens.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(item.getCodigo() + " ; " + categoria.getCodigo() + " ; " + Integer.parseInt(quantidade));
            bw.newLine();
            bw.close();

            System.out.println("CATEGORIA (ITEM) CADASTRADA!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados da categoria (item) ou encerre a operação.");
            System.out.print("Continuar cadastro (S) ou encerrar a operação (N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarCategoriaItem();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo categoriasitens.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarCategoriaItem() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<CategoriaItem> listaCategoriaItens = new ArrayList<>();
        File categoriasitens = new File("./arquivos/categoriasitens.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(categoriasitens))) {
            if (!categoriasitens.exists() || categoriasitens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = identificarItem(Integer.parseInt(dados[0]));
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (item != null && categoria != null) {
                        CategoriaItem categoriaItem = new CategoriaItem(item, categoria, Integer.parseInt(dados[2]));
                        listaCategoriaItens.add(categoriaItem);
                    }
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR CATEGORIA (ITEM) ***");
        System.out.print("Digite o código do item cuja categoria será editada: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (CategoriaItem categoriaItem : listaCategoriaItens) {
            if (categoriaItem.getItem().getCodigo() == codigo) {
                cadastrado = true;
                System.out.println("Categoria (item) identificada!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Item atual: " + categoriaItem.getItem().getDescricao() + ", Cód. " + categoriaItem.getItem().getCodigo());
                System.out.print("Novo item: ");
                String codigoItemAtualizadoS = scan.nextLine();
                int codigoItemAtualizado;
                if (codigoItemAtualizadoS.isEmpty()) {
                    codigoItemAtualizado = categoriaItem.getItem().getCodigo();
                } else {
                    codigoItemAtualizado = Integer.parseInt(codigoItemAtualizadoS);
                }
                System.out.println("Categoria atual: " + categoriaItem.getCategoria().getDescricao() + ", Cód. " + categoriaItem.getCategoria().getCodigo());
                System.out.print("Nova categoria: ");
                String codigoCategoriaAtualizadaS = scan.nextLine();
                int codigoCategoriaAtualizada;
                if (codigoCategoriaAtualizadaS.isEmpty()) {
                    codigoCategoriaAtualizada = categoriaItem.getCategoria().getCodigo();
                } else {
                    codigoCategoriaAtualizada = Integer.parseInt(codigoCategoriaAtualizadaS);
                }

                System.out.println("Quantidade atual: " + categoriaItem.getQuantidade());
                System.out.print("Nova quantidade: ");
                String quantidadeAtualizadaS = scan.nextLine();
                int quantidadeAtualizada;
                if (quantidadeAtualizadaS.isEmpty()) {
                    quantidadeAtualizada = categoriaItem.getQuantidade();
                } else {
                    quantidadeAtualizada = Integer.parseInt(quantidadeAtualizadaS);
                }

                String linhaAtualizada = codigoItemAtualizado + " ; " + codigoCategoriaAtualizada + " ; " + quantidadeAtualizada;
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(categoriaItem.getItem().getCodigo() + " ; " + categoriaItem.getCategoria().getCodigo() + " ; " + categoriaItem.getQuantidade());
            }
        }

        if (!cadastrado) {
            System.out.println("Categoria do item com código " + codigo + " não encontrada.");
            return false;
        }

        FileWriter fw = new FileWriter(categoriasitens);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categoriasitens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo categoriasitens.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static CategoriaItem consultarCategoriaItem() {
        List<CategoriaItem> listaCategoriaItens = new ArrayList<>();
        File categoriasitens = new File("./arquivos/categoriasitens.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(categoriasitens))) {
            if (!categoriasitens.exists() || categoriasitens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = identificarItem(Integer.parseInt(dados[0]));
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (item != null && categoria != null) {
                        CategoriaItem categoriaItem = new CategoriaItem(item, categoria, Integer.parseInt(dados[2]));
                        listaCategoriaItens.add(categoriaItem);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categoriasitens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo categoriasitens.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR CATEGORIA (ITEM) ***");
        System.out.print("Digite o código do item cuja categoria será consultada: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaCategoriaItens.size(); i++) {
            if (listaCategoriaItens.get(i).getItem().getCodigo() == codigo) {
                cadastrado = true;
                CategoriaItem categoriaItem = listaCategoriaItens.get(i);
                System.out.println("Categoria (item) identificada!");
                System.out.println("");
                System.out.println("CATEGORIA (ITEM) " + (i + 1));
                System.out.println("ITEM: " + categoriaItem.getItem().getDescricao() + ", Cód. " + categoriaItem.getItem().getCodigo() + " | CATEGORIA: " + categoriaItem.getCategoria().getDescricao() + ", Cód. " + categoriaItem.getCategoria().getCodigo() + " | QUANTIDADE: " + categoriaItem.getQuantidade());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Quarto com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<CategoriaItem> listarCategoriasItens() {
        List<CategoriaItem> listaCategoriaItens = new ArrayList<>();
        File categoriasitens = new File("./arquivos/categoriasitens.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(categoriasitens))) {

            if (!categoriasitens.exists() || categoriasitens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = identificarItem(Integer.parseInt(dados[0]));
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (item != null && categoria != null) {
                        CategoriaItem categoriaItem = new CategoriaItem(item, categoria, Integer.parseInt(dados[2]));
                        listaCategoriaItens.add(categoriaItem);
                    }
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE CATEGORIAS (ITENS) ***");
            for (CategoriaItem categoriaItem : listaCategoriaItens) {
                System.out.println("");
                System.out.println("CATEGORIA (ITEM) " + (listaCategoriaItens.indexOf(categoriaItem) + 1));
                System.out.println("ITEM: " + categoriaItem.getItem().getDescricao() + ", Cod. " + categoriaItem.getItem().getCodigo() + " | CATEGORIA: " + categoriaItem.getCategoria().getDescricao() + ", Cod. " + categoriaItem.getCategoria().getCodigo() + " | QUANTIDADE: " + categoriaItem.getQuantidade());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categoriasitens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo categoriasitens.txt!");
        }
        System.out.println("");
        return listaCategoriaItens;
    }

    public static List<Categoria> leituraCategorias() {
        List<Categoria> listaCategorias = new ArrayList<>();
        File categorias = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\categorias.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(categorias))) {

            if (!categorias.exists() || categorias.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = new Categoria(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaCategorias.add(categoria);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaCategorias;
    }

    public static Categoria identificarCategoria(int codigo) {
        List<Categoria> listaCategorias = leituraCategorias();
        for (Categoria categoria : listaCategorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    public static List<Item> leituraItens() {
        List<Item> listaItens = new ArrayList<>();
        File itens = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\itens.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(itens))) {

            if (!itens.exists() || itens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = new Item(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaItens.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaItens;
    }

    public static Item identificarItem(int codigo) {
        List<Item> listaItens = leituraItens();
        for (Item item : listaItens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }
}

package View;

import Business.Core;
import Data.User;
import Exceptions.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Menu {

    private final PrintStream out;
    private final Scanner in;
    private final Core core;

    public Menu(Core c) {
        out = System.out;
        in = new Scanner(System.in);
        core = c;
    }
    
    /**
     * Método que é chamado quando se inicia o programa, apresenta opções ao 
     * utilizador tais como: Fazer login, registar-se, ou sair.
     */
    public void start() {
        int opcao;

        out.println();
        while (true) {
            out.println("*** GeoCachingPOO ***");
            out.println();
            out.println("*** Menu Principal ***");
            out.println();
            out.println("1-Login");
            out.println("2-Registar");
            out.println("0-Sair\n");

            out.print("Opção: ");
            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                //opcao = -1;
                continue;
            }

            if (opcao > 2 || opcao < 0) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                //opcao = -1;
                continue;
            }

            switch (opcao) {
                case (0):
                    System.exit(0);
                    break;
                case (1):
                    login();
                    break;
                case (2):
                    register();
            }
        }
    }
    
    /**
     * Função que imprime vários '\n' para limpar o ecrã.
     */
    public void clearScreen() {
        int i = 0;
        while (i < 25) {
            out.println();
            i++;
        }
    }

    /**
     * Função que pede ao utilizador os dados do login, e faz o login
     * no sistema.
     */
    private void login() {
        String user;
        String pass;

        clearScreen();
        out.println("*** Login ***");
        out.println();
        out.print("\nEmail: ");
        user = in.nextLine();
        out.print("Password: ");
        pass = in.nextLine();

        try {
            core.login(user, pass);
            out.println("Autenticado com sucesso!");
            out.println(core.getInfo().toString()); //teste, apagar depois!
            in.nextLine();
            clearScreen();
            menu2();
        } catch (EmailInvalidoException ex) {
            out.println("Email introduzido inválido!");
            in.nextLine();
            clearScreen();
        } catch (CamposInvalidosException ex) {
            out.println("Campos introduzidos inválidos!");
            in.nextLine();
            clearScreen();
        } catch (UserNaoExisteException ex) {
            out.println("Não existe nenhum utilizador com esse Email!");
            in.nextLine();
            clearScreen();
        } catch (PasswordMissmatchException ex) {
            out.println("Email e Password não correspondem!");
            in.nextLine();
            clearScreen();
        }
    }

    /**
     * Menu principal do GeoCachingPOO, onde são apresentadas opções de conta 
     * de utilizador, das caches, e a possibilidade de guardar a informação 
     * em ficheiro.
     */
    private void menu2() {
        int opcao;

        out.println();
        while (true) {
            out.println("*** GeoCachingPOO ***");
            out.println();
            out.println("*** Menu Principal***");//falta: Gravar dados...
            out.println();
            out.println("1-Ver informações de conta");
            out.println("2-Alterar informações de conta");
            out.println("3-Ver ou alterar Caches");
            out.println("4-Amigos");
            out.println("5-Guardar");
            out.println("0-Sair");
            out.println();

            out.print("Opção: ");
            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                continue;
            }

            if (opcao > 5 || opcao < 0) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                continue;
            }

            switch (opcao) {
                case (0):
                    clearScreen();
                    return;
                case (1):
                    printInfo();
                    break;
                case (2):
                    menuConta();
                    break;
                case (3):
                    menuCaches();
                    break;
                case (4):
                    menuAmigos();
                    break;
                case (5):
                    break;
            }
        }
    }
    
    /**
     * Método que imprime as informações do utilizador com a sessão ativa.
     */
    private void printInfo() {
        clearScreen();
        User u = core.getInfo();
        out.println("*** Ver Informações de Conta ***");
        out.println();
        out.println("Email: " + u.getMail());
        out.println("Nome: " + u.getNome());
        out.println("Género: " + u.getGenero());
        out.println("Morada: " + u.getMorada());
        out.println("Data de Nascimento: " + u.getDn().toString());
        in.nextLine();
        clearScreen();
    }
    /**
     * Método que recolhe informação de registo e cria uma nova conta.
     */
    private void register() {
        String mail;
        String pass;
        String nome;
        String genero;
        String morada;
        int dia;
        int mes;
        int ano;

        clearScreen();
        out.println("*** Registar ***");
        out.println();
        out.println("Introduza os seus dados:");
        out.print("Email: ");
        mail = in.nextLine();
        try {
            core.checkMail(mail);
        } catch (EmailInvalidoException ex) {
            out.println("Email inválido!");
            in.nextLine();
            clearScreen();
            return;
        }
        out.print("Password: ");
        pass = in.nextLine();
        out.print("Nome: ");
        nome = in.nextLine();
        out.print("Género (M/F): ");
        genero = in.nextLine();
        out.print("Morada: ");
        morada = in.nextLine();
        try {
            out.println("Data de Nascimento: ");
            out.print("- Dia (1 até 31): ");
            dia = Integer.parseInt(in.nextLine());
            out.print("- Mês (1 até 12): ");
            mes = Integer.parseInt(in.nextLine());
            out.print("- Ano: ");
            ano = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            out.print("Data de Nascimento introduzida inválida!");
            in.nextLine();
            clearScreen();
            return;
        }

        try {
            core.registar(mail, pass, nome, genero, morada, dia, mes, ano);
            out.println("Registado com sucesso!");
            in.nextLine();
            clearScreen();
        } catch (CamposInvalidosException ex) {
            out.println("Um ou mais campos foram introduzidos de forma inválida!");
            in.nextLine();
            clearScreen();
        } catch (EmailJaExisteException ex) {
            out.println("Email introduzido já se encontra em utilização!");
            in.nextLine();
            clearScreen();
        } catch (GeneroInvalidoException ex) {
            out.println("Género inválido!");
            in.nextLine();
            clearScreen();
        } catch (DataInvalidaException ex) {
            out.println("Data de Nascimento inválida!");
            in.nextLine();
            clearScreen();
        } catch (EmailInvalidoException ex) {
            out.println("Email inválido!");
            in.nextLine();
            clearScreen();
        }

    }
    /**
     * Método que apresenta o menu que permite ao utilizador alterar os seus
     * dados pessoais.
     */
    private void menuConta() {
        int opcao = -1;

        out.println();
        while (true) {
            out.println("*** GeoCachingPOO ***");
            out.println();
            out.println("*** Alterar Informações de Conta ***");
            out.println();
            out.println("1-Nome");
            out.println("2-Morada");
            out.println("3-Genero");
            out.println("4-Data de Nascimento");
            out.println("5-Password");
            out.println("0-Voltar");
            out.println();
            out.print("Opção: ");

            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                opcao = -1;
                continue;
            }

            if (opcao > 5 || opcao < 0) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                opcao = -1;
                continue;
            }

            String x;
            int dia, mes, ano;
            switch (opcao) {
                case (0):
                    clearScreen();
                    return;
                case (1):
                    clearScreen();
                    out.println("*** Alterar Nome ***");
                    out.println();
                    out.print("Intruduza o novo nome: ");
                    x = in.nextLine();
                    try {
                        core.updateName(x);
                        out.print("Nome alterado com sucesso!");
                        in.nextLine();
                        clearScreen();
                    } catch (NomeInvalidoException ex) {
                        out.print("Nome introduzido inválido!");
                        in.nextLine();
                        clearScreen();
                    }
                    break;
                case (2):
                    clearScreen();
                    out.println("*** Alterar Morada ***");
                    out.println();
                    out.print("Intruduza a nova morada: ");
                    x = in.nextLine();
                    try {
                        core.updateMorada(x);
                        out.print("Morada alterada com sucesso!");
                        in.nextLine();
                        clearScreen();
                    } catch (MoradaInvalidaException ex) {
                        out.print("Morada introduzida inválida!");
                        in.nextLine();
                        clearScreen();
                    }
                    break;
                case (3):
                    out.println();
                    out.println("A alterar género...");
                    core.trocaGenero();
                    out.println("Género alterado com sucesso!");
                    in.nextLine();
                    clearScreen();
                    break;
                case (4):
                    clearScreen();
                    out.println("*** Alterar Data de Nascimento ***");
                    out.println();
                    out.println("Intruduza a nova data de nascimento: ");
                    try {
                        out.print("- Dia (1 até 31): ");
                        dia = Integer.parseInt(in.nextLine());
                        out.print("- Mês (1 até 12): ");
                        mes = Integer.parseInt(in.nextLine());
                        out.print("- Ano: ");
                        ano = Integer.parseInt(in.nextLine());
                    } catch (Exception e) {
                        out.print("Data de Nascimento introduzida inválida!");
                        in.nextLine();
                        clearScreen();
                        break;
                    }

                    try {
                        core.updateDN(dia, mes, ano);
                        out.print("Data de Nascimento alterada com sucesso!");
                        in.nextLine();
                        clearScreen();
                    } catch (CamposInvalidosException ex) {
                        out.print("Data de Nascimento introduzida inválida!");
                        in.nextLine();
                        clearScreen();
                    }
                    break;
                case (5):
                    clearScreen();
                    out.println("*** Alterar Password ***");
                    out.println();
                    out.print("Intruduza a password antiga: ");
                    x = in.nextLine();

                    if (!core.checkPass(x)) {
                        out.println("Password errada!");
                        in.nextLine();
                        clearScreen();
                        break;
                    }
                    out.print("Intruduza a nova password: ");
                    x = in.nextLine();
                    try {
                        core.updatePass(x);
                        out.print("Password alterada com sucesso!");
                        in.nextLine();
                        clearScreen();
                    } catch (PasswordInvalidaException e) {
                        out.print("Password introduzida inválida!");
                        in.nextLine();
                        clearScreen();
                    }
                    break;

            }
        }
    }
    
    /**
     * Método que apresenta o menu "Amigos" que permite ao utilizador adicionar
     * e remover amigos, aceitar ou recusar pedidos de amizade.
     */
    private void menuAmigos() {
        int opcao;

        out.println();
        while (true) {
            out.println("*** GeoCachingPOO ***");
            out.println();
            out.println("*** Amigos ***");
            out.println();
            out.println("1-Ver lista de amigos");
            out.println("2-Ver lista de pedidos de amizade");
            out.println("3-Adicionar amigo");
            out.println("4-Aceitar pedido de amizade");
            out.println("5-Ver atividades recentes de um amigo WIP"); //FALTA
            out.println("0-Voltar");
            out.println();

            out.print("Opção: ");
            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                continue;
            }

            if (opcao > 5 || opcao < 0) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                continue;
            }

            switch (opcao) {
                case (0):
                    clearScreen();
                    return;
                case (1):
                    printAmigos();
                    break;
                case (2):
                    printPedidos();
                    break;
                case (3):
                    pedirAmigo();
                    break;
                case (4):
                    aceitarAmigo();
                    break;
                case (5):
                    break;

            }
        }
    }

    /**
     * Método que imprime os amigos do utilizador com a sessão iniciada.
     */
    private void printAmigos() {
        clearScreen();
        User u = core.getInfo();
        out.println("*** Ver Amigos ***");
        out.println();
        HashMap<String, User> a = u.verAmigos();
        for (String e : a.keySet()) {
            u = a.get(e);
            out.println("Mail: " + e);
            out.println("Nome: " + u.getNome());
            out.println();
        }
        in.nextLine();
        clearScreen();
    }
    /**
     * Método que imprime os pedidos de amizade do utilizador com a sessão
     * iniciada.
     */
    private void printPedidos() {
        clearScreen();
        User u = core.getInfo();
        out.println("*** Ver Pedidos de Amizade ***");
        out.println();
        HashMap<String, User> a = u.verPedidosAmizade();
        for (String e : a.keySet()) {
            u = a.get(e);
            out.println("Mail: " + e);
            out.println("Nome: " + u.getNome());
            out.println();
        }
        in.nextLine();
        clearScreen();
    }

    /**
     * Método que permite ao utilizador pedir outros em amizade.
     */
    private void pedirAmigo() {
        clearScreen();
        User u = core.getInfo();
        out.println("*** Pedir em Amizade ***");
        out.println();
        out.println("Intruduza o email da pessoa a quem quer enviar o pedido:");
        String m = in.nextLine();
        try {
            core.pedeAmigo(m);
            out.println("Pedido enviado com sucesso!");
            in.nextLine();
            clearScreen();
        } catch (EmailInvalidoException ex) {
            out.println("Email introduzido inválido inválido!");
            in.nextLine();
            clearScreen();
        } catch (UserNaoExisteException ex) {
            out.println("Email não corresponde a nenhum utilizador!");
            in.nextLine();
            clearScreen();
        } catch (JaEAmigoException ex) {
            out.println("Já é amigo desse utilizador");
            in.nextLine();
            clearScreen();
        }

    }

    /**
     * Método que permite aceitar um pedido de amizade feito por outro
     * utilizador.
     */
    private void aceitarAmigo() {
        clearScreen();
        User u = core.getInfo();
        out.println("*** Aceitar pedido de amizade ***");
        out.println();
        out.println("Intruduza o email da pessoa que quer aceitar o pedido:");
        String m = in.nextLine();
        try {
            core.aceitaAmigo(m);
            out.println("Pedido enviado com sucesso!");
            in.nextLine();
            clearScreen();
        } catch (EmailInvalidoException ex) {
            out.println("Email introduzido inválido inválido!");
            in.nextLine();
            clearScreen();
        } catch (UserNaoExisteException ex) {
            out.println("Email não corresponde a nenhum utilizador!");
            in.nextLine();
            clearScreen();
        } catch (PedidoNaoExisteException ex) {
            out.println("Esse utilizador não lhe pediu amizade!");
            in.nextLine();
            clearScreen();
        } catch (JaEAmigoException ex) {
            out.println("Já é amigo desse utilizador");
            in.nextLine();
            clearScreen();
        }
    }

    /**
     * Método que cria o menu das caches.
     * @return O menu a apresentar.
     */
    private String mOperacoesCaches() {
        int i = 1;

        return "***Operações com Caches***\n"
                + i + "-Ver Caches\n"
                + (++i) + "-Ver Detalhes Cache\n"
                + (++i) + "-Criar Cache\n"
                + (++i) + "-Editar Cache\n"
                + (++i) + "-Remover Cache\n\n"
                + "0-Voltar";
    }

    /**
     * 
     * @param lista 
     */
    private void mVerLista(ArrayList<String> lista) {
        String m;
        boolean exit = false;
        int cont = 0, size = lista.size(), screensize = 20, currPage = 1, maxPages;

        out.println("*** Lista de Caches***\n");
        maxPages = size / screensize;
        if (((float) size % screensize) >= 0) {
            maxPages++;
        }
        for (String i : lista) {
            out.println(i);
            cont++;
            if (cont >= screensize) {
                out.println("Página: " + currPage + " de: " + maxPages);
                out.println("Mostrar proxima pagina?(S/N)");
                m = in.nextLine();
                while (!exit && cont != 0) {
                    switch (m) {
                        case ("S"):
                            cont = 0;
                            break;
                        case ("N"):
                            exit = true;
                            return;
                        default:
                            out.println("Opção invalida: Escreva S para sim ou N para não.");

                    }
                }
                clearScreen();
                currPage++;
            }

        }
        out.println("Página: " + currPage + " de: " + maxPages + "\nFIM");
        in.nextLine();

    }
    
    private String mCriarCache() {
        int i = 1;
        return "*** Criar Cache ***\n"
                + +i + "-Cache Tradicional\n"
                + (++i) + "-Micro-Cache\n"
                + (++i) + "-Cache Mistério\n"
                + (++i) + "-Multi Cache\n"
                + (++i) + "-Cache Evento\n";
    }

    // Criar Menus de criação para cada tipo de cache
    
    private void menuCaches() {
        int opcao;
        while (true) {
            clearScreen();
            out.println(this.mOperacoesCaches());

            out.print("Opção: ");
            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                continue;
            }

            switch (opcao) {
                case (0):
                    clearScreen();
                    return;
                case (1):
                    verCaches();
                    break;
                case (2):
                    detCache();
                    break;
                case (3):
                    criarCache();
                    break;
                case (4):
                    editCache();
                    break;
                case (5):
                    removeCache();
                    break;
                default:
                    out.println("Intruduza uma opção válida!");
                    in.nextLine();
                    clearScreen();
                    break;

            }
        }
    }
    
    /**
     * Método que apresenta a informação de todas as caches.
     */
    public void verCaches() {
        clearScreen();
        mVerLista(core.getListaCaches());
    }
    
    /**
     * Método que permite apagar uma cache do sistema.
     */
    private void detCache() {
        clearScreen();
        out.println("Introduzir referencia da cache:\n");
        String cache = in.nextLine();
        clearScreen();
        out.println(core.getDetalhesCache(cache));
        in.nextLine();
        mDetalhesCache(cache);

    }

    /**
     * 
     * @param cache 
     */
    private void mDetalhesCache(String cache) {
        if (core.getInfo().getMail().equals(cache))
        out.println("Operações:\n");
        out.println("1-EditarCache");

    }

    private void criarCache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void editCache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void removeCache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

package View;

import Business.Core;
import Exceptions.*;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Menu {

    PrintStream out;
    Scanner in;
    Core core;

    public Menu(Core c) {
        out = System.out;
        in = new Scanner(System.in);
        core = c;
    }

    public void start() {
        int opcao = -1;

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
                opcao = -1;
                continue;
            }

            if (opcao > 2 || opcao < 0) {
                out.println("Intruduza uma opção válida!");
                in.nextLine();
                clearScreen();
                opcao = -1;
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

    public void clearScreen() {
        int i = 0;
        while (i < 25) {
            out.println();
            i++;
        }
    }

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

    private void menu2() {
        int opcao;

        out.println();
        while (true) {
            out.println("*** GeoCachingPOO ***");
            out.println();
            out.println("*** Menu Principal***");
            out.println();
            out.println("1-Alterar informações de conta");
            out.println("2-Adicionar actividade");
            out.println("3-Amigos");
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

            if (opcao > 3 || opcao < 0) {
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
                    menuConta();
                    break;
                case (2):
                    break;
                case (3):
                    break;

            }
        }
    }

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
                    out.print("Intruduza a nova data de nascimento: ");
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

                    if (core.checkPass(x)) {
                        out.print("Password errada!");
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

}

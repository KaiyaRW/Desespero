package br.upe.UserInterface;

import br.upe.facade.Facade;
import jakarta.persistence.DiscriminatorValue;

import java.util.Scanner;

public class MainMenu {

    private final Facade facade;
    private final Scanner scanner = new Scanner(System.in);

    public MainMenu(Facade facade) {
        this.facade = facade;
    }

    public void displayStartMenu() {
        boolean running = true;

        while (running) {
            System.out.println("App Menu");
            System.out.println("1. Login");
            System.out.println("2. Criar novo Usuário");
            System.out.println("3. Sair");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> displayLoginMenu();
                    case 2 -> displayCreateUserMenu();
                    case 3 -> running = false;
                    default -> System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro no menu: " + e.getMessage());
                scanner.nextLine(); // Limpa a entrada inválida.
            }
        }

        // Fecha o Facade ao sair completamente do menu principal
        facade.close();
    }

    private void displayLoginMenu() {
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine().trim();
        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine().trim();

        try {
            facade.getAuthController().login(email, password);
            System.out.println("Login realizado com sucesso!");
            displayHomeMenu();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void displayHomeMenu() {
        String userType = facade.getStateController().getCurrentUser().getClass()
                .getAnnotation(DiscriminatorValue.class).value();

        if ("ADMIN".equals(userType)) {
            new AdminMenu(facade, scanner).displayAdminMenu();
        } else {
            new UserMenu(facade, scanner).displayUserMenu();
        }
        facade.getAuthController().logout();
    }

    private void displayCreateUserMenu() {
        System.out.println("Menu de Criação de Usuário");
        System.out.println("1. Criar Administrador");
        System.out.println("2. Criar Usuário Comum");
        System.out.print("Escolha uma opção: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o nome completo: ");
            String name = scanner.nextLine().trim();
            System.out.print("Digite o e-mail: ");
            String email = scanner.nextLine().trim();
            System.out.print("Digite a senha: ");
            String password = scanner.nextLine().trim();

            if (choice == 1) {
                facade.getAuthController().createNewAdmin(name, email, password);
                System.out.println("Administrador criado com sucesso.");
            } else if (choice == 2) {
                facade.getAuthController().createNewUser(name, email, password);
                System.out.println("Usuário criado com sucesso.");
            } else {
                System.out.println("Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
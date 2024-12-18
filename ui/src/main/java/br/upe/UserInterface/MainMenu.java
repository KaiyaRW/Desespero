package br.upe.UserInterface;

import br.upe.facade.FacadeController;
import br.upe.pojos.GreatEvent;
import br.upe.pojos.Subscription;
import br.upe.pojos.User;

import java.util.Collection;
import java.util.Scanner;

public class MainMenu {
    private final FacadeController facadeController = new FacadeController();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU INICIAL ---");
            System.out.println("1. Login");
            System.out.println("2. Criar usuário");
            System.out.println("3. Sair");

            int choice = getUserInputAsInt();
            switch (choice) {
                case 1 -> login();
                case 2 -> createUser();
                case 3 -> {
                    running = false;
                    System.out.println("Encerrando o sistema. Até logo!");
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String password = scanner.nextLine().trim();

        try {
            facadeController.login(email, password);
            User user = facadeController.getCurrentUser();
            System.out.println("Login realizado com sucesso!");

            if (user.isAdmin()) {
                displayAdminMenu();
            } else {
                displayUserMenu();
            }

            facadeController.logout(); // Logout automático ao sair do menu.
        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private void createUser() {
        System.out.println("\n--- Criar Usuário ---");
        System.out.println("1. Criar Administrador");
        System.out.println("2. Criar Usuário Comum");

        int choice = getUserInputAsInt();
        System.out.print("Nome completo: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String password = scanner.nextLine().trim();

        try {
            if (choice == 1) {
                facadeController.createAdminUser(name, "", "", email, password);
                System.out.println("Administrador criado com sucesso.");
            } else if (choice == 2) {
                facadeController.createCommonUser(name, "", "", email, password);
                System.out.println("Usuário comum criado com sucesso.");
            } else {
                System.out.println("Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    private void displayAdminMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Criar Evento");
            System.out.println("2. Listar Meus Eventos");
            System.out.println("3. Logout");

            int choice = getUserInputAsInt();
            switch (choice) {
                case 1 -> createEvent();
                case 2 -> listAdminEvents();
                case 3 -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void displayUserMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU USUÁRIO COMUM ---");
            System.out.println("1. Inscrever-se em Evento");
            System.out.println("2. Minhas Inscrições");
            System.out.println("3. Logout");

            int choice = getUserInputAsInt();
            switch (choice) {
                case 1 -> subscribeToEvent();
                case 2 -> listSubscriptions();
                case 3 -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void createEvent() {
        System.out.print("Nome do evento: ");
        String eventName = scanner.nextLine().trim();
        System.out.print("Diretor do evento: ");
        String director = scanner.nextLine().trim();

        try {
            facadeController.createEvent(eventName, director);
            System.out.println("Evento criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar evento: " + e.getMessage());
        }
    }

    private void listAdminEvents() {
        Collection<GreatEvent> events = facadeController.getAdminEvents();
        System.out.println("\n--- Meus Eventos ---");
        events.forEach(event -> System.out.printf("ID: %d - Nome: %s\n", event.getId(), event.getDescritor()));
    }

    private void subscribeToEvent() {
        Collection<GreatEvent> events = facadeController.getAvailableEvents();
        System.out.println("\n--- Eventos Disponíveis ---");

        if (events.isEmpty()) {
            System.out.println("Nenhum evento disponível no momento.");
            return;
        }

        events.forEach(event -> System.out.printf("ID: %d - Nome: %s\n", event.getId(), event.getDescritor()));

        System.out.print("Digite o ID do evento para se inscrever: ");
        Long eventId = scanner.nextLong();

        try {
            facadeController.subscribeToEvent(eventId);
            System.out.println("Inscrição realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inscrever-se: " + e.getMessage());
        }
    }

    private void listSubscriptions() {
        Collection<Subscription> subscriptions = facadeController.getUserSubscriptions();

        System.out.println("\n--- Minhas Inscrições ---");
        subscriptions.forEach(subscription -> System.out.printf("Evento ID: %d - Data: %s\n",
                subscription.getEventId(), subscription.getDate()));
    }

    private int getUserInputAsInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
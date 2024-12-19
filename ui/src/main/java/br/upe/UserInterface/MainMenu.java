package br.upe.UserInterface;

import br.upe.facade.Facade;
import br.upe.pojos.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayLoginMenu();
                case 2 -> displayCreateUserMenu();
                case 3 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
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
        if (facade.getStateController().getCurrentUser().isAdmin()) {
            displayAdminMenu();
        } else {
            displayUserMenu();
        }
        facade.getAuthController().logout();
    }

    private void displayCreateUserMenu() {
        System.out.println("Menu de Criação de Usuário");
        System.out.println("1. Criar Administrador");
        System.out.println("2. Criar Usuário Comum");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
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

    private void displayUserMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Menu do Usuário");
            System.out.println("1. Inscrever-se em Eventos");
            System.out.println("2. Visualizar Inscrições");
            System.out.println("3. Atualizar Senha");
            System.out.println("4. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> subscribeToEvent();
                case 2 -> viewUserSubscriptions();
                case 3 -> updatePassword();
                case 4 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void subscribeToEvent() {
        try {
            // Listando os eventos disponíveis
            facade.getGreatEventController().getAllEvents()
                    .forEach(event -> System.out.println("Evento: " + event.getTitulo() + " [ID: " + event.getId() + "]"));

            System.out.print("Digite o ID do evento desejado: ");
            Long eventId = scanner.nextLong();

            // Buscando o evento pelo ID
            Event selectedEvent = facade.getGreatEventController().getAllEvents()
                    .stream()
                    .filter(event -> event.getId().equals(eventId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado."));

            // Realizando a inscrição
            facade.getSubscriptionController().subscribeToEvent(
                    facade.getStateController().getCurrentUser(),
                    selectedEvent
            );

            System.out.println("Inscrição realizada com sucesso para o evento: " + selectedEvent.getTitulo());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void viewUserSubscriptions() {
        // Exemplo para visualização das inscrições:
        facade.getSubscriptionController().getAllEventSubscriptions()
                .forEach(subscription -> System.out.println("Evento inscrito: " + subscription.getEvent()));
    }

    private void updatePassword() {
        System.out.print("Digite a senha atual: ");
        String currentPassword = scanner.nextLine().trim();

        try {
            if (currentPassword.equals(facade.getStateController().getCurrentUser().getPassword())) {
                System.out.print("Digite a nova senha: ");
                String newPassword = scanner.nextLine();
                facade.getStateController().getCurrentUser().setPassword(newPassword);
                System.out.println("Senha atualizada com sucesso.");
            } else {
                System.out.println("Senha atual incorreta.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void displayAdminMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Menu Administrativo");
            System.out.println("1. Criar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Gerenciar Evento");
            System.out.println("4. Atualizar Senha");
            System.out.println("5. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createEvent();
                case 2 -> listEvents();
                case 3 -> manageEvent();
                case 4 -> updatePassword();
                case 5 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void createEvent() {
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição: ");
            String descritor = scanner.nextLine();
            System.out.print("Diretor: ");
            String diretor = scanner.nextLine();
            System.out.print("Data início (dd/MM/yyyy): ");
            Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
            System.out.print("Data fim (dd/MM/yyyy): ");
            Date dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

            facade.getGreatEventController().createEvent(titulo, descritor, diretor, dataInicio, dataFim);
            System.out.println("Evento criado com sucesso.");
        } catch (ParseException e) {
            System.out.println("Erro ao inserir datas.");
        }
    }

    private void listEvents() {
        facade.getGreatEventController().getAllEvents()
                .forEach(event -> System.out.println("Evento: " + event.getTitulo() + " [ID: " + event.getId() + "]"));
    }

    private void manageEvent() {
        System.out.print("Digite o ID do evento a ser gerenciado: ");
        Long eventId = scanner.nextLong();
        scanner.nextLine(); // Consumir linha restante

        System.out.println("Gerenciando evento [ID " + eventId + "]");
        System.out.println("Operações ainda não implementadas");
    }
}
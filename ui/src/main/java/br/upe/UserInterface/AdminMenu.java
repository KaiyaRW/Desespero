package br.upe.UserInterface;

import br.upe.facade.Facade;
import com.sun.tools.javac.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminMenu {

    private final Facade facade;
    private final Scanner scanner;

    public AdminMenu(Facade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void displayAdminMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Menu Administrativo");
            System.out.println("1. Criar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Gerenciar Evento");
            System.out.println("4. Atualizar Senha");
            System.out.println("5. Alterar Nome");
            System.out.println("6. Alterar Email");
            System.out.println("7. Remover Conta");
            System.out.println("8. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createEvent();
                case 2 -> listEvents();
                case 3 -> manageEvent();
                case 4 -> updatePasswordForAdmin();
                case 5 -> updateAdminName();
                case 6 -> updateAdminEmail();
                case 7 -> deleteAdminAccount();
                case 8 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void updateAdminName() {
        System.out.print("Digite o novo nome: ");
        String newName = scanner.nextLine().trim();

        try {
            Long adminId = facade.getStateController().getCurrentUser().getId();
            facade.getAdminUserController().updateAdminUserName(adminId, newName);
            System.out.println("Nome atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o nome: " + e.getMessage());
        }
    }

    private void updateAdminEmail() {
        System.out.print("Digite o novo email: ");
        String newEmail = scanner.nextLine().trim();

        try {
            Long adminId = facade.getStateController().getCurrentUser().getId();
            facade.getAdminUserController().updateAdminUserEmail(adminId, newEmail);
            System.out.println("Email atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o email: " + e.getMessage());
        }
    }

    private void updatePasswordForAdmin() {
        System.out.print("Digite sua nova senha: ");
        String newPassword = scanner.nextLine().trim();

        try {
            Long adminId = facade.getStateController().getCurrentUser().getId();
            facade.getAdminUserController().updateAdminUserPassword(adminId, newPassword);
            System.out.println("Senha atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a senha: " + e.getMessage());
        }
    }

    private void deleteAdminAccount() {
        System.out.print("Tem certeza que deseja remover sua conta? (s/n): ");
        String confirmation = scanner.nextLine().trim();

        if ("s".equalsIgnoreCase(confirmation)) {
            try {
                Long adminId = facade.getStateController().getCurrentUser().getId();
                facade.getAdminUserController().deleteAdminUser(adminId);
                System.out.println("Conta removida com sucesso.");
                facade.getAuthController().logout();
                new MainMenu(facade).displayStartMenu();
            } catch (Exception e) {
                System.out.println("Erro ao remover a conta: " + e.getMessage());
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

            facade.getEventController().createEvent(titulo, descritor, diretor, dataInicio, dataFim);
            System.out.println("Evento criado com sucesso.");
        } catch (ParseException e) {
            System.out.println("Erro ao inserir datas.");
        }
    }

    private void listEvents() {
        facade.getEventController().getAllEvents()
                .forEach(event -> System.out.println("Evento: " + event.getTitulo() + " [ID: " + event.getId() + "]"));
    }

    private void manageEvent() {
        try {
            // Obtém o ID do administrador atual
            Long adminId = facade.getStateController().getCurrentUser().getId();

            // Instancia a classe EventMenu para gerenciar eventos
            EventMenu eventMenu = new EventMenu(facade.getAdminUserController(), scanner, adminId);

            // Chama o menu de gerenciamento de eventos
            eventMenu.displayEventMenu();

        } catch (Exception e) {
            System.out.println("Erro ao acessar o gerenciamento de eventos: " + e.getMessage());
        }
    }
}
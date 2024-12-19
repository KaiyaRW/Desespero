package br.upe.UserInterface;

import br.upe.facade.Facade;
import br.upe.pojos.Event;
import java.util.Scanner;

public class UserMenu {

    private final Facade facade;
    private final Scanner scanner;

    public UserMenu(Facade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void displayUserMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Menu do Usuário");
            System.out.println("1. Inscrever-se em Eventos");
            System.out.println("2. Visualizar Inscrições");
            System.out.println("3. Atualizar Senha");
            System.out.println("4. Alterar Nome");
            System.out.println("5. Alterar Email");
            System.out.println("6. Remover Conta");
            System.out.println("7. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> subscribeToEvent();
                case 2 -> viewUserSubscriptions();
                case 3 -> updatePasswordForUser();
                case 4 -> updateUserName();
                case 5 -> updateUserEmail();
                case 6 -> deleteUserAccount();
                case 7 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void subscribeToEvent() {
        try {
            // Listando os eventos disponíveis
            facade.getEventController().getAllEvents()
                    .forEach(event -> System.out.println("Evento: " + event.getTitulo() + " [ID: " + event.getId() + "]"));

            System.out.print("Digite o ID do evento desejado: ");
            Long eventId = scanner.nextLong();

            // Buscando o evento pelo ID
            Event selectedEvent = facade.getEventController().getAllEvents()
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


    private void updateUserName() {
        System.out.print("Digite o novo nome: ");
        String newName = scanner.nextLine().trim();

        try {
            Long userId = facade.getStateController().getCurrentUser().getId();
            facade.getCommonUserController().updateUserName(userId, newName);
            System.out.println("Nome atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o nome: " + e.getMessage());
        }
    }

    private void updateUserEmail() {
        System.out.print("Digite o novo email: ");
        String newEmail = scanner.nextLine().trim();

        try {
            Long userId = facade.getStateController().getCurrentUser().getId();
            facade.getCommonUserController().updateUserEmail(userId, newEmail);
            System.out.println("Email atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o email: " + e.getMessage());
        }
    }

    private void deleteUserAccount() {
        System.out.print("Tem certeza que deseja remover sua conta? (s/n): ");
        String confirmation = scanner.nextLine().trim();

        if ("s".equalsIgnoreCase(confirmation)) {
            try {
                Long userId = facade.getStateController().getCurrentUser().getId();
                facade.getCommonUserController().deleteUser(userId);
                System.out.println("Conta removida com sucesso.");
                facade.getAuthController().logout();
                new MainMenu(facade).displayStartMenu();
            } catch (Exception e) {
                System.out.println("Erro ao remover conta: " + e.getMessage());
            }
        }
    }

    private void updatePasswordForUser() {
        System.out.print("Digite sua nova senha: ");
        String newPassword = scanner.nextLine().trim();

        try {
            Long userId = facade.getStateController().getCurrentUser().getId();
            facade.getCommonUserController().updateUserPassword(userId, newPassword);
            System.out.println("Senha atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a senha: " + e.getMessage());
        }
    }
}
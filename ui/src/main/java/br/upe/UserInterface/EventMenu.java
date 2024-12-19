package br.upe.UserInterface;

import br.upe.controllers.AdminUserController;
import br.upe.pojos.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EventMenu {

    private final AdminUserController adminUserController;
    private final Scanner scanner;
    private final Long adminUserId; // ID do administrador atualmente logado

    public EventMenu(AdminUserController adminUserController, Scanner scanner, Long adminUserId) {
        this.adminUserController = adminUserController;
        this.scanner = scanner;
        this.adminUserId = adminUserId;
    }

    public void displayEventMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu de Gerenciamento de Eventos");
            System.out.println("1. Listar Eventos do Administrador");
            System.out.println("2. Escolher Evento para Gerenciar");
            System.out.println("3. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha após o número

            switch (choice) {
                case 1 -> listAdminEvents();
                case 2 -> selectEventToManage();
                case 3 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void listAdminEvents() {
        try {
            // Utiliza a lista de eventos do administrador
            List<Event> events = adminUserController.listAdminEvents(adminUserId);
            if (events.isEmpty()) {
                System.out.println("Nenhum evento encontrado para este administrador.");
                return;
            }

            // Exibe os eventos usando os getters
            System.out.println("Eventos:");
            events.forEach(event -> System.out.println("ID: " + event.getId() + " - Título: " + event.getTitulo()));
        } catch (Exception e) {
            System.out.println("Erro ao listar eventos: " + e.getMessage());
        }
    }

    private void selectEventToManage() {
        System.out.print("Digite o ID do evento para gerenciar: ");
        Long eventId = scanner.nextLong();
        scanner.nextLine(); // Consumir a linha após entrada

        try {
            // Aqui validamos se o evento está associado ao administrador
            List<Event> events = adminUserController.listAdminEvents(adminUserId);
            Event selectedEvent = events.stream()
                    .filter(event -> event.getId().equals(eventId))
                    .findFirst()
                    .orElse(null);

            if (selectedEvent == null) {
                System.out.println("Evento não encontrado ou não pertence a este administrador!");
                return;
            }
            manageEvent(selectedEvent);
        } catch (Exception e) {
            System.out.println("Erro ao acessar evento: " + e.getMessage());
        }
    }

    private void manageEvent(Event event) {
        boolean running = true;

        while (running) {
            System.out.println("\nGerenciar Evento [ID: " + event.getId() + ", Título: " + event.getTitulo() + "]");
            System.out.println("1. Atualizar Título");
            System.out.println("2. Atualizar Descrição");
            System.out.println("3. Atualizar Diretor");
            System.out.println("4. Atualizar Data de Início");
            System.out.println("5. Atualizar Data de Término");
            System.out.println("6. Deletar Evento");
            System.out.println("7. Manejar Sessões");
            System.out.println("8. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir linha restante

            switch (choice) {
                case 1 -> updateTitle(event);
                case 2 -> updateDescription(event);
                case 3 -> updateDirector(event);
                case 4 -> updateStartDate(event);
                case 5 -> updateEndDate(event);
                case 6 -> deleteEvent(event);
                case 7 -> System.out.println("Manejo de sessões não implementado nesta versão.");
                case 8 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void updateTitle(Event event) {
        System.out.print("Novo título: ");
        String newTitle = scanner.nextLine();

        try {
            event.setTitulo(newTitle);
            System.out.println("Título atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar título: " + e.getMessage());
        }
    }

    private void updateDescription(Event event) {
        System.out.print("Nova descrição: ");
        String newDescription = scanner.nextLine();

        try {
            event.setDescritor(newDescription);
            System.out.println("Descrição atualizada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar descrição: " + e.getMessage());
        }
    }

    private void updateDirector(Event event) {
        System.out.print("Novo diretor: ");
        String newDirector = scanner.nextLine();

        try {
            event.setDirector(newDirector);
            System.out.println("Diretor atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar diretor: " + e.getMessage());
        }
    }

    private void updateStartDate(Event event) {
        System.out.print("Nova data de início (dd/MM/yyyy): ");
        String dateStr = scanner.nextLine();

        try {
            Date newStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            event.setStartDate(newStartDate);
            System.out.println("Data de início atualizada com sucesso.");
        } catch (ParseException e) {
            System.out.println("Formato de data inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar data de início: " + e.getMessage());
        }
    }

    private void updateEndDate(Event event) {
        System.out.print("Nova data de término (dd/MM/yyyy): ");
        String dateStr = scanner.nextLine();

        try {
            Date newEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            event.setEndDate(newEndDate);
            System.out.println("Data de término atualizada com sucesso.");
        } catch (ParseException e) {
            System.out.println("Formato de data inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar data de término: " + e.getMessage());
        }
    }

    private void deleteEvent(Event event) {
        try {
            System.out.println("Evento deletado com sucesso (simulação).");
        } catch (Exception e) {
            System.out.println("Erro ao deletar evento: " + e.getMessage());
        }
    }
}
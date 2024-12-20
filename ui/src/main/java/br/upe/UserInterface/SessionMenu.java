package br.upe.UserInterface;

import br.upe.facade.Facade;
import br.upe.pojos.Session;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SessionMenu {

    private final Facade facade;
    private final Scanner scanner;

    public SessionMenu(Facade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void displaySessionMenu(Long eventId) {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu de Sessões do Evento [ID: " + eventId + "]");
            System.out.println("1. Listar Sessões");
            System.out.println("2. Criar Nova Sessão");
            System.out.println("3. Atualizar Sessão");
            System.out.println("4. Deletar Sessão");
            System.out.println("5. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha restante

            switch (choice) {
                case 1 -> listSessions(eventId);
                case 2 -> createSession(eventId);
                case 3 -> updateSessionMenu(eventId);
                case 4 -> deleteSession(eventId);
                case 5 -> running = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    // Função ajustada para listar sessões de um evento específico
    private void listSessions(Long eventId) {
        try {
            List<Session> allSessions = facade.getSessionController().getAllSessions(); // Obtém todas as sessões
            List<Session> sessionsByEvent = allSessions.stream()
                    .filter(session -> session.getEvent().getId().equals(eventId)) // Filtra por evento
                    .collect(Collectors.toList());

            if (sessionsByEvent.isEmpty()) {
                System.out.println("Nenhuma sessão encontrada para este evento.");
                return;
            }

            sessionsByEvent.forEach(session -> System.out.println("ID: " + session.getId() + " - Descritor: " + session.getDescritor() +
                    " - Data de Início: " + session.getStartDate() +
                    " - Data de Término: " + session.getEndDate()));
        } catch (Exception e) {
            System.out.println("Erro ao listar sessões: " + e.getMessage());
        }
    }

    private void createSession(Long eventId) {
        try {
            System.out.print("Descritor da sessão: ");
            String descritor = scanner.nextLine();

            System.out.print("Data de início (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();

            System.out.print("Data de término (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();

            // Conversão de strings para objetos de data
            Date startDate = java.sql.Date.valueOf(startDateStr);
            Date endDate = java.sql.Date.valueOf(endDateStr);

            facade.getSessionController().createSession(descritor, startDate, endDate);
            System.out.println("Sessão criada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar sessão: " + e.getMessage());
        }
    }

    private void updateSessionMenu(Long eventId) {
        System.out.print("Digite o ID da sessão que deseja atualizar: ");
        Long sessionId = scanner.nextLong();
        scanner.nextLine(); // Consumir a linha restante

        boolean updating = true;
        while (updating) {
            System.out.println("\nMenu de Atualização da Sessão [ID: " + sessionId + "]");
            System.out.println("1. Atualizar Descritor");
            System.out.println("2. Atualizar Data de Início");
            System.out.println("3. Atualizar Data de Término");
            System.out.println("4. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha restante

            switch (choice) {
                case 1 -> updateSessionDescritor(sessionId);
                case 2 -> updateSessionStartDate(sessionId);
                case 3 -> updateSessionEndDate(sessionId);
                case 4 -> updating = false;
                default -> System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void updateSessionDescritor(Long sessionId) {
        try {
            System.out.print("Digite o novo descritor: ");
            String newDescritor = scanner.nextLine();
            facade.getSessionController().updateSessionDescritor(sessionId, newDescritor);
            System.out.println("Descritor atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar descritor: " + e.getMessage());
        }
    }

    private void updateSessionStartDate(Long sessionId) {
        try {
            System.out.print("Digite a nova data de início (yyyy-MM-dd): ");
            String newStartDateStr = scanner.nextLine();
            Date newStartDate = java.sql.Date.valueOf(newStartDateStr);

            facade.getSessionController().updateSessionStartDate(sessionId, newStartDate);
            System.out.println("Data de início atualizada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar data de início: " + e.getMessage());
        }
    }

    private void updateSessionEndDate(Long sessionId) {
        try {
            System.out.print("Digite a nova data de término (yyyy-MM-dd): ");
            String newEndDateStr = scanner.nextLine();
            Date newEndDate = java.sql.Date.valueOf(newEndDateStr);

            facade.getSessionController().updateSessionEndDate(sessionId, newEndDate);
            System.out.println("Data de término atualizada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar data de término: " + e.getMessage());
        }
    }

    private void deleteSession(Long eventId) {
        System.out.print("Digite o ID da sessão para deletar: ");
        Long sessionId = scanner.nextLong();

        try {
            facade.getSessionController().deleteSession(sessionId);
            System.out.println("Sessão deletada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao deletar sessão: " + e.getMessage());
        }
    }
}
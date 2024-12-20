package br.upe.UserInterface;

import br.upe.facade.Facade;

public class App {
    public static void main(String[] args) {
        // Instanciar o Facade (centraliza os controladores e DAOs)
        Facade facade = new Facade();

        // Instanciar o menu principal utilizando o Facade
        MainMenu mainMenu = new MainMenu(facade);

        try {
            // Iniciar a execução do menu
            mainMenu.displayStartMenu();
        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
        } finally {
            // Assegura fechamento do Facade (e recursos associados como EntityManager)
            facade.close();
        }
    }
}
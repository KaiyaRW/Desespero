package br.upe.UserInterface;

import br.upe.facade.Facade;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        // Inicializar o EntityManagerFactory usando a unidade de persistência definida no persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

        // Criar o EntityManager a partir do EntityManagerFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Instanciar a fachada com o EntityManager
        Facade facade = new Facade(entityManager);

        // Instanciar o menu principal utilizando a fachada
        MainMenu mainMenu = new MainMenu(facade);

        // Iniciar a execução do menu
        mainMenu.displayStartMenu();

        // Fechar EntityManager ao final
        entityManager.close();
        entityManagerFactory.close();
    }
}
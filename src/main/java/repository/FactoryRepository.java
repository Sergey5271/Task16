package repository;

public interface FactoryRepository {

    UserRepository getUserRepository();

    SubscriptionRepository getSubscriptionRepository();

    BookRepository getBookRepository();

    RepositoryOrder getRepositoryOrder();

}

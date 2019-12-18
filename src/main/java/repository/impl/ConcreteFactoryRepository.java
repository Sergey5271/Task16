package repository.impl;

import repository.BookRepository;
import repository.FactoryRepository;
import repository.RepositoryOrder;
import repository.SubscriptionRepository;
import repository.UserRepository;

public class ConcreteFactoryRepository implements FactoryRepository {

    private static final ConcreteFactoryRepository FACTORY = new ConcreteFactoryRepository();

    public ConcreteFactoryRepository() {
    }

    public static ConcreteFactoryRepository getInstance() {
        return FACTORY;
    }

    @Override
    public UserRepository getUserRepository() {
        return ConcreteUserRepository.getInstance();
    }

    @Override
    public SubscriptionRepository getSubscriptionRepository() {
        return ConcreteSubscriptionRepository.getInstance();
    }

    @Override
    public BookRepository getBookRepository() {
        return ConcreteBookRepository.getInstance();
    }

    @Override
    public RepositoryOrder getRepositoryOrder() {
        return RepositoryConcreteOrder.getInstance();
    }


}

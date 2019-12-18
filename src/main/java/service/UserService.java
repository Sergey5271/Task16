package service;

import entity.User;

public interface UserService {

    boolean add(User user);

    User getByEmail(String email);

}

package main.org.example.repository;

import main.org.example.entity.User;
import main.org.example.exception.UserNotFoundException;

public class UserRepository {
    private static final int MAX_SIZE = 100;
    private final User[] users = new User[MAX_SIZE];

    public void add(User user) {
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] == null) {
                users[i] = user;
                break;
            }
        }
    }

    public User[] findAll() {
        return users;
    }

    public void printAll() {
        for (int i = 0; i < MAX_SIZE; i++) {
            System.out.println(findAll()[i]);
        }
    }

    public User findByNameAndPassword(String userName, String password) throws UserNotFoundException {
        for (User user : this.users) {
            if (user.getName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Wrong name or password");
    }

    public User findByName(String userName) throws UserNotFoundException {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with name %s not found".formatted(userName));
    }
}


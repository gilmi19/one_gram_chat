package main.org.example.service;

import main.org.example.entity.Message;
import main.org.example.entity.User;
import main.org.example.exception.UserNotFoundException;
import main.org.example.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addMessage(Message message, String userName) throws UserNotFoundException {

        User user = userRepository.findByName(userName);
        Message[] userMessages = user.getMessages();
        for (int i = 0; i < userMessages.length; i++) {
            if (userMessages[i] == null) {
                userMessages[i] = message;
                break;
            }
        }
    }

    public void add(User user) {
        userRepository.add(user);
    }

    public User[] findAll() {
        return userRepository.findAll();
    }

    public void printAll() {
        userRepository.printAll();
    }

    public User findByNameAndPassword(String userName, String password) throws UserNotFoundException {
        return userRepository.findByNameAndPassword(userName, password);
    }

    public User findByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name);
    }
}


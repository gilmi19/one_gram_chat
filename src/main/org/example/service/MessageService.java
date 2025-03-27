package main.org.example.service;

import main.org.example.entity.Message;
import main.org.example.enums.MessageType;
import main.org.example.entity.User;
import main.org.example.exception.SessionException;
import main.org.example.exception.UserNotFoundException;
import main.org.example.repository.UserRepository;

public class MessageService {

    private  final UserService userService;

    public MessageService(UserService userService) {
        this.userService = userService;
    }

    public void readMessage(User currentUser) throws SessionException {
        if (currentUser == null) {
            throw new SessionException("Current user is not authorised");
        }
        Message[] messages = currentUser.getMessages();
        for (Message message : messages) {

            if (message == null) {
                break;
            }

            System.out.printf(
                    """
                            Письмо от: %s
                            Текст сообщения: %s
                            Письмо для: %s
                            Текст сообщения: %s
                            """,
                    message.getUserFrom().getName(),
                    message.getContent(),
                    message.getUserTo().getName(),
                    message.getContent());
        }
    }

    /**
     * Написать письмо: Вводится имя пользователя, вводится текст письма.
     * У текущего пользователя записывается в список сообщений как исходящий
     * У пользователя которому пишем, записывается в список сообщений как входящее
     * если такого пользователя нет, то возникает ошибка: такого пользователя нет
     * если текущего пользователя нет, то возникает ошибка: вы не авторизованы
     *
     * @param userName    - имя пользователя, который получит письмо
     * @param content     - содержание письма
     * @param currentUser - текущий пользователь, который вошел в систему
     */
    public void sendMessage(String userName, String content, User currentUser) throws SessionException, UserNotFoundException {

        if (currentUser == null) {
            throw new SessionException("You are not authorized");
        }
        User userTo = userService.findByName(userName);
        createMessages(content, currentUser, userTo);
    }

    private void createMessages(String content, User currentUser, User userTo) throws UserNotFoundException {
        createIncomeMessage(content, currentUser, userTo);
        createOutcomeMessage(content, currentUser, userTo);
    }

    private void createIncomeMessage(String content, User currentUser, User userTo) throws UserNotFoundException {
        Message message = new Message(content, MessageType.INCOME, currentUser, userTo);
        userService.addMessage(message, currentUser.getName());
    }

    private void createOutcomeMessage(String content, User currentUser, User userTo) throws UserNotFoundException {
        Message message = new Message(content, MessageType.OUTCOME, currentUser, userTo);
        userService.addMessage(message, userTo.getName());
    }
}

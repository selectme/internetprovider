package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.service.UserService;

import java.util.HashMap;
import java.util.Map;

// singleton
public class TMPCommandStorage {
    private Map<String, ServletCommand> commandsByNames = new HashMap<>(); // TODO: (MAYBE, OPTIONAL) not direct new HashMap


    public ServletCommand getCommandByName(String name) {
        // TODO: throw exception if command not found
        return commandsByNames.get(name);
    }

    private void loadCommands() {
        addCommandByName(new LoginServletCommand(new UserService()));
    }

    private void addCommandByName(ServletCommand command) {
        commandsByNames.put(command.getName(), command);
    }
}

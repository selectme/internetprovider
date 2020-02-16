package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.model.Admin;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.UserService;

import java.util.HashMap;
import java.util.Map;

// singleton
public class CommandStorage {

    private Map<String, ServletCommand> commandsByNames = new HashMap<>(); // TODO: (MAYBE, OPTIONAL) not direct new HashMap

    private static CommandStorage commandStorage;
    private AdminService adminService;
    private ClientService clientService;
    private UserService userService;

    private CommandStorage() {
        adminService = new AdminService();
        clientService = new ClientService();
        userService = new UserService();
        loadCommands();
    }

    public static CommandStorage getInstance() {
        if (commandStorage == null) {
            commandStorage = new CommandStorage();
        }
        return commandStorage;
    }

    public ServletCommand getCommandByName(String name) {
        // TODO: throw exception if command not found
        return commandsByNames.get(name);
    }

    private void loadCommands() {
        addCommandByName(new LoginServletCommand(userService));
        addCommandByName(new AddTariffServletCommand(adminService));
        addCommandByName(new AddUserServletCommand(adminService));
        addCommandByName(new ChangeTariffPlanServletCommand(adminService, clientService));
        addCommandByName(new DeleteTariffPlanServletCommand(adminService));
        addCommandByName(new EditClientByClientServletCommand(clientService));
        addCommandByName(new EditTariffPlanServletCommand(adminService));
        addCommandByName(new EditUserByAdminServletCommand(adminService));
        addCommandByName(new MakeCreditPaymentServletCommand(clientService));
        addCommandByName(new DeleteUserServletCommand(adminService));
    }

    private void addCommandByName(ServletCommand command) {
        commandsByNames.put(command.getName(), command);
    }
}

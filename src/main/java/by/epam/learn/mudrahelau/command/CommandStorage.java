package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.dao.impl.AdminDaoDbImpl;
import by.epam.learn.mudrahelau.dao.impl.ClientDaoDbImpl;
import by.epam.learn.mudrahelau.dao.impl.UserDaoDbImpl;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.UserService;
import by.epam.learn.mudrahelau.service.impl.AdminServiceDbImpl;
import by.epam.learn.mudrahelau.service.impl.ClientServiceDbImpl;
import by.epam.learn.mudrahelau.service.impl.UserServiceDbImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandStorage is intended to store {@link ServletCommand} used to execute the logic associated with receiving and sending HTTP
 * requests and responses.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class CommandStorage {
    /**
     * {@link Map} that contains names(keys) of {@link ServletCommand} and instances(values) of {@link ServletCommand}.
     */
    private Map<String, ServletCommand> commandsByNames = new HashMap<>();
    /**
     * static instance.
     */
    private static CommandStorage commandStorage;
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * @see ClientService
     */
    private ClientService clientService;
    /**
     * @see UserService
     */
    private UserService userService;


    private CommandStorage() {
        adminService = new AdminServiceDbImpl(new AdminDaoDbImpl());
        clientService = new ClientServiceDbImpl(new ClientDaoDbImpl());
        userService = new UserServiceDbImpl(new UserDaoDbImpl());
        loadCommands();
    }

    public static CommandStorage getInstance() {
        if (commandStorage == null) {
            commandStorage = new CommandStorage();
        }
        return commandStorage;
    }

    /**
     * Gets {@link ServletCommand} by name.
     *
     * @param name name of {@link ServletCommand}.
     * @return {@link ServletCommand} associated by name from the map.
     */
    public ServletCommand getCommandByName(String name) {
        ServletCommand servletCommand = commandsByNames.get(name);
        if (servletCommand == null) {
            servletCommand = new NotExistsServletCommand();
        }
        return servletCommand;
    }

    /**
     * Load {@link ServletCommand} in the moment of instantiation CommandStorage.
     */
    private void loadCommands() {
        addCommandByName(new LoginServletCommand(userService));
        addCommandByName(new AddTariffServletCommand(adminService));
        addCommandByName(new AddUserServletCommand(adminService));
        addCommandByName(new ChangeTariffPlanServletCommand(adminService));
        addCommandByName(new DeleteTariffPlanServletCommand(adminService));
        addCommandByName(new EditClientByClientServletCommand(clientService));
        addCommandByName(new EditTariffPlanServletCommand(adminService));
        addCommandByName(new EditUserByAdminServletCommand(adminService));
        addCommandByName(new MakeCreditPaymentServletCommand(clientService));
        addCommandByName(new DeleteUserServletCommand(adminService));
        addCommandByName(new LogoutServletCommand());
        addCommandByName(new ShowAddUserPageServletCommand());
        addCommandByName(new ShowAdminPanelServletCommand());
        addCommandByName(new ShowChangeTariffServletCommand(adminService));
        addCommandByName(new ShowClientAccountServletCommand(adminService));
        addCommandByName(new ShowClientPaymentsPageServletCommand(clientService));
        addCommandByName(new ShowEditClientByClientPageServletCommand(adminService));
        addCommandByName(new ShowEditTariffPageServletCommand(adminService));
        addCommandByName(new ShowEditUserPageServletCommand(adminService));
        addCommandByName(new ShowLoginPageServletCommand());
        addCommandByName(new ShowPaymentPageServletCommand(adminService));
        addCommandByName(new ShowTariffsServletCommand(adminService));
        addCommandByName(new ShowUsersListServletCommand(adminService));
        addCommandByName(new ShowAddTariffPageServletCommand());

    }

    private void addCommandByName(ServletCommand command) {
        commandsByNames.put(command.getName(), command);
    }
}

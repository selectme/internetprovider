package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class CommandStorage {

    private Map<String, ServletCommand> commandsByNames = new HashMap<>();

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
        ServletCommand servletCommand = commandsByNames.get(name);
        if (servletCommand == null) {
            servletCommand = new NotExistsServletCommand();
        }
        return servletCommand;
    }

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

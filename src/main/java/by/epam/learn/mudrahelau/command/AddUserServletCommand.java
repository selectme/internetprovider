package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.validator.AdminValidator;
import by.epam.learn.mudrahelau.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of {@link ServletCommand}.
 * AddUserServletCommand is intended to add {@link User} into a database.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class AddUserServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "add_user";
    /**
     * Localized error message.
     */
    private static final String ERROR_MESSAGE = "label.incorrect.user.data.error";

    public AddUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (AdminValidator.checkUserIsAdmin(user)) {
                String login = request.getParameter(ParameterConstant.LOGIN);
                String password = request.getParameter(ParameterConstant.PASSWORD);
                String name = request.getParameter(ParameterConstant.NAME);
                String surname = request.getParameter(ParameterConstant.SURNAME);
                Role role = Role.valueOf(request.getParameter(ParameterConstant.ROLE));
                User newUser = new User();
                newUser.setLogin(login);
                newUser.setPassword(password);
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setRole(role);
                if (UserValidator.validateCreatingUser(newUser)) {
                    adminService.addUser(newUser);
                    response.sendRedirect(RedirectConstants.SHOW_USERS_REDIRECT);
                } else {
                    request.setAttribute(ParameterConstant.ERROR_ATTRIBUTE, ERROR_MESSAGE);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.ADD_USER_PAGE);
                    requestDispatcher.forward(request, response);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendRedirect(RedirectConstants.SHOW_LOGIN_PAGE_REDIRECT);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}

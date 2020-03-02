package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.constant.RedirectConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * Implementation of {@link ServletCommand}.
 * DeleteUserServletCommand is intended to delete {@link User} from a database.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class DeleteUserServletCommand implements ServletCommand {
    /**
     * @see AdminService
     */
    private AdminService adminService;
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "delete_user";

    public DeleteUserServletCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                long userId = Long.parseLong(request.getParameter(ParameterConstant.USER_ID));
                adminService.deleteUserById(userId);
                response.sendRedirect(RedirectConstants.SHOW_USERS_REDIRECT);
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}

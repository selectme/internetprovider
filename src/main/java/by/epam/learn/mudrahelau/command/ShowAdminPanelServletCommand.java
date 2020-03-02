package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.learn.mudrahelau.validator.AdminValidator.checkUserIsAdmin;

/**
 * Implementation of {@link ServletCommand}.
 * ShowAdminPanelServletCommand is intended for forwarding {@link User} to the administration panel page.
 * {@link User} must have {@link by.epam.learn.mudrahelau.role.Role} ADMIN.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class ShowAdminPanelServletCommand implements ServletCommand {
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "show_administration_panel";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destinationPage = PagesConstant.MAIN_PAGE;
        User user = (User) request.getSession().getAttribute(ParameterConstant.USER);
        if (user != null) {
            if (checkUserIsAdmin(user)) {
                destinationPage = PagesConstant.ADMIN_PANEL_PAGE;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destinationPage);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}

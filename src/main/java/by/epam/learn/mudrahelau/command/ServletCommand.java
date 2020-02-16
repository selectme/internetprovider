package by.epam.learn.mudrahelau.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);

    // @NonNull
    String getName();
}

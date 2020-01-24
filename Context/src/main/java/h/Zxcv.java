package h;

import ru.itis.Controller;
import ru.itis.Mapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Mapping("/first")
public class Zxcv implements Controller {

    public final Integer nkl = 5;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext context) {

    }
}

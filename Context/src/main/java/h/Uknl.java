package h;

import ru.itis.Component;

public class Uknl implements Component {
    private final String FIND_BY_ID = "SELECT * FROM pg_user WHERE id = ?";
}

package transaction;

import java.sql.Connection;

public interface Operation {
    void action(Connection connection);
}

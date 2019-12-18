package transaction;
import java.sql.Connection;

public interface TransactionManager {

    void doInTransaction(Connection connection, Operation operation);
}

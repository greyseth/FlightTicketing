import com.smk.dao.UserDao;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTests {
    @Test
    void logUser() {
        UserDao userDao = new UserDao();
        System.out.println(userDao.login("Grey", "5572"));
    }
}

import controller.Controller;
import controller.LoginMenuController;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class Test {

    private Controller c = new Controller() ;
    private LoginMenuController l = new LoginMenuController(c) ;

    public Test() throws IOException {
    }

    @org.junit.jupiter.api.Test
    public void bankIsClosed() throws IOException {
        String output = l.login("alimali5" , "12345" , false ) ;
        Assertions.assertEquals( output , "Username and password didn’t match!" ) ;
    }
}

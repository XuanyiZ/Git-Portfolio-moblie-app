package utility;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/3/26.
 */
public class HttpHandlerTest {
    @Test
    public void makeServiceCall() throws Exception {
        HttpHandler sh = new HttpHandler();
        String jsonStr = sh.makeServiceCall("https://api.github.com/users/XuanyiZ/followers","GET");
        assert (jsonStr != null);

        HttpHandler sh2 = new HttpHandler();
        String jsonStr2 = sh.makeServiceCall("https://api.github.com/users/XuanyiZ/following","PUT");
        assert (jsonStr2 != null);
    }

}
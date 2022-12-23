import com.kangkang.mapper.OrdersMapper;
import com.kangkang.pojo.Orders;
import com.kangkang.util.CheckCodeUtil;
import com.kangkang.util.GetCtx;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestAll {
    @Test
    public void testMapper(){
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        OrdersMapper bean = ctx.getBean(OrdersMapper.class);
        Orders orders = new Orders();
        orders.setCustomerId(1);
        int i = bean.selectCount(0, 5, orders);
        System.out.println(i);
    }
    @Test
    public void compareVAboutMapAndArray(){
        System.out.println("\u624b\u94fe");
    }

    @Test
    public void testCheckCode() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\javacode\\DatebaseDesign\\DatabaseDesign\\src\\main\\webapp\\img\\a.jpeg");
        String s = CheckCodeUtil.outputVerifyImage(100, 50, fileOutputStream, 4);
        System.out.println(s);
    }
}

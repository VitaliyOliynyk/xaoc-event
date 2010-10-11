package eu.vitaliy.xaocevent;



import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:/xaoc-event-context.xml")
public class AppTest 
{
    //@Resource IBean1 bean1;
    static ApplicationContext ctx;
    static  {
        ctx = new ClassPathXmlApplicationContext("xaoc-event-context.xml");
    }

    public AppTest( )
    {
    }

    @Test
    @Ignore
    public void test()
    {
        //given
        IBean1 bean1 = (IBean1) ctx.getBean("bean1");
        //when
        bean1.m1();
        
        //then
        Assert.assertTrue(bean1.isM2OK());

    }
}

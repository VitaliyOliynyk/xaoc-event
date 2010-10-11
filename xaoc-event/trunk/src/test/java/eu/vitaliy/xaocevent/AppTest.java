package eu.vitaliy.xaocevent;



import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xaoc-event-context.xml")
public class AppTest 
{
    @Resource IBean1 bean1;
    
    public AppTest( )
    {
    }

    @Test
    @Ignore
    public void test()
    {
        //given
        
        //when
        bean1.m1();
        
        //then
        Assert.assertTrue(bean1.isM2OK());

    }
}

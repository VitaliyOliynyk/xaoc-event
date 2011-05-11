package eu.vitaliy.xaocevent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.fest.assertions.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xaoc-event-context-test.xml")
public class AppTest 
{
    @Autowired
    IBean bean1;

    @Autowired
    IBean bean2;


    @Test
    public void testBean1()
    {
        test(bean1);
    }

    @Test
    public void testBean2()
    {
        test(bean2);
    }


    private void test(IBean bean)
    {
       //given
        String testArgument = "testArgument";
        //when
        bean.eventSender(testArgument);

        //then
        assertThat(bean1.isReceiveNamedEventWithoutArgument()).isTrue();
        assertThat(bean1.getSenderArgument()).isEqualTo(testArgument);
    }


}

package eu.vitaliy.xaocevent;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 15.08.11
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xaoc-event-context-test.xml")
@Ignore
public class SpringEventQueueTest {

    @Autowired
    private IBeanWithSpringEventSuport bean3;

    @Test
    public void test()
    {
        //given
        String testArgument = "testArgument";

        //when
        bean3.eventSenderViaSpringEventQueue(testArgument);
        boolean result = bean3.isReceiveNamedEventWithoutArgument();
        //then
        assertThat(result).isTrue();
        //assertThat(bean3.getSenderArgument()).isEqualTo(testArgument);


    }

}

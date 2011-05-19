package eu.vitaliy.xaocevent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 19.05.11
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xaoc-event-context-test.xml")
public class ThreadSafeTest {
    @Autowired
    IBeanThreadSafe beanThreadSafe;

    @Test
    public void testThreadSafe()
    {
        int cntPerThreads = 100;
        int threadsCnt = 4;
        TestThread[] threads = new TestThread[threadsCnt];
        for(int i=0;i<threadsCnt;i++)
        {
            threads[i] = createTestThread(cntPerThreads);
        }

        for(int i=0;i<threadsCnt;i++)
        {
            threads[i].start();
        }
    }





    private TestThread createTestThread(int cnt) {
        return new  TestThread(cnt);
    }


    class TestThread extends Thread{

        int cnt;

        TestThread(int cnt)
        {
            this.cnt = cnt;
        }
        @Override
        public void run() {
            long threadId = Thread.currentThread().getId();
            for(int i=0;i<cnt; i++)
            {
                beanThreadSafe.eventSender(threadId);
            }
        }
    }
}

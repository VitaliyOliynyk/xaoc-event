package eu.vitaliy.xaocevent;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;
/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 29.06.11
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class MetaDataContextTest {
    private MetaDataContext metaDataContext;

    @Before
    public void before()
    {
         metaDataContext = new MetaDataContext("aaa", String.class);
    }

    @Test
    public void testEquals()
    {
         //given
         MetaDataContext obj = null;

         assertThat(metaDataContext.equals(obj)).isFalse();
         assertThat(metaDataContext.equals("ccc")).isFalse();

         int x = 2;
         assertThat(metaDataContext.equals(new MetaDataContext(x, Integer.class))).isFalse();

         assertThat(metaDataContext.equals(new MetaDataContext("aaa", String.class))).isTrue();
         assertThat(metaDataContext.equals(new MetaDataContext("aab", String.class))).isFalse();
    }
}

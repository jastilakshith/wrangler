package io.cdap.wrangler.utils;

import io.cdap.wrangler.api.parser.TimeDuration;
import org.junit.Assert;
import org.junit.Test;

public class TimeDurationTest {

  @Test
  public void testTimeDuration() {
    TimeDuration td = new TimeDuration("1s");
    Assert.assertEquals(1000L, td.getMilliseconds());

    td = new TimeDuration("2m");
    Assert.assertEquals(120000L, td.getMilliseconds());

    td = new TimeDuration("1.5h");
    Assert.assertEquals(5400000L, td.getMilliseconds());
  }
}


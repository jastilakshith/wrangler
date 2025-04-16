package io.cdap.wrangler.utils;

import io.cdap.wrangler.api.parser.ByteSize;
import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {

  @Test
  public void testByteSize() {
    ByteSize bs = new ByteSize("1MB");
    Assert.assertEquals(1048576L, bs.getBytes());

    bs = new ByteSize("2KB");
    Assert.assertEquals(2048L, bs.getBytes());

    bs = new ByteSize("1GB");
    Assert.assertEquals(1073741824L, bs.getBytes());
  }
}

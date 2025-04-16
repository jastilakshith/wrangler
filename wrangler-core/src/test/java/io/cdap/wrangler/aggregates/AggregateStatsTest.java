package io.cdap.wrangler.steps.aggregates;

import io.cdap.wrangler.api.RecipePipeline;
import io.cdap.wrangler.test.TestingRig;
import io.cdap.wrangler.api.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStatsTest {

  @Test
  public void testAggregateStats() throws Exception {
    List<Row> rows = Arrays.asList(
      new Row("data_size", "1MB").add("response_time", "1s"),
      new Row("data_size", "512KB").add("response_time", "500ms")
    );

    String[] recipe = new String[]{
      "aggregate-stats :data_size :response_time total_size_mb total_time_sec"
    };

    List<Row> result = TestingRig.execute(recipe, rows);

    Row output = result.get(0);
    Assert.assertEquals(1572864L, output.getValue("total_size_mb")); // 1MB + 512KB
    Assert.assertEquals(1500L, output.getValue("total_time_sec"));   // 1s + 0.5s
  }
}

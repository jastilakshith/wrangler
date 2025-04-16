package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class TimeDuration implements Token {
  private final String value;
  private final long milliseconds;

  public TimeDuration(String value) {
    this.value = value;
    this.milliseconds = parseMilliseconds(value);
  }

  private long parseMilliseconds(String value) {
    value = value.trim().toLowerCase();
    double number = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
    if (value.endsWith("ms")) return (long) number;
    if (value.endsWith("s")) return (long)(number * 1000);
    if (value.endsWith("m")) return (long)(number * 60 * 1000);
    if (value.endsWith("h")) return (long)(number * 60 * 60 * 1000);
    if (value.endsWith("d")) return (long)(number * 24 * 60 * 60 * 1000); // Optional: days support
    return (long) number;
  }

  public long getMilliseconds() {
    return milliseconds;
  }

  @Override
  public Object value() {
    return milliseconds;
  }

  @Override
  public TokenType type() {
    return TokenType.TIME_DURATION; // Will add next
  }

  @Override
  public JsonElement toJson() {
    return new JsonPrimitive(value);
  }
}

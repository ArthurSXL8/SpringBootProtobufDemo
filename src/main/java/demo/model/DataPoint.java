package demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
public class DataPoint {
  private final String metric;
  private final long timestamp;
  private final long value;
  private final Map<String, String> tags;
}


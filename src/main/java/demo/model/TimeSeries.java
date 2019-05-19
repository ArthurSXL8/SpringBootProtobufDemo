package demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@Builder
public class TimeSeries {
  private final String metric;
  private final Map<String, String> tags;
  private final List<String> aggregateTags;
  private final Map<String, Double> dps;
}


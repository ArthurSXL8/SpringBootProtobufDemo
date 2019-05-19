package demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Query {
  @Builder.Default
  private String aggregator = "sum";
  private final String metric;
  @Builder.Default
  private final String downsample = "1s-avg";
  @Builder.Default
  private boolean explicitTags = true;
  private List<TagFilter> filters;
}


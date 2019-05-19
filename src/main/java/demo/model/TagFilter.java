package demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TagFilter {
  @Builder.Default
  private String type = "literal_or";
  private final String tagk;
  private final String filter;
  @Builder.Default
  private boolean groupBy = false;
}


package demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class OpenTsdbRequestBody {
  private final long start;
  private final long end;
  private final List<Query> queries;
}


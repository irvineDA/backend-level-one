package com.da_learn.backend.level.one.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;

public class PostgresFunctionsContributor implements FunctionContributor {

  public static final String FTS_MATCH = "fts_match";
  public static final String TS_RANK = "ts_rank";

  @Override
  public void contributeFunctions(FunctionContributions functionContributions) {
    functionContributions
        .getFunctionRegistry()
        .registerPattern(
            FTS_MATCH,
            "?1 @@ to_tsquery('simple', ?2)",
            functionContributions
                .getTypeConfiguration()
                .getBasicTypeRegistry()
                .resolve(StandardBasicTypes.BOOLEAN));

    functionContributions
        .getFunctionRegistry()
        .registerPattern(
            TS_RANK,
            "ts_rank(?1, to_tsquery('simple', ?2))",
            functionContributions
                .getTypeConfiguration()
                .getBasicTypeRegistry()
                .resolve(StandardBasicTypes.FLOAT));
  }
}

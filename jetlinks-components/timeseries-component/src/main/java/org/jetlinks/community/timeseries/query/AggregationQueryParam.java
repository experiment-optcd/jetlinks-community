package org.jetlinks.community.timeseries.query;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.core.dsl.Query;
import org.hswebframework.ezorm.core.param.QueryParam;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@Setter
public class AggregationQueryParam {

    //集合列
    private List<AggregationColumn> aggColumns = new ArrayList<>();

    //按时间分组
    private TimeGroup groupByTime;

    //按字段分组
    private List<Group> groupBy = new ArrayList<>();

    //最大返回记录条数
    private int limit;

    private long startWithTime = 0;

    private long endWithTime = System.currentTimeMillis();

    //条件过滤
    private QueryParam queryParam = new QueryParam();

    public static AggregationQueryParam of() {
        return new AggregationQueryParam();
    }

    public AggregationQueryParam from(long time) {
        this.startWithTime = time;
        return this;
    }

    public AggregationQueryParam from(Date time) {
        if (null != time) {
            return from(time.getTime());
        }
        return this;
    }

    public AggregationQueryParam to(long time) {
        this.endWithTime = time;
        return this;
    }

    public AggregationQueryParam to(Date time) {
        if (null != time) {
            return to(time.getTime());
        }
        return this;
    }

    public AggregationQueryParam agg(String property, String alias, Aggregation agg) {
        aggColumns.add(new AggregationColumn(property, alias, agg));
        return this;
    }


    public AggregationQueryParam agg(String property, Aggregation agg) {
        return agg(property, property, agg);
    }

    public AggregationQueryParam sum(String property, String alias) {
        return agg(property, alias, Aggregation.SUM);
    }

    public AggregationQueryParam sum(String property) {
        return agg(property, Aggregation.SUM);
    }

    public AggregationQueryParam avg(String property, String alias) {
        return agg(property, alias, Aggregation.AVG);
    }

    public AggregationQueryParam avg(String property) {
        return agg(property, Aggregation.AVG);
    }

    public AggregationQueryParam count(String property, String alias) {
        return agg(property, alias, Aggregation.COUNT);
    }

    public AggregationQueryParam count(String property) {
        return agg(property, Aggregation.COUNT);
    }

    public AggregationQueryParam max(String property, String alias) {
        return agg(property, alias, Aggregation.MAX);
    }

    public AggregationQueryParam max(String property) {
        return agg(property, Aggregation.MAX);
    }

    public AggregationQueryParam min(String property, String alias) {
        return agg(property, alias, Aggregation.MIN);
    }

    public AggregationQueryParam min(String property) {
        return agg(property, Aggregation.MIN);
    }

    public AggregationQueryParam groupBy(Duration time, String alias, String format) {
        return groupBy(new TimeGroup(time, alias, format));
    }

    public AggregationQueryParam groupBy(Duration time, String format) {
        return groupBy(time, "time", format);
    }

    public AggregationQueryParam groupBy(TimeGroup timeGroup) {
        this.groupByTime = timeGroup;
        return this;
    }

    public AggregationQueryParam groupBy(Group group) {
        groupBy.add(group);
        return this;
    }

    public AggregationQueryParam groupBy(String property, String alias) {
        return groupBy(new Group(property, alias));
    }

    public <T> T execute(Function<AggregationQueryParam, T> executor) {
        return executor.apply(this);
    }

    public AggregationQueryParam filter(Consumer<Query<?, QueryParam>> consumer) {
        consumer.accept(Query.of(queryParam));
        return this;
    }

    public AggregationQueryParam limit(int limit) {
        this.limit = limit;
        return this;
    }

}

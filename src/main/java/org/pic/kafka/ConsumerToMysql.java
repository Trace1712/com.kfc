package org.pic.kafka;

import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.shaded.curator.org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.pic.db.entity.PixivPicture;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wucy03
 * @since 2022/3/18
 */
// 批量插入数据库
public class ConsumerToMysql {

    @Value("")
    private String topic;

    private final Gson gson = new Gson();

    // flink 配置
    private final Properties props = new Properties();

    // 初始化环境
    private final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


    public ConsumerToMysql() {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
    }

    public void startFlink() throws Exception {

        SingleOutputStreamOperator<PixivPicture> student = env.addSource(new FlinkKafkaConsumer011<>(
                        topic,
                        new SimpleStringSchema(),
                        props))
                .setParallelism(1)
                .map(string -> gson.fromJson(string, PixivPicture.class));

        student.timeWindowAll(Time.minutes(1L)).apply(
                (AllWindowFunction<PixivPicture, List<PixivPicture>, TimeWindow>) (window, values, out) -> {
                ArrayList<PixivPicture> students = Lists.newArrayList(values);
                if (students.size() > 0) {
                    System.out.println("1 分钟内收集到 student 的数据条数是：" + students.size());
                    out.collect(students);
                }
        }).addSink(new SinkToMySql());

        env.execute("flink learning connectors kafka");
    }
}

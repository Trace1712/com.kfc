package org.pic.kafka;

import com.avro.bean.User;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.pic.ser.SimpleAvroSchemaFlink;

import java.util.Properties;

public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        //获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setAutoWatermarkInterval(100L);
        //kafka配置
        String topic = "sample";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "localhost:9092");//多个的话可以指定
        prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("value.deserializer", "org.pic.ser.SimpleAvroSchemaFlink");
        prop.setProperty("auto.offset.reset","latest");
        prop.setProperty("group.id","consumer1");

        FlinkKafkaConsumer<User> myConsumer = new FlinkKafkaConsumer<User>(topic, new SimpleAvroSchemaFlink(), prop);
        //获取数据
        DataStreamSource<User> text = env.addSource(myConsumer);
        DataStream<String> keyValue = text.map(new MapFunction<User, String>() {
            @Override
            public String map(User user) throws Exception {
                System.out.println(user.getValue());
                return null;
            }
        });
        
//        keyValue.timeWindowAll(Time.minutes(1)).apply(new AllWindowFunction<String, PixivPicture, TimeWindow>() {
//            @Override
//            public void apply(TimeWindow timeWindow, Iterable<String> iterable, Collector<PixivPicture> collector) throws Exception {
//
//            }
//        }).addSink(new SinkToMySql());
        //打印
        text.print().setParallelism(1);
        //执行
        //env.execute("StreamingFormCollection");
        env.execute();
    }
}

package org.pic.ser;

import com.avro.bean.User;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public class SimpleAvroSchemaFlink implements DeserializationSchema<User> {
    @Override
    public User deserialize(byte[] bytes) throws IOException {
        // 用来保存结果数据
        User userBehavior = new User();
        // 创建输入流用来读取二进制文件
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
        // 创建输入序列化执行器
        SpecificDatumReader<User> stockSpecificDatumReader = new SpecificDatumReader<User>(userBehavior.getSchema());
        //创建二进制解码器
        BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(arrayInputStream, null);
        try {
            // 数据读取
            userBehavior=stockSpecificDatumReader.read(null, binaryDecoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 结果返回
        return userBehavior;
    }

    @Override
    public boolean isEndOfStream(User user) {
        return false;
    }

    @Override
    public TypeInformation<User> getProducedType() {
        return TypeInformation.of(new TypeHint<User>(){});
    }
}

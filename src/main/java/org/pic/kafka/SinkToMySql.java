package org.pic.kafka;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.pic.db.entity.PixivPicture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author wucy03
 * @since 2022/3/18
 */
public class SinkToMySql extends RichSinkFunction<List<PixivPicture>> {

    PreparedStatement ps;
    BasicDataSource dataSource;
    private Connection connection;

    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        dataSource = new BasicDataSource();
        connection = getConnection(dataSource);
    }

    public void invoke(List<PixivPicture> pictureList, Context context) throws Exception {
        //遍历数据集合
        for (PixivPicture pixivPicture : pictureList) {
            ps.setLong(1, pixivPicture.getUserId());
            ps.setString(2, pixivPicture.getNickName());
            ps.setString(3, pixivPicture.getPictureName());
            ps.setLong(4, pixivPicture.getPictureId());
            ps.setString(5, pixivPicture.getPictureUrl());
            ps.setInt(6, pixivPicture.getR18());
            ps.setLong(7, pixivPicture.getLikeNum());
            ps.setLong(8, pixivPicture.getStoreNum());
            ps.setString(9, pixivPicture.getTag());
            ps.setObject(10, pixivPicture.getSumbitTime());
            ps.setString(11, pixivPicture.getException());
        }
        int[] count = ps.executeBatch();//批量后执行
        System.out.println("成功了插入了" + count.length + "行数据");
    }


    private static Connection getConnection(BasicDataSource dataSource) {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //注意，替换成自己本地的 mysql 数据库地址和用户名、密码
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //设置连接池的一些参数
        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(50);
        dataSource.setMinIdle(2);

        Connection con = null;
        try {
            con = dataSource.getConnection();
            System.out.println("创建连接池：" + con);
        } catch (Exception e) {
            System.out.println("-----------mysql get connection has exception , msg = " + e.getMessage());
        }
        return con;
    }
}

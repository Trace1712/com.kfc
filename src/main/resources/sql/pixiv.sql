-- auto-generated definition
create table pixiv_picture
(
    ID           bigint auto_increment
        primary key,
    USER_ID      varchar(512)                              not null comment '作者id',
    NICKNAME     varchar(512)                              not null comment '作者名称',
    KIND         tinyint      default 0                    not null comment '作品类型 2-文字图片,1-套图，0-单张图片',
    PICTURE_NAME varchar(512)                              not null comment '图片名称',
    PICTURE_ID   varchar(1024)                             not null comment '图片ID',
    PICTURE_URL  varchar(1024)                             not null comment '图片地址',
    R_18         tinyint      default 0                    not null comment '十八禁 1-是，0-否',
    LIKE_NUM     int          default 0                    not null comment '喜欢数',
    STORE_NUM    int          default 0                    not null comment '收藏数',
    TAG          varchar(1024)                             not null comment '图片标签',
    SUBMIT_TIME  timestamp(3) default CURRENT_TIMESTAMP(3) not null comment '提交时间',
    EXCEPTION    longtext                                  null comment '报错信息',
    constraint USER_ID
    unique (USER_ID)
)
comment 'Pixiv图片 | wucy | 2022-01-24';

-- auto-generated definition
create table every_rank
(
    ID         bigint auto_increment
        primary key,
    USER_ID    bigint                                 not null comment 'Pixiv作者id',
    RANK_NUM   int                                    not null comment '排行名次',
    RANK_TIME  timestamp    default CURRENT_TIMESTAMP not null comment '时间',
    PICTURE_ID varchar(1024)                          not null comment '图片ID',
    constraint PICTURE_ID
        unique (PICTURE_ID)
)
comment '每日排行榜 | wucy | 2022-03-04';

create table task_monitor
(
    ID         bigint auto_increment
        primary key,
    START_URL   varchar(1024)                          not null comment '开始地址',
    START_TIME  timestamp    default CURRENT_TIMESTAMP not null comment '任务开始时间',
    END_TIME    timestamp    default CURRENT_TIMESTAMP not null comment '任务结束时间',
    PICTURE_NUM int          default 0                 not null comment '任务获取图片数',
    CONDITION   int          default 0                 not null comment '执行情况 0-未执行 1-执行中 2-执行成功 3-执行失败',
    EXCEPTION   varchar(1024)                          comment '报错信息'
)
    comment '任务监控表 | wucy | 2022-03-22';


drop table if exists tb_video CASCADE;

create table tb_video(
    videoId bigint generated by default as identity,
    title varchar(255) NOT NULL,
    descricao varchar(255) NOT NULL,
    uri varchar(255) NOT NULL,
    primary key(videoId)
);




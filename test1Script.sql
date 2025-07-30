create table t_user(
id varchar(16) not null primary key,
pwd varchar(32) not null,
name varchar(128) not null,
level char(1) not null,
"desc" varchar(256) null,
reg_date timestamp not null);


select * from t_user;

delete from t_user where id = 'HKT';

delete  from t_user;

SELECT pg_encoding_to_char(encoding) FROM pg_database WHERE datname = 'test';


SELECT
  level,
  length(level) AS character_length,   -- 문자 수
  octet_length(level) AS byte_length   -- 실제 바이트 수
FROM t_user;

--USING => 컬럼 타입을 변경할 때, 기존 데이터의 형식을 새 타입에 맞게 변환하는 방식
ALTER TABLE t_user
ALTER COLUMN reg_date TYPE timestamp without time zone USING reg_date::timestamp without time zone;
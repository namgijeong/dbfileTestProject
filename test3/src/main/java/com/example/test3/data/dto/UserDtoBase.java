package com.example.test3.data.dto;

import java.sql.Timestamp;

public interface UserDtoBase {
    String getId();

    String getName();

    String getPwd();

    String getLevel();

    String getDesc();

    Timestamp getRegDate();
}

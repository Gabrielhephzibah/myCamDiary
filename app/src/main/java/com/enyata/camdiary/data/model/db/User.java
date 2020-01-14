

package com.enyata.camdiary.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sanni Michael on 10/12/2019
 */
@Entity(tableName = "users")
public class User {
    @ColumnInfo(name = "created_at")
    public String createdAt;

    @PrimaryKey
    public Long id;

    public String name;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}

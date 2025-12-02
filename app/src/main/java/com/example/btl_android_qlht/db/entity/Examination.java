package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Examination",
        foreignKeys = {
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subjectId", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semesterId", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Examination {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "subject_id")
    public String subjectId;

    @ColumnInfo(name = "semester_id")
    public String semesterId;
    @ColumnInfo(name = "time")
    public String time;
    @ColumnInfo(name = "room")
    public String room;
    @ColumnInfo(name = "status")
    public int status; // e.g. 0 = scheduled, 1 = done

    public Examination() {}
}

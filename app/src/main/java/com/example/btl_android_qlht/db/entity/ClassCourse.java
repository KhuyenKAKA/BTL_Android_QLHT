package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Class",
        foreignKeys = {
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semesterId", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subjectId", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Teacher.class, parentColumns = "id", childColumns = "teacherId", onDelete = ForeignKey.NO_ACTION)
        }
)
public class ClassCourse {
    @PrimaryKey
    public String id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "semester_id")
    public String semesterId;
    @ColumnInfo(name = "subject_id")
    public String subjectId;
    @ColumnInfo(name = "teacher_id")
    public String teacherId;
    @ColumnInfo(name = "room")
    public String room;
    @ColumnInfo(name = "start_time")
    public String startTime; // e.g. "2025-12-01 08:00"

    @ColumnInfo(name = "end_time")
    public String endTime;
    @ColumnInfo(name = "quantity")
    public int quantity;

    public ClassCourse() {}
}
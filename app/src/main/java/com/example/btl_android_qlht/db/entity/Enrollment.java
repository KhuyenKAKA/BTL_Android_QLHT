package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Enrollment",
        foreignKeys = {
                @ForeignKey(entity = ClassCourse.class, parentColumns = "id", childColumns = "classId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "studentId", onDelete = ForeignKey.CASCADE)
        }
)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "class_id")
    public String classId;
    @ColumnInfo(name = "student_id")
    public String studentId;

    @ColumnInfo(name = "regular_score")
    public float regularScore;
    @ColumnInfo(name = "mid_score")
    public float midScore;
    @ColumnInfo(name = "final_score")
    public float finalScore;
    @ColumnInfo(name = "avg_score")
    public float avgScore;
    @ColumnInfo(name = "grade")
    public String grade;

    public Enrollment() {}
}

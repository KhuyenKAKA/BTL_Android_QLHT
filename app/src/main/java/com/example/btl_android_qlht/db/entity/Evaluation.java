package com.example.btl_android_qlht.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Evaluation",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "studentId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semesterId", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Evaluation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name =  "student_id")
    public String studentId;
    @ColumnInfo(name =  "semester_id")
    public String semesterId;
    @ColumnInfo(name =  "teacher_score")
    public int teacherScore;
    @ColumnInfo(name =  "bonus_score")
    public int bonusScore;
    @ColumnInfo(name =  "rank")
    public String rank;

    public Evaluation() {}
}

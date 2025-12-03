package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Evaluation",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "student_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semester_id", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Evaluation {
    @NonNull
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

    public Evaluation(int id, String studentId, String semesterId, int teacherScore, int bonusScore, String rank) {
        this.id = id;
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.teacherScore = teacherScore;
        this.bonusScore = bonusScore;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public int getTeacherScore() {
        return teacherScore;
    }

    public void setTeacherScore(int teacherScore) {
        this.teacherScore = teacherScore;
    }

    public int getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(int bonusScore) {
        this.bonusScore = bonusScore;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    @Ignore
    public Evaluation() {
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", semesterId='" + semesterId + '\'' +
                ", teacherScore=" + teacherScore +
                ", bonusScore=" + bonusScore +
                ", rank='" + rank + '\'' +
                '}';
    }
}

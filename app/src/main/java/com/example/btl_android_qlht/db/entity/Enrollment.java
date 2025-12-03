package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Enrollment",
        foreignKeys = {
                @ForeignKey(entity = ClassCourse.class, parentColumns = "id", childColumns = "class_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "student_id", onDelete = ForeignKey.CASCADE)
        }
)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
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


    public Enrollment(int id, String classId, String studentId, float regularScore, float midScore, float finalScore, float avgScore, String grade) {
        this.id = id;
        this.classId = classId;
        this.studentId = studentId;
        this.regularScore = regularScore;
        this.midScore = midScore;
        this.finalScore = finalScore;
        this.avgScore = avgScore;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public float getRegularScore() {
        return regularScore;
    }

    public void setRegularScore(float regularScore) {
        this.regularScore = regularScore;
    }

    public float getMidScore() {
        return midScore;
    }

    public void setMidScore(float midScore) {
        this.midScore = midScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Ignore
    public Enrollment() {
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", regularScore=" + regularScore +
                ", midScore=" + midScore +
                ", finalScore=" + finalScore +
                ", avgScore=" + avgScore +
                ", grade='" + grade + '\'' +
                '}';
    }
}

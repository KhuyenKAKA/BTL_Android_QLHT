package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Class",
        foreignKeys = {
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semester_id", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subject_id", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Teacher.class, parentColumns = "id", childColumns = "teacher_id", onDelete = ForeignKey.NO_ACTION)
        }
)
public class ClassCourse {
    @NonNull
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


    public ClassCourse(String id, String name, String semesterId, String subjectId, String teacherId, String room, String startTime, String endTime, int quantity) {
        this.id = id;
        this.name = name;
        this.semesterId = semesterId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Ignore
    public ClassCourse() {
    }

    @Override
    public String toString() {
        return "ClassCourse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", semesterId='" + semesterId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", room='" + room + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
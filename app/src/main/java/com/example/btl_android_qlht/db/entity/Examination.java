package com.example.btl_android_qlht.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Examination",
        foreignKeys = {
                @ForeignKey(entity = Subject.class, parentColumns = "id", childColumns = "subject_id", onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Semester.class, parentColumns = "id", childColumns = "semester_id", onDelete = ForeignKey.NO_ACTION)
        }
)
public class Examination {
    @NonNull
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


    public Examination(int id, String subjectId, String semesterId, String time, String room, int status) {
        this.id = id;
        this.subjectId = subjectId;
        this.semesterId = semesterId;
        this.time = time;
        this.room = room;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Ignore

    public Examination() {
    }


    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", subjectId='" + subjectId + '\'' +
                ", semesterId='" + semesterId + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'' +
                ", status=" + status +
                '}';
    }
}

package com.example.a33.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(tableName = "word_table")
@NoArgsConstructor
@Data
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    public Word(String word){this.mWord = word;}
    public String getWord(){return this.mWord;}
}

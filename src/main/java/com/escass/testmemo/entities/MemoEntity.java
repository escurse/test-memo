package com.escass.testmemo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"index"})
public class MemoEntity {
    private int index;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}

package com.escass.testmemo.vos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoVo {
    public final int countPerPage = 5;
    public final int requestPage;
    public final int totalCount;
    public final int movableMinPage = 1;
    public final int movableMaxPage;
    public final int offsetCount;

    public MemoVo(int requestPage, int totalCount) {
        this.requestPage = requestPage;
        this.totalCount = totalCount;
        this.movableMaxPage = totalCount / this.countPerPage + (totalCount % this.countPerPage == 0 ? 0 : 1);
        this.offsetCount = (requestPage - 1) * this.countPerPage;
    }
}

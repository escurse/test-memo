package com.escass.testmemo.mappers;

import com.escass.testmemo.entities.MemoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemoMapper {
    int insertMemo(MemoEntity memo);

    int selectMemoCountByIndex(@Param("index") int index);

    MemoEntity[] selectMemosByIndex(@Param("index") int index,
                                    @Param("limitCount") int limitCount,
                                    @Param("offsetCount") int offsetCount);

    int selectMemoCountBySearch(@Param("search") String search);

    MemoEntity[] selectMemoBySearch(@Param("search") String search,
                                  @Param("limitCount") int limitCount,
                                  @Param("offsetCount") int offsetCount);
}

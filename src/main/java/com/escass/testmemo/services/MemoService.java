package com.escass.testmemo.services;

import com.escass.testmemo.entities.MemoEntity;
import com.escass.testmemo.mappers.MemoMapper;
import com.escass.testmemo.results.memo.WriteResult;
import com.escass.testmemo.vos.MemoVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    public Pair<MemoEntity[], MemoVo> getMemosByIndex(int index) {
        if (index < 1) {
            return null;
        }
        int totalCount = this.memoMapper.selectMemoCountByIndex(index);
        MemoVo memoVo = new MemoVo(index, totalCount);
        return Pair.of(this.memoMapper.selectMemosByIndex(index, memoVo.countPerPage, memoVo.offsetCount), memoVo);
    }

    public WriteResult write(MemoEntity memo) {
        if (memo == null ||
            memo.getIndex() < 0 ||
            memo.getWriter() == null || memo.getWriter().length() < 2 || memo.getWriter().length() > 10 ||
            memo.getContent() == null || memo.getContent().isEmpty() || memo.getContent().length() > 100) {
            return WriteResult.FAILURE;
        }
        memo.setCreatedAt(LocalDateTime.now());
        memo.setDeleted(false);
        return this.memoMapper.insertMemo(memo) > 0
                ? WriteResult.SUCCESS
                : WriteResult.FAILURE;
    }
}

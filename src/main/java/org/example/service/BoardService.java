package org.example.service;

import org.example.dto.BoardEditRequest;
import org.example.dto.BoardRegRequest;
import org.example.entity.BoardEntity;
import org.example.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Cacheable(cacheNames = "board", key = "#seq")
    public BoardEntity findBySeq(Long seq) {
        return boardMapper.findBySeq(seq);
    }

    public List<BoardEntity> findByTitle(String title) {
        return boardMapper.findByTitle(title);
    }

    public String save(BoardRegRequest boardRegRequest) {
        return (boardMapper.save(boardRegRequest) == 1) ? "OK" : "Fail";
    }

    public int update(BoardEditRequest board) {
        return boardMapper.update(board);
    }

    public int delete(Long boardSeq) {
        return boardMapper.delete(boardSeq);
    }
}

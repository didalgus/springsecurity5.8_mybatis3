package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.dto.BoardEditRequest;
import org.example.dto.BoardRegRequest;
import org.example.entity.BoardEntity;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT seq, title, content, reg_name, reg_dt FROM board WHERE seq = #{seq}")
    BoardEntity findBySeq(Long seq);

    @Select({"<script>",
            "SELECT seq, title, content, reg_type, reg_name, reg_dt FROM board",
            "<where>",
            "<if test='title != null'> title LIKE CONCAT('%', #{title}, '%')</if>",
            "</where>",
            "</script>"})
    List<BoardEntity> findByTitle(String title);

    @Insert("INSERT INTO board (title, content, reg_name) VALUES (#{title}, #{content}, #{regName})")
    int save(BoardRegRequest boardRegRequest);

    @Update("UPDATE board SET title = #{title}, content = #{content}, reg_name=#{regName} WHERE seq = #{seq}")
    int update(BoardEditRequest boardEditRequest);

    @Delete("DELETE FROM board WHERE seq = #{seq}")
    int delete(Long seq);
}

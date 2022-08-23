package com.sp.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.Board;

@Mapper
public interface BoardMapper {
	public int insertBoard(Board dto);
	public int dataCount(Map<String, Object> map);
	public List<Board> listBoard(Map<String, Object> map);
	public Board readBoard(int num);
	public int updateHitCount(int num);
	public Board preReadBoard(Map<String, Object> map);
	public Board nextReadBoard(Map<String, Object> map);
	public int updateBoard(Board dto);
	public int deleteBoard(int num);
}

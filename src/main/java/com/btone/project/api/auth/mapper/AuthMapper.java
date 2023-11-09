package com.btone.project.api.auth.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.btone.project.api.auth.vo.AuthVO;

@Mapper
public interface AuthMapper {
	public int signup(AuthVO input);
	public int edit(AuthVO input);
	public int cancel(AuthVO input);
	public List<AuthVO> lookup(AuthVO input);
}

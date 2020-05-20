package org.example.app.mapper;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberMapper {

    Member selectById(Integer id);

    List<Member> selectAll();

}

package com.spring.boot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import com.spring.boot.domain.Member;

// 스프링 JDBCTemplate 방식
public class JdbcTemplateMemberRepository implements MemberRepository{

	private final JdbcTemplate jdbcTemplate;
	
	// 생성자가 하나일 때는 @AutoWired 생략 가능
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	@Override
	public Member save(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		member.setId(key.longValue());
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// JdbcTemplate에서 Select 쿼리문을 사용할 때 query()메서드를 사용한다.
		List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return result.stream().findAny(); //findAny()는 조건에 맞는 요소 1개를 리턴한다.
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny(); //findAny()는 조건에 맞는 요소 1개를 리턴한다.
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}

	private RowMapper<Member> memberRowMapper(){
		
		// 람다식으로 표현
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member; 
				
		};
		
		/*
		return new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return member; 
				
			}
		};
		*/
	}
	
}

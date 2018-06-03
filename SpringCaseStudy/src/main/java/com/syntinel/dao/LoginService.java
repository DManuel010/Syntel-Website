package com.syntinel.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Login;

@Service
public class LoginService implements ServiceInterface<Login>{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public LoginService() {
		
	}
	
	@Override
	public void create(Login login) {
		String sql = "INSERT INTO LOGIN VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, new Object [] {login.getLoginId(), login.getUsername(), login.getPassword(), login.getType()});
	}
	
	public void delete(int loginId) {
		String sql = "DELETE FROM LOGIN WHERE LOGINID=?";
		jdbcTemplate.update(sql, loginId);
	}
}

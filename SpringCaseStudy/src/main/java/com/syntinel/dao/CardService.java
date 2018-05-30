package com.syntinel.dao;


import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Card;

@Service
public class CardService{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public CardService() {
		
	}


	public void create(Card card) {
		
		String sql = "INSERT INTO CARD(NAME, NUMBER, EXPDATE) VALUES(?,?,?)";
		jdbcTemplate.update(sql,  new Object[] {card.getName(), card.getNumber(), card.getExpirationDate()});
	
	}
	
	public void delete(int cardID) {
		String sql = "DELETE FROM CARD WHERE CARDID=?";
		jdbcTemplate.update(sql, cardID);
	}
}

package org.syntinel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Location;

public class LocationRowMapper implements RowMapper<Location>{

	@Override
	public Location mapRow(ResultSet rs, int index) throws SQLException {
		Location location = new Location();
		location.setLocationId(rs.getInt(1));
		location.setCountry(rs.getString(2));
		location.setState(rs.getString(3));
		location.setCity(rs.getString(4));
		location.setStreet_number(rs.getString(5));
		location.setRoom_number(rs.getString(6));
		location.setZip_code(rs.getString(7));
		return location;
	}

}

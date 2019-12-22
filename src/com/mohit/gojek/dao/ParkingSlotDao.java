package com.mohit.gojek.dao;

import java.sql.SQLException;
import java.util.List;

import com.mohit.gojek.model.ParkingSlot;

public interface ParkingSlotDao {

	public ParkingSlot save(ParkingSlot parkingSlot) throws SQLException;

	public ParkingSlot update(ParkingSlot parkingSlot) throws SQLException;
	
	public ParkingSlot get(Long id) throws SQLException;

	public Long getSlotNumberById(Long id) throws SQLException;

	public ParkingSlot getBySlotNumber(Long slotNumber) throws SQLException;
	
	public List<Long> getSlotNumbersByStatus(Boolean status) throws SQLException;
}

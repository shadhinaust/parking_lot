package com.mohit.gojek.dao;

import java.sql.SQLException;
import java.util.List;

import com.mohit.gojek.model.Reservation;

public interface ReservationDao {

	public Reservation save(Reservation reservation) throws SQLException;

	public Reservation update(Reservation reservation) throws SQLException;

	public Reservation getByParkingSlotIdAndStatus(Long parkingSlotId, Boolean status) throws SQLException;

	public Long getParkingSlotIdByCarIdAndStatus(Long carId, Boolean status) throws SQLException;

	public List<Reservation> getByStatus(Boolean status) throws SQLException;
}

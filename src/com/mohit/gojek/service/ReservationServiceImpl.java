package com.mohit.gojek.service;

import java.sql.SQLException;
import java.util.List;

import com.mohit.gojek.dao.ReservationDao;
import com.mohit.gojek.dao.ReservationDaoImpl;
import com.mohit.gojek.model.Reservation;

public class ReservationServiceImpl implements ReservationService {

	private ReservationDao reservationDao = new ReservationDaoImpl();

	@Override
	public Reservation save(Reservation reservation) {
		try {
			return reservationDao.save(reservation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reservation getByParkingSlotIdAndStatu(Long parkingSlotId, Boolean status) {
		try {
			return reservationDao.getByParkingSlotIdAndStatus(parkingSlotId, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reservation update(Reservation reservation) {
		try {
			return reservationDao.update(reservation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reservation> getByStatus(Boolean status) {
		try {
			return reservationDao.getByStatus(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long getParkingSlotIdByCarIdAndStatu(Long carId, Boolean status) {
		try {
			return reservationDao.getParkingSlotIdByCarIdAndStatus(carId, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

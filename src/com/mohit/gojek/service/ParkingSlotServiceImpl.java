package com.mohit.gojek.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.mohit.gojek.dao.ParkingSlotDao;
import com.mohit.gojek.dao.ParkingSlotDaoImpl;
import com.mohit.gojek.model.ParkingSlot;

public class ParkingSlotServiceImpl implements ParkingSlotService {
	
	private ParkingSlotDao parkingSlotDao = new ParkingSlotDaoImpl();

	@Override
	public ParkingSlot saveParkingSlot(ParkingSlot parkingSlot) {
		try {
			return parkingSlotDao.save(parkingSlot);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ParkingSlot updateParkingSlot(ParkingSlot parkingSlot) {
		try {
			return parkingSlotDao.update(parkingSlot);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Long> getAvailableParkingSlotNumbers() {
		try {
			return parkingSlotDao.getSlotNumbersByStatus(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ParkingSlot getBySlotNumber(Long slotNumber) {
		try {
			return parkingSlotDao.getBySlotNumber(slotNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ParkingSlot getById(Long id) {
		try {
			return parkingSlotDao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

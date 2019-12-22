package com.mohit.gojek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mohit.gojek.connection.ConnectionProvider;
import com.mohit.gojek.model.ParkingSlot;

public class ParkingSlotDaoImpl implements ParkingSlotDao {

	private static Connection connection;
	private static PreparedStatement ps;

	@Override
	public ParkingSlot save(ParkingSlot parkingSlot) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("insert into parking_slot(slot_number, status) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, parkingSlot.getSlotNumber());
			ps.setBoolean(2, parkingSlot.getStatus());
			ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				parkingSlot.setId(resultSet.getLong(1));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			parkingSlot = null;
		} finally {
			ps.close();
			connection.close();
		}
		return parkingSlot;
	}

	@Override
	public ParkingSlot update(ParkingSlot parkingSlot) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("update parking_slot set status=? where id=?");
			ps.setBoolean(1, parkingSlot.getStatus());
			ps.setLong(2, parkingSlot.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			parkingSlot = null;
		} finally {
			ps.close();
			connection.close();
		}
		return parkingSlot;
	}

	@Override
	public ParkingSlot getBySlotNumber(Long slotNumber) throws SQLException {
		ParkingSlot parkingSlot = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from parking_slot where slot_number=?");
			ps.setLong(1, slotNumber);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				parkingSlot.setId(resultSet.getLong("id"));
				parkingSlot.setSlotNumber(resultSet.getLong("slot_number"));
				parkingSlot.setStatus(resultSet.getBoolean("status"));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return parkingSlot;
	}

	@Override
	public Long getSlotNumberById(Long id) throws SQLException {
		Long slotNumber = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select slot_number from parking_slot where id=?");
			ps.setLong(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				slotNumber = resultSet.getLong("slot_number");
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return slotNumber;
	}

	@Override
	public ParkingSlot get(Long id) throws SQLException {
		ParkingSlot parkingSlot = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from parking_slot where id=?");
			ps.setLong(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				parkingSlot.setId(resultSet.getLong("id"));
				parkingSlot.setSlotNumber(resultSet.getLong("slot_number"));
				parkingSlot.setStatus(resultSet.getBoolean("status"));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return parkingSlot;
	}

	@Override
	public List<Long> getSlotNumbersByStatus(Boolean status) throws SQLException {
		List<Long> ids = new ArrayList<Long>();
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select slot_number from parking_slot where status=?");
			ps.setBoolean(1, status);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ids.add(resultSet.getLong("slot_number"));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return ids;
	}
}

package com.mohit.gojek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mohit.gojek.connection.ConnectionProvider;
import com.mohit.gojek.model.Reservation;

public class ReservationDaoImpl implements ReservationDao {

	private static Connection connection;
	private static PreparedStatement ps;

	@Override
	public Reservation save(Reservation reservation) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("insert into reservation(parking_slot_id, car_id) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getParkingSlotId());
			ps.setLong(2, reservation.getCarId());
			ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				reservation.setId(resultSet.getLong(1));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			reservation = null;
		} finally {
			ps.close();
			connection.close();
		}
		return reservation;
	}

	@Override
	public Long getParkingSlotIdByCarIdAndStatus(Long carId, Boolean status) throws SQLException {
		Long parkingSlotId = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select parking_slot_id from reservation where car_id=? and status=?");
			ps.setLong(1, carId);
			ps.setBoolean(2, status);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				parkingSlotId = resultSet.getLong(1);
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return parkingSlotId;
	}

	@Override
	public List<Reservation> getByStatus(Boolean status) throws SQLException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from reservation where status=?");
			ps.setBoolean(1, status);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getLong("id"));
				reservation.setParkingSlotId(resultSet.getLong("parking_slot_id"));
				reservation.setCarId(resultSet.getLong("car_id"));
				reservation.setStatus(resultSet.getBoolean("status"));
				reservations.add(reservation);
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return reservations;
	}

	@Override
	public Reservation update(Reservation reservation) throws SQLException {
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("update reservation set status=? where id=?");
			ps.setBoolean(1, reservation.getStatus());
			ps.setLong(2, reservation.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			reservation = null;
		} finally {
			ps.close();
			connection.close();
		}
		return reservation;
	}

	@Override
	public Reservation getByParkingSlotIdAndStatus(Long parkingSlotId, Boolean status) throws SQLException {
		Reservation reservation = null;
		try {
			connection = ConnectionProvider.getConnection();
			ps = connection.prepareStatement("select * from reservation where parking_slot_id=? and status=?");
			ps.setLong(1, parkingSlotId);
			ps.setBoolean(2, status);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				reservation = new Reservation();
				reservation.setId(resultSet.getLong("id"));
				reservation.setParkingSlotId(resultSet.getLong("parking_slot_id"));
				reservation.setCarId(resultSet.getLong("car_id"));
				reservation.setStatus(resultSet.getBoolean("status"));
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			connection.close();
		}
		return reservation;
	}

}

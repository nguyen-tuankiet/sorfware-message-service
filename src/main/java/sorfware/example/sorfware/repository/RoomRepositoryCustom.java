package sorfware.example.sorfware.repository;

import sorfware.example.sorfware.model.entity.Room;

import java.util.List;

public interface RoomRepositoryCustom {
    List<Room> findRoomsByUserId(String userId);
}

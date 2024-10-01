package vn.tiger.shipment.command.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tiger.shipment.command.api.entities.Shipment;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
}

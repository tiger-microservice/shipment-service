package vn.tiger.shipment.command.api.events;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vn.tiger.sagacommon.events.OrderShippedEvent;
import vn.tiger.shipment.command.api.entities.Shipment;
import vn.tiger.shipment.command.api.repositories.ShipmentRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ShippingEventHandler {

    private final ShipmentRepository shipmentRepository;

    @EventHandler
    public void on(OrderShippedEvent event) {
        Shipment shipment = Shipment.builder().build();
        BeanUtils.copyProperties(event, shipment);
        shipment.setShipmentId(UUID.fromString(event.getShippingId()));
        shipmentRepository.save(shipment);
    }
}

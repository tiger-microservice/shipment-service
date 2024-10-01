package vn.tiger.shipment.command.api.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import vn.tiger.sagacommon.commands.ShipOrderCommand;
import vn.tiger.sagacommon.events.OrderShippedEvent;

@Aggregate
public class ShipmentAggregate {

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public ShipmentAggregate() {}

    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand shipOrderCommand) {
        // Validate the Command
        // Publish the Order Shipped Event
        OrderShippedEvent orderShippedEvent = OrderShippedEvent.builder()
                .shippingId(shipOrderCommand.getShipmentId())
                .orderId(shipOrderCommand.getOrderId())
                .shipmentStatus("COMPLETED")
                .build();

        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event) {
        this.orderId = event.getOrderId();
        this.shipmentId = event.getShippingId();
        this.shipmentStatus = event.getShipmentStatus();
    }
}

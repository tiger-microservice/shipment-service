package vn.tiger.shipment.command.api.entities;

import com.tiger.cores.entities.SoftDelEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "shipment")
@SQLDelete(sql = "UPDATE shipment set is_deleted = true where id = ?")
public class Shipment extends SoftDelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shipment_id")
    UUID shipmentId;
    private String orderId;
    private LocalDateTime executeTime;
    private String shipmentStatus;
}

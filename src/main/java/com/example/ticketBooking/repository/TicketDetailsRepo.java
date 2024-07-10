package com.example.ticketBooking.repository;

import com.example.ticketBooking.entity.TicketDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface TicketDetailsRepo extends JpaRepository<TicketDetailsEntity,Integer> {
    TicketDetailsEntity findByUserId(@RequestParam Integer userId);

    TicketDetailsEntity findByTableNo(@RequestParam Integer tableNo);
    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'VIP' AND t.isOccupied = 'Y'")
    Integer countVipOccupied();
    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'VVIP' AND t.isOccupied = 'Y'")
    Integer countVVipOccupied();
    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'DELEGATES' AND t.isOccupied = 'Y'")
    Integer countDelegatesOccupied();

    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'VIP' AND t.isOccupied = 'N'")
    Integer countVipUnOccupied();
    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'VVIP' AND t.isOccupied = 'N'")
    Integer countVVipUnOccupied();
    @Query("SELECT COUNT(t) FROM TicketDetailsEntity t WHERE t.type = 'DELEGATES' AND t.isOccupied = 'N'")
    Integer countDelegatesUnOccupied();
}

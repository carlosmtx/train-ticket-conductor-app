package com.railway.railwayconductor.business.security.strategy;

import com.railway.railwayconductor.business.api.entity.Ticket;

/**
 * Created by cteixeira on 10-11-2015.
 */
public class TicketHashStrategy implements HashStrategy{
    private final Ticket ticket;

    public TicketHashStrategy(Ticket ticket) {

        this.ticket = ticket;
    }

    @Override
    public String getStringToHash() {
        return ticket.getId() + ticket.getDeparture() + ticket.getArrival() + ticket.getDepartureTime();
    }
}

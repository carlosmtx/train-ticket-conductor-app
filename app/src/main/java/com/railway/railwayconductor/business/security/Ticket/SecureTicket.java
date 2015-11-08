package com.railway.railwayconductor.business.security.Ticket;

import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.security.Signature.SignatureProvider;
import com.railway.railwayconductor.business.security.Signature.SignatureValidator;
import com.railway.railwayconductor.business.security.Strategy.HashStrategy;

/**
 * Created by cteixeira on 08-11-2015.
 */
public class SecureTicket implements HashStrategy, SignatureProvider {
    private final Ticket ticket;
    private final SignatureValidator signatureValidator;

    public SecureTicket(Ticket ticket) {
        this.ticket = ticket;
        signatureValidator = new SignatureValidator(this,this);
    }

    @Override
    public String getStringToHash() {
        return ticket.getId() + ticket.getDeparture() + ticket.getArrival() + ticket.getDepartureTime();
    }

    @Override
    public String provideSignature() {
        return ticket.getSignature();
    }

    public boolean isValid(){
        return signatureValidator.validate();
    }
}

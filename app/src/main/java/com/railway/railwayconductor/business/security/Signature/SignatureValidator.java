package com.railway.railwayconductor.business.security.Signature;

import android.util.Base64;

import com.railway.railwayconductor.business.security.Strategy.HashStrategy;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by cteixeira on 08-11-2015.
 */
public class SignatureValidator {
    private final HashStrategy strategy;
    private final SignatureProvider signatureProvider;

    public SignatureValidator(HashStrategy strategy,SignatureProvider signatureProvider) {

        this.strategy = strategy;
        this.signatureProvider = signatureProvider;
    }

    public boolean validate(){
        try {
            String value = strategy.getStringToHash();
            String signature = signatureProvider.provideSignature();

            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(signature, Base64.DEFAULT));
            KeyFactory kf = KeyFactory.getInstance("RSA");

            PublicKey key = kf.generatePublic(spec);

            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(key);
            signer.update(value.getBytes());

            return (signer.verify(Base64.decode(signature, Base64.DEFAULT)));
        } catch (InvalidKeySpecException | InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

}

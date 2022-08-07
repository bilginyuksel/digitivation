package com.bilginyuksel.digitivation;

public interface BusinessUseCase<REQ, RES> {
    RES handle(REQ req);
}

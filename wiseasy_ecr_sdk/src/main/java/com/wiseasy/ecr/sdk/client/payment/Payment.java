package com.wiseasy.ecr.sdk.client.payment;

import com.wiseasy.ecr.sdk.listener.ECRHubResponseCallBack;

/**
 * payment
 */
public abstract class Payment {

    public abstract ECRHubResponseCallBack getResponseCallBack(String transType);

    public abstract void init(ECRHubResponseCallBack callBack);

    public abstract void sale(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void close(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void query(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void refund(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void cancel(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void auth(PaymentRequestParams params, ECRHubResponseCallBack callBack);

    public abstract void completion(PaymentRequestParams params, ECRHubResponseCallBack callBack);
}

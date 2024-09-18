package com.wiseasy.ecr.sdk.client.payment;

public class PaymentResponseParams {

    /**
     * 消息主题
     */
    private String topic = "";
    /**
     * 应用id
     */
    private String app_id;

    /**
     * 消息id
     */
    private String request_id;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 交易是否成功
     */
    private String response_code;

    private String response_msg;
    private PaymentResponseParams.BizData biz_data;

    private PaymentResponseParams.DeviceData device_data;

    public void setDevice_data(PaymentResponseParams.DeviceData device_data) {
        this.device_data = device_data;
    }

    public PaymentResponseParams.DeviceData getDevice_data() {
        if (null == device_data) {
            device_data = new PaymentResponseParams.DeviceData();
        }
        return device_data;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setBiz_data(PaymentResponseParams.BizData biz_data) {
        this.biz_data = biz_data;
    }

    public PaymentResponseParams.BizData getBiz_data() {
        return biz_data;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getApp_id() {
        return app_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTopic() {
        return topic;
    }


    public class DeviceData {
        /**
         * device mac address
         */
        private String mac_address = "";
        /**
         * device name
         */
        private String device_name = "";
        /**
         * device alias name
         */
        private String alias_name = "";
        /**
         * server ip address
         */
        private String ip_address = "";
        /**
         * server port number
         */
        private String port = "";

        public void setAlias_name(String alias_name) {
            this.alias_name = alias_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public void setMac_address(String mac_address) {
            this.mac_address = mac_address;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getAlias_name() {
            return alias_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public String getIp_address() {
            return ip_address;
        }

        public String getMac_address() {
            return mac_address;
        }

        public String getPort() {
            return port;
        }
    }


    public class BizData {
        /**
         * 是否需要终端确认
         */
        private boolean confirm_on_terminal;
        /**
         * 多订单处理模式
         */
        private String order_queue_mode;

        /**
         * 时间有效期
         */
        private int expires;

        /**
         * 交易号
         */
        private String trans_no;

        /**
         * 调用方订单号
         */
        private String merchant_order_no;

        /**
         * 原调用方订单号
         */
        private String orig_merchant_order_no;

        /**
         * 支付方式类目
         */
        private String pay_scenario;

        /**
         * 支付方式id
         */
        private String pay_method_id;

        /**
         * 附加数据
         */
        private String attach;

        /**
         * 描述信息
         */
        private String description;

        /**
         * 回调地址
         */
        private String notify_url;

        /**
         * 交易状态
         */
        private String trans_status;

        /**
         * 错误信息
         */
        private String msg;

        private String push_no;

        private String order_amount;

        private String cash_amount;

        private String tip_amount;

        private String service_charge_amount;

        private String tax_amount;

        private String price_currency;

        private String trans_type;

        private boolean limit_length;

        private boolean is_auto_settlement;

        private String card_type;

        public String getService_charge_amount() {
            return service_charge_amount;
        }

        public void setService_charge_amount(String service_charge_amount) {
            this.service_charge_amount = service_charge_amount;
        }

        public String getTax_amount() {
            return tax_amount;
        }

        public void setTax_amount(String tax_amount) {
            this.tax_amount = tax_amount;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getCard_type() {
            return card_type;
        }

        public boolean isIs_auto_settlement() {
            return is_auto_settlement;
        }

        public boolean isConfirm_on_terminal() {
            return confirm_on_terminal;
        }

        public String getPay_scenario() {
            return pay_scenario;
        }

        public void setPay_scenario(String pay_scenario) {
            this.pay_scenario = pay_scenario;
        }

        public void setIs_auto_settlement(boolean is_auto_settlement) {
            this.is_auto_settlement = is_auto_settlement;
        }

        public void setcash_amount(String cash_amount) {
            this.cash_amount = cash_amount;
        }

        public String getcash_amount() {
            return cash_amount;
        }

        public void setLimit_length(boolean limit_length) {
            this.limit_length = limit_length;
        }

        public boolean getLimit_length() {
            return limit_length;
        }

        public void setPush_no(String push_no) {
            this.push_no = push_no;
        }

        public String getPush_no() {
            return push_no;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public String getPrice_currency() {
            return price_currency;
        }

        public String getTip_amount() {
            return tip_amount;
        }

        public String getTrans_type() {
            return trans_type;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public void setPrice_currency(String price_currency) {
            this.price_currency = price_currency;
        }

        public void setTip_amount(String tip_amount) {
            this.tip_amount = tip_amount;
        }

        public void setTrans_type(String trans_type) {
            this.trans_type = trans_type;
        }

        public String getTrans_status() {
            return trans_status;
        }

        public int getExpires() {
            return expires;
        }

        public String getOrder_queue_mode() {
            return order_queue_mode;
        }

        public void setTrans_status(String trans_status) {
            this.trans_status = trans_status;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setMerchant_order_no(String merchant_order_no) {
            this.merchant_order_no = merchant_order_no;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public void setOrig_merchant_order_no(String orig_merchant_order_no) {
            this.orig_merchant_order_no = orig_merchant_order_no;
        }

        public void setPay_method_id(String pay_method_id) {
            this.pay_method_id = pay_method_id;
        }

        public void setTrans_no(String trans_no) {
            this.trans_no = trans_no;
        }

        public String getAttach() {
            return attach;
        }

        public String getDescription() {
            return description;
        }

        public String getMerchant_order_no() {
            return merchant_order_no;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public String getOrig_merchant_order_no() {
            return orig_merchant_order_no;
        }

        public String getPay_method_id() {
            return pay_method_id;
        }

        public String getTrans_no() {
            return trans_no;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setConfirm_on_terminal(boolean confirm_on_terminal) {
            this.confirm_on_terminal = confirm_on_terminal;
        }

        public boolean getConfirm_on_terminal() {
            return confirm_on_terminal;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }

        public void setOrder_queue_mode(String order_queue_mode) {
            this.order_queue_mode = order_queue_mode;
        }
    }
}

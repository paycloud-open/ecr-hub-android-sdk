package com.wiseasy.ecr.sdk.demo

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.wiseasy.ecr.sdk.client.ECRHubClient
import com.wiseasy.ecr.sdk.client.payment.PaymentRequestParams
import com.wiseasy.ecr.sdk.client.payment.PaymentResponseParams
import com.wiseasy.ecr.sdk.listener.ECRHubResponseCallBack
import com.wiseasy.ecr.sdk.util.Constants
import kotlinx.android.synthetic.main.activity_refund.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class RefundActivity : Activity() {
    companion object {
        lateinit var mClient: ECRHubClient
    }


    fun getCurDateStr(format: String?): String? {
        val c = Calendar.getInstance()
        return date2Str(c, format)
    }

    fun date2Str(c: Calendar?, format: String?): String? {
        return if (c == null) null else date2Str(
            c.time,
            format
        )
    }

    fun date2Str(d: Date?, format: String?): String? {
        var format = format
        return if (d == null) {
            null
        } else {
            if (format == null || format.length == 0) {
                format = "yyyy-MM-dd HH:mm:ss"
            }
            val sdf = SimpleDateFormat(format)
            sdf.format(d)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refund)
        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        tv_btn_2.setOnClickListener {
            finish()
        }
        tv_btn_1.setOnClickListener {
            val amount = edit_input_amount.text.toString()
            if (amount.isEmpty()) {
                Toast.makeText(this, "Please input amount", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val merchantOrderNo = edit_input_merchant_order_no.text.toString()
            val params = PaymentRequestParams()
            params.trans_type = Constants.TRANS_TYPE_REFUND
            params.app_id = "wz6012822ca2f1as78"
            if (merchantOrderNo.isEmpty()) {
                if (sharedPreferences.getString("merchant_order_no", "").toString().isEmpty()) {
                    Toast.makeText(this, "Please input orig merchant order no", Toast.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                } else {
                    params.orig_merchant_order_no =
                        sharedPreferences.getString("merchant_order_no", "").toString()
                }
            } else {
                params.orig_merchant_order_no = merchantOrderNo

            }
            params.merchant_order_no = "123" + getCurDateStr("yyyyMMddHHmmss")
            params.order_amount = amount
            params.on_screen_tip = false
            params.confirm_on_terminal = false
            val voiceData = params.voice_data
            voiceData.content = "CodePay Register Received a new order"
            voiceData.content_locale = "en-US"
            params.voice_data = voiceData
            runOnUiThread {
                tv_btn_3.text =
                    "Send Refund data" + params.toJSON().toString()
            }
            mClient.payment.refund(params, object :
                ECRHubResponseCallBack {
                override fun onError(errorCode: String?, errorMsg: String?) {
                    runOnUiThread {
                        tv_btn_3.text = tv_btn_3.text.toString() + "\n" + "交易失败" + errorMsg
                    }
                }

                override fun onSuccess(data: PaymentResponseParams?) {
                    runOnUiThread {
                        tv_btn_3.text =
                            tv_btn_3.text.toString() + "\n" + "Result:" + JSON.toJSON(data)
                    }
                }
            })
        }

        tv_btn_4.setOnClickListener {
            val merchantOrderNo = edit_input_merchant_order_no.text.toString()
            val params = PaymentRequestParams()
            params.trans_type = Constants.TRANS_TYPE_VOID
            params.app_id = "wz6012822ca2f1as78"
            if (merchantOrderNo.isEmpty()) {
                if (sharedPreferences.getString("merchant_order_no", "").toString().isEmpty()) {
                    Toast.makeText(this, "Please input orig merchant order no", Toast.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                } else {
                    params.orig_merchant_order_no =
                        sharedPreferences.getString("merchant_order_no", "").toString()
                }
            } else {
                params.orig_merchant_order_no = merchantOrderNo
            }
            params.merchant_order_no = "123" + getCurDateStr("yyyyMMddHHmmss")
            params.confirm_on_terminal = false
            val voiceData = params.voice_data
            voiceData.content = "CodePay Register Received a new order"
            voiceData.content_locale = "en-US"
            params.voice_data = voiceData
            runOnUiThread {
                tv_btn_3.text =
                    "Send Void data --> " + params.toJSON().toString()
            }
            mClient.payment.cancel(params, object :
                ECRHubResponseCallBack {
                override fun onError(errorCode: String?, errorMsg: String?) {
                    runOnUiThread {
                        tv_btn_3.text = tv_btn_3.text.toString() + "\n" + "Failure:" + errorMsg
                    }
                }

                override fun onSuccess(data: PaymentResponseParams?) {
                    runOnUiThread {
                        tv_btn_3.text =
                            tv_btn_3.text.toString() + "\n" + "Result:" + JSON.toJSON(data)
                    }
                }
            })
        }

        tv_btn_close.setOnClickListener {
            val merchantOrderNo = sharedPreferences.getString("merchant_order_no", "").toString()
            val params =
                PaymentRequestParams()
            if (merchantOrderNo.isEmpty()) {
                Toast.makeText(this, "Transaction does not exist", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                params.merchant_order_no = merchantOrderNo
            }
            params.app_id = "wz6012822ca2f1as78"
            runOnUiThread {
                tv_btn_3.text =
                    "Send Close data --> " + params.toJSON().toString()
            }
            mClient.payment.close(params, object :
                ECRHubResponseCallBack {
                override fun onError(errorCode: String?, errorMsg: String?) {
                    runOnUiThread {
                        tv_btn_3.text = tv_btn_3.text.toString() + "\n" + "Failure:" + errorMsg
                    }
                }

                override fun onSuccess(data: PaymentResponseParams?) {
                    val editor = sharedPreferences.edit()
                    editor.remove("merchant_order_no")
                    editor.apply()
                    runOnUiThread {
                        tv_btn_3.text =
                            tv_btn_3.text.toString() + "\n" + "result:" + JSON.toJSON(data)
                    }
                }

            })
        }
    }
}
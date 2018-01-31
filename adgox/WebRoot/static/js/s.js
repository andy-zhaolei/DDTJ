webpackJsonp([1], {
    650 : function(t, e, a) {
        function n(t) {
            a(659)
        }
        var r = a(10)(a(653), a(665), n, null, null);
        t.exports = r.exports
    },
    653 : function(t, e, a) {
        "use strict";
        Object.defineProperty(e, "__esModule", {
            value: !0
        });
        var n = a(244),
        r = a.n(n),
        o = a(243),
        i = a.n(o),
        s = a(242),
        u = a(101);
        a(241);
        e["default"] = {
            name: "zn_withdraw",
            data: function() {
                return {
                    idStatus: !1,
                    bankStatus: !1,
                    nameStatus: !1,
                    phoneStatus: !1,
                    form: {
                        amount: "",
                        certNo: "",
                        name: "",
                        bankAccount: "",
                        bank: [],
                        phone: "",
                        fundPassword: "",
                        verifyCode: "",
                        orderNum: ""
                    },
                    list: [],
                    viewName: "",
                    mobile: "",
                    balance: "",
                    bankName: [],
                    allow: !0,
                    isFirstIdStatus: !0,
                    isFirstNameStatus: !0,
                    isFirstBankStatus: !0,
                    isFirstBankAccountStatus: !0,
                    isFirstPhoneStatus: !0,
                    canGetVcode: !0,
                    afterTime: 60,
                    tips: "",
                    showLoading: !1,
                    loadingText: "正在提交..."
                }
            },
            created: function() {
                var t = this;
                return i()(r.a.mark(function e() {
                    var a;
                    return r.a.wrap(function(e) {
                        for (;;) switch (e.prev = e.next) {
                        case 0:
                            return a = JSON.parse(payment.getUserInfo()),
                            t.form.fundPassword = a.fundPassword,
                            e.next = 4,
                            t.getListBank();
                        case 4:
                            return e.next = 6,
                            t.checkCard();
                        case 6:
                            return e.next = 8,
                            t.getBalance();
                        case 8:
                            return e.next = 10,
                            t.getPhone();
                        case 10:
                            return e.next = 12,
                            t.getOrderNum();
                        case 12:
                            return e.next = 14,
                            t.getFundTimeInfo();
                        case 14:
                        case "end":
                            return e.stop()
                        }
                    },
                    e, t)
                }))()
            },
            computed: {
                isSubmit: function() {
                    return ! (this.form.amount && this.bankName.length && this.form.certNo && this.viewName && this.form.bankAccount && this.form.phone && this.form.verifyCode && this.form.fundPassword && this.allow)
                },
                fee: function() {
                    return this.form.amount && this.form.amount < 100 ? "2.00": "0.00"
                }
            },
            methods: {
                selectBank: function(t) {
                    this.form.bank = t
                },
                validateId: function() {
                    var t = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
                    t.test(this.form.certNo) ? this.idStatus = !0 : (this.$vux.toast.text("请输入正确的身份证"), this.idStatus = !1)
                },
                validateAmount: function() {
                    a.i(u.a)(this.form.amount) ? this.form.amount <= 2 ? (this.$vux.toast.text("提现余额需要2元以上"), this.form.amount = "") : this.form.amount > this.balance && (this.$vux.toast.text("提现余额不能超过" + this.balance + "元"), this.form.amount = "") : (this.$vux.toast.text("金额最多为小数点后两位"), this.form.amount = "")
                },
                validateName: function() {
                    var t = /^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$/;
                    t.test(this.viewName) ? this.nameStatus = !0 : (this.$vux.toast.text("请输入中文"), this.nameStatus = !1)
                },
                validateBank: function() {
                    var t = /^\d{16,19}$/;
                    t.test(this.form.bankAccount) ? this.bankStatus = !0 : (this.$vux.toast.text("请输入正确的银行卡号"), this.bankStatus = !1)
                },
                validatePhone: function() {
                    var t = /^1[345789]\d{9}$/;
                    t.test(this.form.phone) ? this.phoneStatus = !0 : (this.$vux.toast.text("请输入正确的手机号"), this.phoneStatus = !1)
                },
                getListBank: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/fund/listBankH5", {
                                    data: {
                                        payCode: "ZNDEFRAY"
                                    },
                                    auth: !0
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.list = o);
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                getOrderNum: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/fund/out/order/num", {
                                    auth: !0
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.form.orderNum = o.orderNum);
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                getFundTimeInfo: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/fund/getFundTimeInfo", {
                                    auth: !0,
                                    data: {
                                        type: "OUT"
                                    }
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.tips = o.info);
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                getPhone: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/userInfo/query", {
                                    auth: !0
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.mobile = o.mobile);
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                getBalance: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/fund/query", {
                                    auth: !0
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.balance = o.balance);
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                checkCard: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i, u;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                return e.next = 2,
                                s.a.post("/api/oauth/fund/card/query", {
                                    data: {
                                        payType: 2,
                                        payCode: "ZNDEFRAY"
                                    },
                                    auth: !0
                                });
                            case 2:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (o.certNo && ("CHANNEL" === o.authType && (t.isFirstIdStatus = !1), t.idStatus = !0, t.form.certNo = o.certNo), o.name && ("CHANNEL" === o.authType && (t.isFirstNameStatus = !1), t.nameStatus = !0, t.form.name = o.name, t.viewName = o.name), o.bank && (t.isFirstBankStatus = !1, u = t.list.filter(function(t) {
                                    return t.value === o.bank
                                }), u.length && (t.form.bank = [], t.form.bank.push(u[0].value), t.bankName = [], t.bankName.push(u[0].name))), o.bankAccount && (t.isFirstBankAccountStatus = !1, t.bankStatus = !0, t.form.bankAccount = o.bankAccount), o.phone && (t.isFirstPhoneStatus = !1, t.phoneStatus = !0, t.form.phone = o.phone));
                            case 7:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                getCode: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i, u, c, m;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                if (!t.mobile) {
                                    e.next = 10;
                                    break
                                }
                                return e.next = 3,
                                s.a.post("/api/verifyCode/getVerifyCodeByCenter", {
                                    data: {
                                        mobile: t.mobile,
                                        type: "withdraw"
                                    },
                                    auth: !0
                                });
                            case 3:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                "SUCCESS" === i && (t.$vux.toast.text("验证码发送成功"), t.canGetVcode = !1, u = t.afterTime, c = null, m = function() {
                                    t.canGetVcode = !0,
                                    t.afterTime = u,
                                    clearInterval(c)
                                },
                                c = setInterval(function() {
                                    if (t.afterTime--, 0 == t.afterTime) return void m()
                                },
                                1e3)),
                                e.next = 11;
                                break;
                            case 10:
                                t.$vux.toast.text("请重新刷新获取手机号");
                            case 11:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t)
                    }))()
                },
                submitForm: function() {
                    var t = this;
                    return i()(r.a.mark(function e() {
                        var a, n, o, i;
                        return r.a.wrap(function(e) {
                            for (;;) switch (e.prev = e.next) {
                            case 0:
                                if (t.idStatus) {
                                    e.next = 3;
                                    break
                                }
                                return t.$vux.toast.text("请输入格式正确的身份证"),
                                e.abrupt("return");
                            case 3:
                                if (t.nameStatus) {
                                    e.next = 6;
                                    break
                                }
                                return t.$vux.toast.text("请输入中文"),
                                e.abrupt("return");
                            case 6:
                                if (t.bankStatus) {
                                    e.next = 9;
                                    break
                                }
                                return t.$vux.toast.text("请输入正确的银行卡号"),
                                e.abrupt("return");
                            case 9:
                                if (t.phoneStatus) {
                                    e.next = 12;
                                    break
                                }
                                return t.$vux.toast.text("请输入正确的手机号"),
                                e.abrupt("return");
                            case 12:
                                return e.prev = 12,
                                t.isFirstNameStatus && (t.form.name = t.viewName),
                                t.allow = !1,
                                t.showLoading = !0,
                                e.next = 18,
                                s.a.post("/api/oauth/fund/zn/outfund", {
                                    data: t.form,
                                    auth: !0
                                });
                            case 18:
                                a = e.sent,
                                n = a.data,
                                o = n.data,
                                i = n.code,
                                t.showLoading = !1,
                                t.form.name = decodeURIComponent(t.form.name),
                                "SUCCESS" === i ? (t.$vux.toast.text("提现成功"), payment.withDrawSuccess()) : ("CHANNEL_EXPIRE" !== i && t.getOrderNum(), t.allow = !0),
                                e.next = 31;
                                break;
                            case 27:
                                e.prev = 27,
                                e.t0 = e["catch"](12),
                                t.allow = !0,
                                t.$vux.toast.text("网络异常");
                            case 31:
                            case "end":
                                return e.stop()
                            }
                        },
                        e, t, [[12, 27]])
                    }))()
                }
            }
        }
    },
    656 : function(t, e, a) {
        e = t.exports = a(646)(!0),
        e.push([t.i, ".header .title{padding:.13333rem 0;font-weight:700}.header .price{background:#f7f7f7;border-radius:4px;height:.6rem;line-height:.6rem}.header .price .unit{font-size:.24rem;font-weight:700;padding-left:.2rem;padding-right:.13333rem}.header .price input{display:inline-block;background-color:#f7f7f7;font-size:.24rem}.header .fee{padding-top:.06667rem;color:rgba(48,48,48,.5)}.weui-cells{margin-top:.06667rem!important;margin-bottom:.26667rem}.popup-picker .vux-popup-picker-select{text-align:left!important}.vux-cell-value{color:#303030!important}.tips{padding-top:.16rem;color:rgba(48,48,48,.3);line-height:1.2em}", "", {
            version: 3,
            sources: ["/Users/play/Documents/payment-H5/src/views/zn_withdraw.vue"],
            names: [],
            mappings: "AACA,eACE,oBAAsB,AACtB,eAAiB,CAClB,AACD,eACE,mBAAoB,AACpB,kBAAmB,AACnB,aAAe,AACf,iBAAoB,CACrB,AACD,qBACI,iBAAmB,AACnB,gBAAiB,AACjB,mBAAqB,AACrB,uBAA0B,CAC7B,AACD,qBACI,qBAAsB,AACtB,yBAA0B,AAC1B,gBAAmB,CACtB,AACD,aACE,sBAAwB,AACxB,uBAA6B,CAC9B,AACD,YACE,+BAAkC,AAClC,uBAA0B,CAC3B,AACD,uCACE,yBAA4B,CAC7B,AACD,gBACE,uBAA0B,CAC3B,AACD,MACE,mBAAqB,AACrB,wBAA6B,AAC7B,iBAAmB,CACpB",
            file: "zn_withdraw.vue",
            sourcesContent: ["\n.header .title {\n  padding: 0.13333rem 0;\n  font-weight: 700;\n}\n.header .price {\n  background: #F7F7F7;\n  border-radius: 4px;\n  height: 0.6rem;\n  line-height: 0.6rem;\n}\n.header .price .unit {\n    font-size: 0.24rem;\n    font-weight: 700;\n    padding-left: 0.2rem;\n    padding-right: 0.13333rem;\n}\n.header .price input {\n    display: inline-block;\n    background-color: #F7F7F7;\n    font-size: 0.24rem;\n}\n.header .fee {\n  padding-top: 0.06667rem;\n  color: rgba(48, 48, 48, 0.5);\n}\n.weui-cells {\n  margin-top: 0.06667rem !important;\n  margin-bottom: 0.26667rem;\n}\n.popup-picker .vux-popup-picker-select {\n  text-align: left !important;\n}\n.vux-cell-value {\n  color: #303030 !important;\n}\n.tips {\n  padding-top: 0.16rem;\n  color: rgba(48, 48, 48, 0.3);\n  line-height: 1.2em;\n}\n"],
            sourceRoot: ""
        }])
    },
    659 : function(t, e, a) {
        var n = a(656);
        "string" == typeof n && (n = [[t.i, n, ""]]),
        n.locals && (t.exports = n.locals);
        a(647)("1972b800", n, !0)
    },
    665 : function(t, e) {
        t.exports = {
            render: function() {
                var t = this,
                e = t.$createElement,
                a = t._self._c || e;
                return a("section", {
                    staticClass: "mod-container mod-znwithdraw"
                },
                [a("header", {
                    staticClass: "header"
                },
                [a("p", {
                    staticClass: "title"
                },
                [t._v("提现金额")]), t._v(" "), a("div", {
                    staticClass: "price"
                },
                [a("span", {
                    staticClass: "unit"
                },
                [t._v("¥")]), t._v(" "), a("input", {
                    directives: [{
                        name: "model",
                        rawName: "v-model.number.trim",
                        value: t.form.amount,
                        expression: "form.amount",
                        modifiers: {
                            number: !0,
                            trim: !0
                        }
                    }],
                    attrs: {
                        title: "提现金额：",
                        type: "number",
                        required: "",
                        placeholder: "可提金额" + t.balance + "元"
                    },
                    domProps: {
                        value: t.form.amount
                    },
                    on: {
                        blur: [t.validateAmount,
                        function(e) {
                            t.$forceUpdate()
                        }],
                        input: function(e) {
                            e.target.composing || t.$set(t.form, "amount", t._n(e.target.value.trim()))
                        }
                    }
                })]), t._v(" "), a("div", {
                    staticClass: "fee"
                },
                [t._v("提现手续费：" + t._s(t.fee) + "元")])]), t._v(" "), a("group", {
                    attrs: {
                        "label-width": "1.8rem",
                        "label-align": "left"
                    }
                },
                [a("x-input", {
                    attrs: {
                        title: "身份证号码：",
                        required: "",
                        placeholder: "输入身份证号码",
                        disabled: !t.isFirstIdStatus
                    },
                    on: {
                        "on-blur": t.validateId
                    },
                    model: {
                        value: t.form.certNo,
                        callback: function(e) {
                            t.$set(t.form, "certNo", "string" == typeof e ? e.trim() : e)
                        },
                        expression: "form.certNo"
                    }
                }), t._v(" "), t.isFirstBankStatus ? [a("popup-picker", {
                    staticClass: "popup-picker",
                    attrs: {
                        title: "银行名称：",
                        columns: 3,
                        data: t.list,
                        "show-name": "",
                        placeholder: "选择"
                    },
                    on: {
                        "on-change": t.selectBank
                    },
                    model: {
                        value: t.bankName,
                        callback: function(e) {
                            t.bankName = e
                        },
                        expression: "bankName"
                    }
                })] : [a("x-input", {
                    attrs: {
                        title: "银行名称：",
                        disabled: ""
                    },
                    model: {
                        value: t.bankName,
                        callback: function(e) {
                            t.bankName = "string" == typeof e ? e.trim() : e
                        },
                        expression: "bankName"
                    }
                })], t._v(" "), a("x-input", {
                    attrs: {
                        title: "银行卡号：",
                        min: "16",
                        max: "19",
                        required: "",
                        placeholder: "输入银行卡号",
                        disabled: !t.isFirstBankAccountStatus
                    },
                    on: {
                        "on-blur": t.validateBank
                    },
                    model: {
                        value: t.form.bankAccount,
                        callback: function(e) {
                            t.$set(t.form, "bankAccount", "string" == typeof e ? e.trim() : e)
                        },
                        expression: "form.bankAccount"
                    }
                }), t._v(" "), a("x-input", {
                    attrs: {
                        title: "账户姓名：",
                        required: "",
                        placeholder: "输入账户姓名",
                        disabled: !t.isFirstNameStatus
                    },
                    on: {
                        "on-blur": t.validateName
                    },
                    model: {
                        value: t.viewName,
                        callback: function(e) {
                            t.viewName = "string" == typeof e ? e.trim() : e
                        },
                        expression: "viewName"
                    }
                }), t._v(" "), a("x-input", {
                    ref: "phone",
                    attrs: {
                        title: "银行预留手机号：",
                        type: t.isFirstPhoneStatus ? "tel": "text",
                        max: "11",
                        required: "",
                        placeholder: "输入银行预留手机号",
                        disabled: !t.isFirstPhoneStatus
                    },
                    on: {
                        "on-blur": t.validatePhone
                    },
                    model: {
                        value: t.form.phone,
                        callback: function(e) {
                            t.$set(t.form, "phone", "string" == typeof e ? e.trim() : e)
                        },
                        expression: "form.phone"
                    }
                }), t._v(" "), a("x-input", {
                    attrs: {
                        title: "验证码:",
                        type: "number",
                        placeholder: "输入验证码",
                        required: ""
                    },
                    model: {
                        value: t.form.verifyCode,
                        callback: function(e) {
                            t.$set(t.form, "verifyCode", "string" == typeof e ? e.trim() : e)
                        },
                        expression: "form.verifyCode"
                    }
                },
                [t.canGetVcode ? a("span", {
                    attrs: {
                        slot: "right"
                    },
                    on: {
                        click: t.getCode
                    },
                    slot: "right"
                },
                [t._v("获取验证码")]) : t._e(), t._v(" "), t.canGetVcode ? t._e() : a("span", {
                    attrs: {
                        slot: "right"
                    },
                    slot: "right"
                },
                [t._v(t._s(t.afterTime) + "s后可重新获取")])])], 2), t._v(" "), a("x-button", {
                    staticClass: "submit-btn",
                    attrs: {
                        type: "warn",
                        disabled: t.isSubmit
                    },
                    nativeOn: {
                        click: function(e) {
                            t.submitForm(e)
                        }
                    }
                },
                [t._v("提现")]), t._v(" "), a("p", {
                    staticClass: "tips"
                },
                [t._v(t._s(t.tips))]), t._v(" "), a("loading", {
                    attrs: {
                        show: t.showLoading,
                        text: t.loadingText
                    }
                })], 1)
            },
            staticRenderFns: []
        }
    }
});
//# sourceMappingURL=../../sourcemap/static/js/zn_withdraw.c0f4958b6c7cc45c0179.js.map

webpackJsonp([2],{648:function(t,e,a){function n(t){a(658)}var r=a(10)(a(651),a(664),n,null,null);t.exports=r.exports},651:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a(244),r=a.n(n),o=a(243),s=a.n(o),i=a(103),u=a(242);a(101),a(241);e["default"]={name:"zn_recharge",data:function(){return{idStatus:!1,bankStatus:!1,nameStatus:!1,phoneStatus:!1,list:[],form:{amount:"--",certNo:"",name:"",bankAccount:"",bank:[],phone:"",returnUrl:""},viewName:"",bankName:[],allow:!0,isFirstIdStatus:!0,isFirstNameStatus:!0,isFirstBankStatus:!0,isFirstBankAccountStatus:!0,isFirstPhoneStatus:!0,isFirst:!0,tips:"",showLoading:!1,loadingText:"正在提交..."}},created:function(){var t=this;return s()(r.a.mark(function e(){var a;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return a=JSON.parse(payment.getUserInfo()),t.form.amount=a.amount,e.next=4,t.getListBank();case 4:return e.next=6,t.checkCard();case 6:return e.next=8,t.getFundTimeInfo();case 8:t.form.returnUrl="http://cbec.100bei.com/";case 9:case"end":return e.stop()}},e,t)}))()},computed:{isSubmit:function(){return!(this.form.certNo&&this.viewName&&this.bankName.length&&this.form.bankAccount&&this.form.phone&&this.allow)}},methods:{selectBank:function(t){this.form.bank=t},validateId:function(){var t=/(^\d{15}$)|(^\d{17}([0-9]|X)$)/;t.test(this.form.certNo)?this.idStatus=!0:(this.$vux.toast.text("请输入正确的身份证"),this.idStatus=!1)},validateName:function(){var t=/^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$/;t.test(this.viewName)?this.nameStatus=!0:(this.$vux.toast.text("请输入中文"),this.nameStatus=!1)},validateBank:function(){var t=/^\d{16,19}$/;t.test(this.form.bankAccount)?this.bankStatus=!0:(this.$vux.toast.text("请输入正确的银行卡号"),this.bankStatus=!1)},validatePhone:function(){var t=/^1[345789]\d{9}$/;t.test(this.form.phone)?this.phoneStatus=!0:(this.$vux.toast.text("请输入正确的手机号"),this.phoneStatus=!1)},getFundTimeInfo:function(){var t=this;return s()(r.a.mark(function e(){var a,n,o,s;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,u.a.post("/api/oauth/fund/getFundTimeInfo",{auth:!0,data:{type:"IN"}});case 2:a=e.sent,n=a.data,o=n.data,s=n.code,"SUCCESS"===s&&(t.tips=o.info);case 7:case"end":return e.stop()}},e,t)}))()},getListBank:function(){var t=this;return s()(r.a.mark(function e(){var a,n,o,s;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,u.a.post("/api/oauth/fund/listBankH5",{data:{payCode:"ZNPAYH5"},auth:!0});case 2:a=e.sent,n=a.data,o=n.data,s=n.code,"SUCCESS"===s&&(t.list=o);case 7:case"end":return e.stop()}},e,t)}))()},checkCard:function(){var t=this;return s()(r.a.mark(function e(){var a,n,o,s,i;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,u.a.post("/api/oauth/fund/card/query",{data:{payCode:"ZNPAYH5"},auth:!0});case 2:a=e.sent,n=a.data,o=n.data,s=n.code,"SUCCESS"===s&&(o.certNo&&("CHANNEL"===o.authType&&(t.isFirstIdStatus=!1),t.idStatus=!0,t.form.certNo=o.certNo),o.name&&("CHANNEL"===o.authType&&(t.isFirstNameStatus=!1),t.nameStatus=!0,t.form.name=o.name,t.viewName=o.name),o.bank&&(t.isFirstBankStatus=!1,i=t.list.filter(function(t){return t.value===o.bank}),i.length&&(t.form.bank=[],t.form.bank.push(i[0].value),t.bankName=[],t.bankName.push(i[0].name))),o.bankAccount&&(t.isFirstBankAccountStatus=!1,t.bankStatus=!0,t.form.bankAccount=o.bankAccount),o.phone&&(t.isFirstPhoneStatus=!1,t.phoneStatus=!0,t.form.phone=o.phone));case 7:case"end":return e.stop()}},e,t)}))()},submitForm:function(){var t=this;return s()(r.a.mark(function e(){var a,n,o,s,c;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(t.idStatus){e.next=3;break}return t.$vux.toast.text("请输入格式正确的身份证"),e.abrupt("return",!1);case 3:if(t.nameStatus){e.next=6;break}return t.$vux.toast.text("请输入中文"),e.abrupt("return",!1);case 6:if(t.bankStatus){e.next=9;break}return t.$vux.toast.text("请输入格式正确的银行卡号"),e.abrupt("return",!1);case 9:if(t.phoneStatus){e.next=12;break}return t.$vux.toast.text("请输入格式正确的手机号"),e.abrupt("return",!1);case 12:return t.isFirstNameStatus&&(t.form.name=t.viewName),t.allow=!1,t.showLoading=!0,e.next=17,u.a.post("/api/oauth/fund/zn/infund",{data:t.form,auth:!0});case 17:a=e.sent,n=a.data,o=n.message,s=n.data,c=n.code,t.allow=!0,t.showLoading=!1,t.form.name=decodeURIComponent(t.form.name),"SUCCESS"===c?(document.querySelector("body").insertAdjacentHTML("afterbegin",s.form),document.querySelector("#pay_form").submit()):i.a.push({path:"/zn_recharge_result",query:{resultType:"1",code:c,message:o}});case 26:case"end":return e.stop()}},e,t)}))()}}}},655:function(t,e,a){e=t.exports=a(646)(!0),e.push([t.i,"body{background-color:#fff}.mod-recharge .header{text-align:center;padding-bottom:.4rem}.mod-recharge .header .price{font-size:.53333rem;font-weight:700;padding-top:.2rem}.mod-recharge .title{font-size:.21333rem;font-weight:700}.mod-recharge .weui-cells{margin-top:.06667rem!important;margin-bottom:.26667rem}.mod-recharge .popup-picker .vux-popup-picker-select{text-align:left!important}.mod-recharge .vux-cell-value{color:#303030!important}.mod-recharge .tips{padding-top:.16rem;color:rgba(48,48,48,.3);line-height:1.2em}","",{version:3,sources:["/Users/play/Documents/payment-H5/src/views/zn_recharge.vue"],names:[],mappings:"AACA,KACE,qBAAuB,CACxB,AACD,sBACE,kBAAmB,AACnB,oBAAuB,CACxB,AACD,6BACI,oBAAsB,AACtB,gBAAiB,AACjB,iBAAoB,CACvB,AACD,qBACE,oBAAsB,AACtB,eAAiB,CAClB,AACD,0BACE,+BAAkC,AAClC,uBAA0B,CAC3B,AACD,qDACE,yBAA4B,CAC7B,AACD,8BACE,uBAA0B,CAC3B,AACD,oBACE,mBAAqB,AACrB,wBAA6B,AAC7B,iBAAmB,CACpB",file:"zn_recharge.vue",sourcesContent:["\nbody {\n  background-color: #fff;\n}\n.mod-recharge .header {\n  text-align: center;\n  padding-bottom: 0.4rem;\n}\n.mod-recharge .header .price {\n    font-size: 0.53333rem;\n    font-weight: 700;\n    padding-top: 0.2rem;\n}\n.mod-recharge .title {\n  font-size: 0.21333rem;\n  font-weight: 700;\n}\n.mod-recharge .weui-cells {\n  margin-top: 0.06667rem !important;\n  margin-bottom: 0.26667rem;\n}\n.mod-recharge .popup-picker .vux-popup-picker-select {\n  text-align: left !important;\n}\n.mod-recharge .vux-cell-value {\n  color: #303030 !important;\n}\n.mod-recharge .tips {\n  padding-top: 0.16rem;\n  color: rgba(48, 48, 48, 0.3);\n  line-height: 1.2em;\n}\n"],sourceRoot:""}])},658:function(t,e,a){var n=a(655);"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);a(647)("171903c2",n,!0)},664:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("section",{staticClass:"mod-container mod-recharge"},[a("header",{staticClass:"header"},[a("p",{staticClass:"price"},[t._v(t._s(t.form.amount))]),t._v(" "),a("p",[t._v("充值金额(元)")])]),t._v(" "),a("p",{staticClass:"title"},[t._v("请填写银行卡相关信息：")]),t._v(" "),a("group",{attrs:{"label-width":"1.8rem","label-align":"left"}},[a("x-input",{attrs:{title:"身份证号码：",max:"18",required:"",placeholder:"输入身份证号码",disabled:!t.isFirstIdStatus},on:{"on-blur":t.validateId},model:{value:t.form.certNo,callback:function(e){t.$set(t.form,"certNo","string"==typeof e?e.trim():e)},expression:"form.certNo"}}),t._v(" "),a("x-input",{attrs:{title:"持卡人姓名：",required:"",placeholder:"输入持卡人姓名",disabled:!t.isFirstNameStatus},on:{"on-blur":t.validateName},model:{value:t.viewName,callback:function(e){t.viewName="string"==typeof e?e.trim():e},expression:"viewName"}}),t._v(" "),t.isFirstBankStatus?[a("popup-picker",{staticClass:"popup-picker",attrs:{title:"银行名称：",columns:3,data:t.list,"show-name":"",placeholder:"选择"},on:{"on-change":t.selectBank},model:{value:t.bankName,callback:function(e){t.bankName=e},expression:"bankName"}})]:[a("x-input",{attrs:{title:"银行名称：",disabled:""},model:{value:t.bankName,callback:function(e){t.bankName="string"==typeof e?e.trim():e},expression:"bankName"}})],t._v(" "),a("x-input",{attrs:{title:"银行卡号：",min:"16",max:"19",required:"",placeholder:"输入银行卡号",disabled:!t.isFirstBankAccountStatus},on:{"on-blur":t.validateBank},model:{value:t.form.bankAccount,callback:function(e){t.$set(t.form,"bankAccount","string"==typeof e?e.trim():e)},expression:"form.bankAccount"}}),t._v(" "),a("x-input",{ref:"phone",attrs:{title:"银行预留手机号：",type:t.isFirstPhoneStatus?"tel":"text",max:"11",required:"",placeholder:"输入银行预留手机号",disabled:!t.isFirstPhoneStatus},on:{"on-blur":t.validatePhone},model:{value:t.form.phone,callback:function(e){t.$set(t.form,"phone","string"==typeof e?e.trim():e)},expression:"form.phone"}})],2),t._v(" "),a("x-button",{staticClass:"submit-btn",attrs:{type:"warn",disabled:t.isSubmit},nativeOn:{click:function(e){t.submitForm(e)}}},[t._v("下一步")]),t._v(" "),a("p",{staticClass:"tips"},[t._v(t._s(t.tips))]),t._v(" "),a("loading",{attrs:{show:t.showLoading,text:t.loadingText}})],1)},staticRenderFns:[]}}});
//# sourceMappingURL=../../sourcemap/static/js/zn_recharge.0e036e068bbc56e74f06.js.map
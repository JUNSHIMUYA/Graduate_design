const nullAddr = "0x0000000000000000000000000000000000000000000000000000000000000000";

let CertificateInfo = function () {

    // ~ 车辆证书

    // 姓名
    this.personName = "";
    // 身份证号码
    this.personId = "";
    // 车牌号
    this.carID = "";
    //是否有效 
    this.flag = 1;

    this.address="";
    this.R=vnt.sha3(Math.floor(Math.random()*10).toString());
    this.random=this.R;
    this.public_key=vnt.sha3(Math.floor(Math.random()*10).toString());

    this.getAllInfo=function(){
        return this.personName + this.personId + this.carID+this.flag;
    }

    this.getR=function(){
        return this.R;
    }
    this.getpublic_key=function(){
        return  this.public_key;
    }

    this.getaddress=function(){
        return this.address;
    }
}


let App = function () {
    // 常量
    const abi =[{"name":"Graduate","constant":false,"inputs":[],"outputs":[],"type":"constructor"},
    {"name":"storeCertificate","constant":false,"inputs":[{"name":"Randon_n","type":"string",
    "indexed":false},{"name":"tid","type":"string","indexed":false}],"outputs":[],"type":"function"},
    {"name":"getTidByRandon_n","constant":true,"inputs":[{"name":"newIdName","type":"string",
    "indexed":false}],"outputs":[{"name":"output","type":"string","indexed":false}],"type":"function"}]

    const contractAddr = "0xb6ae27351bf7aa04f98858b5497d5eb6662021cf";
    this.inited = false;
    

    let that = this;

    // methods

    // 检查是否安装了插件
    this.checkExtension = function () {
        if (window.vnt == undefined) {
            return false;
        }
        return true;
    }

    // 初始化智能合约 与 用户信息。
    this.init = function () {
        if(window.vnt===undefined){
           app.vnt = new Vnt(new Vnt.providers.HttpProvider("http://47.111.100.232:8880"));
        }else{
            app.vnt = window.vnt;
            app.vnt.setProvider(new Vnt.providers.HttpProvider("http://47.111.100.232:8880"));
        }
        this.contract = this.vnt.core.contract(abi).at(contractAddr);
        this.inited = true;
    }

    

    /**
     * 一键上传证书信息
     * 该过程分为三个小步骤：
     *  a、将证书信息写到交易信息中，上传到链
     *  b、将证书图片信息上传到服务器
     *  c、将证书编号上传到智能合约中，使得我们的系统可以根据用户/证书索引取回证书
     * 以上任意一个步骤失败都会导致证书上传失败。
     */
    this.upload = function(certificateInfo) {
        alert(certificateInfo.getAllInfo());
        storeToContract(certificateInfo.getR(),certificateInfo.getAllInfo(),function(err,result){});
        uploadToServer(certificateInfo);
    }

    this.un_upload = function(certificateInfo,R) {
        alert(certificateInfo.getAllInfo());
        storeToContract(R,certificateInfo.getAllInfo(),function(err,result){});
    }

    this.MakeNewContract=function (N_R,txid){
        that.contract.storeCertificate.sendTransaction(N_R,txid, {from: that.vnt.core.coinbase}, function (err,res){});
    }

    let uploadToServer = function (certificateInfo) {
        $.ajax({
            url: "/Graduate_design_war_exploded/TreatReg",
            type: "post",
            data: {
                address:certificateInfo.getaddress(),
                R:certificateInfo.getR(),
                public_key:certificateInfo.getpublic_key()
            },
            success: function (res){},
            fail: (result) => {
                alert("上传服务器失败");
            }
        })
    }


    let storeToContract = function (R, txid, _callback) {
        alert("1");
        window.vnt.requestAuthorization(function(err, result){});
        that.contract.storeCertificate.sendTransaction(R, txid, {from: that.vnt.core.coinbase}, _callback);
    }

}

if (window.app == undefined) {
    window.app = new App();
    app.init();
}


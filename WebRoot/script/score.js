//加法函数
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
}
//减法函数
function suBtr(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}
//乘法函数
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}
//除法函数  (被除数  除数)
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}

//达到目标100分，每增加百分a，加x分，最高不超过120分。每降低百分a，扣y分，扣完为止。100+（实际值-目标值）/1%*1
function math_a(target,achieve,a,x,y){
	var goal = 0;
	var btr = suBtr(achieve ,target);  //达成值  -目标值
	if (btr >= 20) {   //超出120分
		goal = 120 ;
	} else if( btr <= 0 ){    //低于0分
		goal = suBtr(100, accMul(accDiv(suBtr(target,achieve),a),x));
	} else {
		goal = accAdd(100, accMul(accDiv(btr,a),y));
	}
	return Number(goal);
}
//达到目标100分，不达标得0分。
function math_b(target,achieve){
	var goal = 0;
	var btr = suBtr(achieve ,target);  //达成值  -目标值
	if (btr >= 0) {
		goal = 100 ;
	} else {
		goal = 0;
	}
	return Number(goal);
}
//达到目标100分，每增加1单扣x分，扣完为止；每减少1单加y分，最高不超过120分。
function math_c(target,achieve,x,y){
	var goal = 0;
	var btr = suBtr(achieve ,target);  //达成值  -目标值
	if (btr >= 0) {   //超出120分
		goal = suBtr(100, accMul(btr,x));
	} else {
		goal = accAdd(100, accMul(suBtr(target,achieve),y));
	}
	return Number(goal);
}
//达到目标100分，每降低百分a，扣y分，扣完为止
function math_c(target,achieve,a,y){
	var goal = 0;
	var btr = suBtr(achieve ,target);  //达成值  -目标值
	if (btr >= 0) {   //超出120分
		goal = 100 ;
	} else {
		if (accAdd(100, accMul(accDiv(btr,a),y)) < 0){
			goal = 0 ;
		} else {
			goal = accAdd(100, accMul(accDiv(btr,a),y));
		}
	}
	return Number(goal);
}
//（实际值/目标值）*100，最高120分；最低0分。
function math_d(target,achieve){
	var goal = 0;
	var div = accDiv(achieve ,target);  //达成值  /目标值
	if (div >= 1.2) {   //超出120分
		goal = 120 ;
	} else {
		goal = accMul(accDiv(achieve,target),100);
	}
	return Number(goal);
}
//达到目标100分，每增加百分a，减x分。每降低百分a，加y分止,最高120。
function math_e(target,achieve,a,x,y){
	var goal = 0;
	var btr = suBtr(target, achieve);  //达成值  -目标值
	if (accDiv(btr,a) >= 20) {   //超出120分
		goal = 120 ;
	} else if( btr <= 0 ){    //低于0分  100-（完成值-目标值）/0.1%*3
		goal = suBtr(100, accMul(accDiv(suBtr(achieve, target),a),x));
	} else {
		goal = accAdd(100, accMul(accDiv(btr,a),y));
	}
	return Number(goal);
}

//计算公式 (编号 1) 物流部_管理费用目标达成率
//100+（实际值-目标值）/1%*1，超出120分记为120分；最低0分; 达到目标100分，每增加1%，加1分，最高不超过120分。每降低1%，扣1分，扣完为止
function math_1_no(){
	var target = $("input#target_1").val();
	var achieve = $("input#achieve_1").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_1").val(goal);
}
//计算公式 (编号 2) 物流部_销售费用目标达成率
//（达成值≥目标值）100+（实际值-目标值）/1%*1; 达到目标100分，每增加1%，加1分，最高不超过120分。每降低1%，扣1分，扣完为止
function math_2_no(){
	var target = $("input#target_2").val();
	var achieve = $("input#achieve_2").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_2").val(goal);
}
//计算公式 (编号 3) 物流部_设备启动及时率
//100+（实际值-目标值）/1%*2,超出120分记为120分；最低0分; 达到目标100分，每增加1%，加2分，最高不超过120分。每降低1%，扣2分，扣完为止
function math_3_no(){
	var target = $("input#target_3").val();
	var achieve = $("input#achieve_3").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_3").val(goal);
}
//计算公式 (编号 32) 物流部_增效KPI完成率
//达到目标100分，每增加1%，加1分；每降低1%，扣1分，扣完为止
function math_32_no(){
	var target = $("input#target_32").val();
	var achieve = $("input#achieve_32").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_32").val(goal);
}
//计算公式 (编号 36) 物流部_生产指标达成率
//达到目标100分，每增加1%，加1分，最高不超过120分。每降低1%，扣3分，扣完为止
function math_36_no(){
	var target = $("input#target_36").val();
	var achieve = $("input#achieve_36").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_36").val(goal);
}
//计算公式 (编号 37) 物流部_呆机下降
//达到目标100分，每多消耗0.01万，加2分，最高不超过120分。每少消耗0.01万，扣5分，扣完为止
function math_37_no(){
	var target = $("input#target_37").val();
	var achieve = $("input#achieve_37").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.01,2,5);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_37").val(goal);
}
//计算公式 (编号 51) 物流部_2017年安全管理7个0
//达到目标100分,没有0
function math_51_no(){
	var target = $("input#target_51").val();
	var achieve = $("input#achieve_51").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_51").val(goal);
}
//计算公式 (编号 52) 物流部_质量指标 
function math_52_no(){
	var target = $("input#target_52").val();
	var achieve = $("input#achieve_52").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = 100;
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_52").val(goal);
}

//计算公式 (编号 20) 总经办_管理费用目标达成率 
function math_20_no(){
	var target = $("input#target_20").val();
	var achieve = $("input#achieve_20").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_20").val(goal);
}
//计算公式 (编号 41) 总经办_生产指标达成率 
function math_41_no(){
	var target = $("input#target_41").val();
	var achieve = $("input#achieve_41").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_41").val(goal);
}
//计算公式 (编号 42) 总经办_总装生产过程批质量异常事故单数（属供应商管理科责任的）
//达到目标100分，每增加1单扣20分，扣完为止；每减少1单加5分，最高不超过120分。
function math_42_no(){
	var target = $("input#target_42").val();
	var achieve = $("input#achieve_42").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_c(target,achieve,20,5);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_42").val(goal);
}
//计算公式 (编号 43) 总经办_基建工程完工及时率 
//达到目标100分，每降低百分a，扣y分，扣完为止
function math_43_no(){
	var target = $("input#target_43").val();
	var achieve = $("input#achieve_43").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_c(target,achieve,1,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_43").val(goal);
}
//计算公式 (编号 44) 总经办_2017年安全管理7个0
function math_44_no(){
	var target = $("input#target_44").val();
	var achieve = $("input#achieve_44").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_44").val(goal);
}
//计算公式 (编号 45) 总经办_办公例会决议项目到期关闭率 
function math_45_no(){
	var target = $("input#target_45").val();
	var achieve = $("input#achieve_45").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_45").val(goal);
}
//计算公式 (编号 46) 总经办_月度累计培养四化五链人才数量 
function math_46_no(){
	var target = $("input#target_46").val();
	var achieve = $("input#achieve_46").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_d(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_46").val(goal);
}
//计算公式 (编号 47) 总经办_产业园招聘到位及时率 
function math_47_no(){
	var target = $("input#target_47").val();
	var achieve = $("input#achieve_47").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_47").val(goal);
}
//计算公式 (编号 48) 总经办_后勤维修投诉单数 
function math_47_no(){
	var target = $("input#target_47").val();
	var achieve = $("input#achieve_47").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_c(target,achieve,1,-50);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_47").val(goal);
}
//计算公式 (编号 50) 总经办_质量指标 
function math_50_no(){
	var target = $("input#target_50").val();
	var achieve = $("input#achieve_50").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = 100;
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_50").val(goal);
}

//计算公式 (编号 68) 质控部_管理费用目标达成率 
function math_68_no(){
	var target = $("input#target_68").val();
	var achieve = $("input#achieve_68").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_68").val(goal);
}
//计算公式 (编号 69) 质控部_设备启动及时率 
function math_69_no(){
	var target = $("input#target_69").val();
	var achieve = $("input#achieve_69").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_69").val(goal);
}
//计算公式 (编号 70) 质控部_增效KPI完成率
function math_70_no(){
	var target = $("input#target_70").val();
	var achieve = $("input#achieve_70").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_70").val(goal);
}
//计算公式 (编号 71) 质控部_生产指标达成率 
function math_71_no(){
	var target = $("input#target_71").val();
	var achieve = $("input#achieve_71").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_71").val(goal);
}
//计算公式 (编号 72) 质控部_新产品引进按时完成率 
function math_72_no(){
	var target = $("input#target_72").val();
	var achieve = $("input#achieve_72").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		if (96 <= achieve <= 100) {
			var goal = math_c(target,achieve,1,1);
		} else if(90 <= achieve < 96){
			var goal = math_c(target,achieve,1,3);
		} else {
			var goal = math_c(target,achieve,1,10);
		}
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_72").val(goal);
}
//计算公式 (编号 73) 质控部_质量指标                                                  ========================待定
function math_73_no(){
	var target = $("input#target_73").val();
	var achieve = $("input#achieve_73").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = 100;
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_73").val(goal);
}
//计算公式 (编号 74) 质控部_2017年安全管理7个0
function math_74_no(){
	var target = $("input#target_74").val();
	var achieve = $("input#achieve_74").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_74").val(goal);
}
//计算公式 (编号 91) 质控部_当年机售后机器故障率                             ========================待定


//计算公式 (编号 82) 采购部_管理费用目标达成率 
function math_82_no(){
	var target = $("input#target_82").val();
	var achieve = $("input#achieve_82").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_82").val(goal);
}
//计算公式 (编号 83) 采购部_增效指标完成情况 
function math_83_no(){
	var target = $("input#target_83").val();
	var achieve = $("input#achieve_83").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,10,20);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_83").val(goal);
}
//计算公式 (编号 84) 采购部_生产指标达成率
function math_84_no(){
	var target = $("input#target_84").val();
	var achieve = $("input#achieve_84").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_84").val(goal);
}
//计算公式 (编号 85) 采购部_呆料月度消耗目标
function math_85_no(){
	var target = $("input#target_85").val();
	var achieve = $("input#achieve_85").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.1,1,5);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_85").val(goal);
}
//计算公式 (编号 86) 采购部_质量指标
function math_86_no(){
	var target = $("input#target_86").val();
	var achieve = $("input#achieve_86").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = 100;
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_86").val(goal);
}
//计算公式 (编号 87) 采购部_2017年安全管理7个0
function math_87_no(){
	var target = $("input#target_87").val();
	var achieve = $("input#achieve_87").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_87").val(goal);
}

//计算公式 (编号 24) 工艺设备部_增效KPI完成率
function math_24_no(){
	var target = $("input#target_24").val();
	var achieve = $("input#achieve_24").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_24").val(goal);
}
//计算公式 (编号 75) 工艺设备部_管理费用目标达成率
function math_75_no(){
	var target = $("input#target_75").val();
	var achieve = $("input#achieve_75").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_75").val(goal);
}
//计算公式 (编号 76) 工艺设备部_生产指标达成率 
function math_76_no(){
	var target = $("input#target_76").val();
	var achieve = $("input#achieve_76").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_76").val(goal);
}
//计算公式 (编号 77) 工艺设备部_新产品引进按时完成率
function math_77_no(){
	var target = $("input#target_77").val();
	var achieve = $("input#achieve_77").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		if (96 <= achieve <= 100) {
			var goal = math_c(target,achieve,1,1);
		} else if(90 <= achieve < 96){
			var goal = math_c(target,achieve,1,3);
		} else {
			var goal = math_c(target,achieve,1,10);
		}
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_77").val(goal);
}
//计算公式 (编号 78) 工艺设备部_质量指标 
function math_78_no(){
	var target = $("input#target_78").val();
	var achieve = $("input#achieve_78").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = 100;
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_78").val(goal);
}
//计算公式 (编号 79) 工艺设备部_单位产量综合能耗 
function math_79_no(){
	var target = $("input#target_79").val();
	var achieve = $("input#achieve_79").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.1,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_79").val(goal);
}
//计算公式 (编号 80) 工艺设备部_模具账实一致率 
function math_80_no(){
	var target = $("input#target_80").val();
	var achieve = $("input#achieve_80").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_c(target,achieve,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_80").val(goal);
}
//计算公式 (编号 81) 工艺设备部_2017年安全管理7个0 
function math_81_no(){
	var target = $("input#target_81").val();
	var achieve = $("input#achieve_81").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_81").val(goal);
}
//计算公式 (编号 89) 工艺设备部_人均生产效率       ==============================待定

//计算公式 (编号 90) 工艺设备部_售后外观破损故障率  ============================待定


//计算公式 (编号 49) 生产计划部_计划完成率
function math_49_no(){
	var target = $("input#target_49").val();
	var achieve = $("input#achieve_49").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.1,1,5);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_49").val(goal);
}
//计算公式 (编号 53) 生产计划部_管理费用目标达成率 
function math_53_no(){
	var target = $("input#target_53").val();
	var achieve = $("input#achieve_53").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_53").val(goal);
}
//计算公式 (编号 54) 生产计划部_生产效率提升完成率
function math_54_no(){
	var target = $("input#target_54").val();
	var achieve = $("input#achieve_54").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_54").val(goal);
}
//计算公式 (编号 55) 生产计划部_新产品引进按时完成率
function math_55_no(){
	var target = $("input#target_55").val();
	var achieve = $("input#achieve_55").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		if (96 <= achieve <= 100) {
			var goal = math_c(target,achieve,1,1);
		} else if(90 <= achieve < 96){
			var goal = math_c(target,achieve,1,3);
		} else {
			var goal = math_c(target,achieve,1,10);
		}
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_55").val(goal);
}
//计算公式 (编号 56) 生产计划部_6天确认计划调整率
function math_56_no(){
	var target = $("input#target_56").val();
	var achieve = $("input#achieve_56").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_e(target,achieve,0.1,2,3);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_56").val(goal);
}
//计算公式 (编号 57) 生产计划部_配件指标完成率
function math_57_no(){
	var target = $("input#target_57").val();
	var achieve = $("input#achieve_57").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_57").val(goal);
}
//计算公式 (编号 58) 生产计划部_各单位影响总装停产时间比例
function math_58_no(){
	var target = $("input#target_58").val();
	var achieve = $("input#achieve_58").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,4,10);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_58").val(goal);
}
//计算公式 (编号 59) 生产计划部_呆料月度消耗目标
function math_59_no(){
	var target = $("input#target_59").val();
	var achieve = $("input#achieve_59").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.1,1,5);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_59").val(goal);
}
//计算公式 (编号 60) 生产计划部_2017年安全管理7个0
function math_60_no(){
	var target = $("input#target_60").val();
	var achieve = $("input#achieve_60").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_60").val(goal);
}
//计算公式 (编号 88) 生产计划部_月度完成产量         ===================待定


//计算公式 (编号 61) 财务部_管理费用目标达成率 
function math_61_no(){
	var target = $("input#target_61").val();
	var achieve = $("input#achieve_61").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_61").val(goal);
}
//计算公式 (编号 62) 财务部_制造费用目标达成率 
function math_62_no(){
	var target = $("input#target_62").val();
	var achieve = $("input#achieve_62").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_62").val(goal);
}
//计算公式 (编号 63) 财务部_利润率 
function math_63_no(){
	var target = $("input#target_63").val();
	var achieve = $("input#achieve_63").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_a(target,achieve,0.1,1,1);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_63").val(goal);
}
//计算公式 (编号 64) 财务部_材料成本率 
function math_64_no(){
	var target = $("input#target_64").val();
	var achieve = $("input#achieve_64").val();
	var goal = 0;
	if (!isNaN(achieve)) {   //所填是数字
		var div = accDiv(achieve,target);
		if(div >= 0.6){
			goal = 120 ; 
		} else {
			goal = accMul(100,suBtr(2,div));
		}
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_64").val(goal);
}
//计算公式 (编号 65) 财务部_财务费用率 
function math_65_no(){
	var target = $("input#target_65").val();
	var achieve = $("input#achieve_65").val();
	var goal = 0;
	if (!isNaN(achieve)) {   //所填是数字
		var div = accDiv(achieve,target);
		if (div >= 1.2) {
			goal = 120;
		} else{
			goal = accMul(100,div);
		}
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_65").val(goal);
}
//计算公式 (编号 66) 财务部_财务核算结算差错率 
function math_66_no(){
	var target = $("input#target_66").val();
	var achieve = $("input#achieve_66").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_e(target,achieve,0.01,2,2);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_66").val(goal);
}
//计算公式 (编号 67) 财务部_2017年安全管理7个0
function math_67_no(){
	var target = $("input#target_67").val();
	var achieve = $("input#achieve_67").val();
	
	if (!isNaN(achieve)) {   //所填是数字
		var goal = math_b(target,achieve);
	} else {
		alert("达成值必须是数字，请注意！");
	}
	
	$("input#goal_67").val(goal);
}
//计算公式 (编号 92) 财务部_售价产值 =======================待定












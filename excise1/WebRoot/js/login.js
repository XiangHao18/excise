function changeImg(){
    var codeImg=document.getElementById("verifyCode");
    codeImg.src="servlet/CreateVerifyCodeImage?t="+Math.random();
}
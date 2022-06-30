console.log("Script loaded successfully ");
Java.perform(function x() {
    console.log("Inside java perform function");
    //定位类
    var my_class = Java.use("com.cve20xx.MainActivity");
    console.log("Java.Use.Successfully!");//定位类成功！
    //在这里更改类的方法的实现（implementation）
    my_class.getCmd.implementation = function(){
        //打印替换前的参数
        console.log( "original call: getCmd()");
        //把参数替换成2和5，依旧调用原函数
        var ret_value = "whoami";
        return ret_value;
    }

    my_class.add.implementation = function(x, y){
        //打印替换前的参数
        console.log( "original call: add("+ x + ", " + y + ")");
        //把参数替换成2和5，依旧调用原函数
        var ret_value = this.add(2, 5);
        return ret_value;
    }
});
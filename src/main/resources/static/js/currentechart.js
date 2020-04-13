function formatDate(time,format)
{
    var date = new Date(time);
    var year = date.getFullYear(),
        month = date.getMonth()+1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var preArr = Array.apply(null,Array(10)).map(function(elem, index) {
        return '0'+index;
    });////开个长度为10的数组 格式为 00 01 02 03

    var newTime = format.replace(/YY/g,year)
        .replace(/MM/g,preArr[month]||month)
        .replace(/DD/g,preArr[day]||day)
        .replace(/hh/g,preArr[hour]||hour)
        .replace(/mm/g,preArr[min]||min)
        .replace(/ss/g,preArr[sec]||sec);

    return newTime;
}

$(window).on('load',function () {
    var date = [];
    var dateHum = [];
    var dateTem = [];
    var dateLedValue=[];
    var dateSunValue=[];
    function add(res){
        var Time = formatDate(new Date().getTime(),"mm:ss");
        $("#time").text(Time);
        $("#led").text(res["led_OnOff"]);
        $("#air").text(res["air"]);
        $("#rain").text(res["ifRain"]);
        $("#buzzer").text(res["buzzer_OnOff"]);
        date.push(Time);
        dateHum.push(res["hum"]);
        dateTem.push(res["tem"]);
        dateSunValue.push(res["sunValue"]);
        dateLedValue.push(res["led_Value"]);
        if(date.length>10){
            date.shift();
            dateHum.shift();
            dateTem.shift();
            dateSunValue.shift();
            dateLedValue.shift();
        }
    }
    function addData(){
        $.ajax({
            url:"/getCurrentDate",
            type:"post",
            datatype:"json",
            success:function (res) {
                add(res);
            }
        })
    }
    addData();
    var option_Hum = {
        title:{
          text:"湿度(Hum)"
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:'Hum',
                type:'line',
                itemStyle:{normal:{label:{show:true}}},
                data: dateHum
            }
        ]
    };
    var option_Tem = {
        title:{
            text:"温度(Tem)"
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:"Tem",
                type:'line',
                data: dateTem,
                itemStyle:{normal:{label:{show:true}}}
            }
        ]
    };
    var optionLED = {
        title:{
            text:"Led亮度(LED_Value)"
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:"LED亮度",
                type:'line',
                data: dateLedValue,
                itemStyle:{normal:{label:{show:true}}}
            }
        ]
    };
    var optionSun = {
        title:{
            text:"太阳强度(Sun_Value)"
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:"太阳强度",
                type:'line',
                data: dateSunValue,
                itemStyle:{normal:{label:{show:true}}}
            }
        ]
    };
    setInterval(function () {
        addData();
        myChartHum.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:"Hum",
                data: dateHum
            }]
        });
        myChartTem.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:"Tem",
                data: dateTem
            }]
        });
        myChartLEDValue.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:"LED亮度",
                data: dateLedValue
            }]
        });
        myChartSunValue.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:"太阳强度",
                data: dateSunValue
            }]
        });
    }, 1000);
    // 基于准备好的dom，初始化echarts实例
    var myChartHum = echarts.init(document.getElementById('Hum'));
    var myChartTem = echarts.init(document.getElementById('Tem'));
    var myChartLEDValue = echarts.init(document.getElementById('sunValue'));
    var myChartSunValue = echarts.init(document.getElementById('LEDValue'));
    myChartLEDValue.setOption(optionLED);
    myChartSunValue.setOption(optionSun);
    myChartTem.setOption(option_Tem);
    myChartHum.setOption(option_Hum);
})
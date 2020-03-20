$(window).on('load',function () {
    var mychart_Hum = echarts.init(document.getElementById("Hum"));
    var date1=[];
    var date2=[];
    var option_Hum = {
        title:{
          text: '湿度(Hum)'
        },
        xAxis: {
            type: 'category',
            data: date1
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: date2,
            type: 'line',
            itemStyle:{normal:{label:{show:true}}}
        }]
    };
    $.ajax({
        url:"/getdate",
        type:"post",
        data:{"dateType":"Hum"},
        datatype:"json",
        success:function (res) {
            for(var i in res){
                date1.push(i);
                date2.push(res[i]);

            }
            mychart_Hum.setOption(option_Hum);
        }
    })
        var mychart_LED = echarts.init(document.getElementById("LED_Value"));
        var date3=[];
        var date4=[];
        var option_LED = {
            title:{
                text: 'LED亮度'
            },
            xAxis: {
                type: 'category',
                data: date3
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: date4,
                type: 'line',
                itemStyle:{normal:{label:{show:true}}}
            }]
        };
        $.ajax({
            url:"/getdate",
            type:"post",
            data:{"dateType":"Hum"},
            datatype:"json",
            success:function (res) {
                for(var i in res){
                    date3.push(i);
                    date4.push(res[i]);

                }
                mychart_LED.setOption(option_LED);
            }
        })
        var mychart_Sun = echarts.init(document.getElementById("sunValue"));
        var date5=[];
        var date6=[];
        var option_Sun = {
            title:{
                text: '太阳亮度'
            },
            xAxis: {
                type: 'category',
                data: date5
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: date6,
                type: 'line',
                itemStyle:{normal:{label:{show:true}}}
            }]
        };

        $.ajax({
            url:"/getdate",
            type:"post",
            data:{"dateType":"sunValue"},
            datatype:"json",
            success:function (res) {
                for(var i in res){
                    date5.push(i);
                    date6.push(res[i]);

                }
                mychart_Sun.setOption(option_Sun);
            }
        })
        var mychart_Tem = echarts.init(document.getElementById("Tem"));
        var date7=[];
        var date8=[];
        var option_Tem = {
            title:{
                text: '温度(Tem)'
            },
            xAxis: {
                type: 'category',
                data: date7
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: date8,
                type: 'line',
                itemStyle:{normal:{label:{show:true}}}

            }]
        };

        $.ajax({
            url:"/getdate",
            type:"post",
            data:{"dateType":"Tem"},
            datatype:"json",
            success:function (res) {
                for(var i in res){
                    date7.push(i);
                    date8.push(res[i]);

                }
                mychart_Tem.setOption(option_Tem);
            }
        })
})
/**

 @Name：layuiAdmin Echarts集成
 @Author：star1029

 @License：GPL-2

 */


layui.define(function (exports) {

    //区块轮播切换
    layui.use(['admin', 'carousel'], function () {
        var $ = layui.$
            , admin = layui.admin
            , carousel = layui.carousel
            , element = layui.element
            , device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function () {
            var othis = $(this);
            carousel.render({
                elem: this
                , width: '100%'
                , arrow: 'none'
                , interval: othis.data('interval')
                , autoplay: othis.data('autoplay') === true
                , trigger: (device.ios || device.android) ? 'click' : 'hover'
                , anim: othis.data('anim')
            });
        });

    });


    layui.use(['echarts'], function () {
        //var $ = layui.jquery;
        //var form = layui.form;
        var $ = layui.$
            , echarts = layui.echarts;

        $.ajax({
            url: "/api/digital/analyze",
            type: "GET",
            dataType: "json",
            success: function (data) {
                willumination = data.data.illumination;
                wmq2 = data.data.mq2;
                wmq135 = data.data.mq135;
                whumidity = data.data.humidity;
                wtemperature = data.data.temperature;
                wtimes = data.data.times;
                Twtemperature = data.data.Ttemperature
                Twhumidity = data.data.Thumidity;


                var echnormline = [], normline = [
                    {
                        //title : {
                        //  text: '未来一周气温变化',
                        //  subtext: '纯属虚构'
                        //},
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['环境温度', '土壤温度']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} °C'
                                }
                            }
                        ],
                        series: [
                            {
                                name: '环境温度',
                                type: 'line',
                                data: wtemperature,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            },
                            {
                                name: '土壤温度',
                                type: 'line',
                                data: Twtemperature,
                                markPoint: {
                                    //data : [{name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}]
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline1').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);

                var echnormline = [], normline = [
                    {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['环境湿度', '土壤湿度']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} %'
                                }
                            }
                        ],
                        series: [
                            {
                                name: '环境湿度',
                                type: 'line',
                                data: whumidity,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            },
                            {
                                name: '土壤湿度',
                                type: 'line',
                                data: Twhumidity,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline2').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);


                var echnormline = [], normline = [
                    {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['MQ-2', 'MQ-135']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} °C'
                                }
                            }
                        ],
                        series: [
                            {
                                name: 'MQ-2',
                                type: 'line',
                                data: wmq2,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            },
                            {
                                name: 'MQ-135',
                                type: 'line',
                                data: wmq135,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline3').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);


                var echnormline = [], normline = [
                    {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['PM2.5', 'PM10']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} °C'
                                }
                            }
                        ],
                        series: [
                            {
                                name: 'PM2.5',
                                type: 'line',
                                data: [11, 11, 15, 13, 12, 13, 10, 12, 11],
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            },
                            {
                                name: 'PM10',
                                type: 'line',
                                data: [1, -2, 2, 5, 3, 2, 0, 0, 1],
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline4').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);


                var echnormline = [], normline = [
                    {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['光照']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} lax'
                                }
                            }
                        ],
                        series: [
                            {
                                name: '光照',
                                type: 'line',
                                data: willumination,
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline5').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);


                var echnormline = [], normline = [
                    {

                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['气压']
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value} °C'
                                }
                            }
                        ],
                        series: [
                            {
                                name: '气压',
                                type: 'line',
                                data: [11, 11, 15, 13, 12, 13, 10, 12, 11],
                                markPoint: {
                                    data: [{type: 'max', name: '最大值'}, {type: 'min', name: '最小值'}]
                                },
                                markLine: {
                                    data: [{type: 'average', name: '平均值'}]
                                }
                            }
                        ]
                    }
                ]
                    , elemnormline = $('#LAY-index-normline6').children('div')
                    , rendernormline = function (index) {
                    echnormline[index] = echarts.init(elemnormline[index], layui.echartsTheme);
                    echnormline[index].setOption(normline[index]);
                    window.onresize = echnormline[index].resize;
                };
                if (!elemnormline[0]) return;
                rendernormline(0);


                var echheapline = [], heapline = [
                    {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {data: ['氨气含量', '硫化氢含量', '氧气含量', '负氧离子', 'C02含量']},
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: wtimes
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '氧气含量',
                                type: 'line',
                                stack: '总量',
                                data: [120, 132, 101, 134, 90, 230, 210, 200, 200]
                            },
                            {
                                name: '硫化氢含量',
                                type: 'line',
                                stack: '总量',
                                data: [220, 182, 191, 234, 290, 330, 310, 290, 300]
                            },
                            {
                                name: '氧气含量',
                                type: 'line',
                                stack: '总量',
                                data: [150, 232, 201, 154, 190, 330, 410, 450, 420]
                            },
                            {
                                name: '负氧离子',
                                type: 'line',
                                stack: '总量',
                                data: [320, 332, 301, 334, 390, 330, 320, 330, 310]
                            },
                            {
                                name: 'C02含量',
                                type: 'line',
                                stack: '总量',
                                data: [820, 932, 901, 934, 1290, 1330, 1320, 1325, 1330]
                            }
                        ]
                    }
                ]
                    , elemheapline = $('#LAY-index-heapline').children('div')
                    , renderheapline = function (index) {
                    echheapline[index] = echarts.init(elemheapline[index], layui.echartsTheme);
                    echheapline[index].setOption(heapline[index]);
                    window.onresize = echheapline[index].resize;
                };
                if (!elemheapline[0]) return;
                renderheapline(0);
                //data.data1
                //data.data2
                //data.data3
            }
            //error: function(e){
            //	alert("error)
            //}
        })
    });
    exports('analyze', {})
});
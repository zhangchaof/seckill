function getSeckillPath() {
    var goodsCode = $("#goodsCode").val();
    g_showLoading();
    $.ajax({
        url: "/seckill/path",
        type: "GET",
        data: {
            goodsCode: goodsCode
        },
        success: function (data) {
            if (data.code == 0) {
                var path = data.data;
                doSeckill(path);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
};

function doSeckill(path) {
    $.ajax({
        url: "/seckill/" + path + "/seckill",
        type: "POST",
        data: {
            goodsCode: $("#goodsCode").val()
        },
        success: function (data) {
            if (data.code == 0) {
                //window.location.href="/order_detail.htm?orderId="+data.data.id;
                getSeckillResult($("#goodsCode").val());
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
};

function getSeckillResult(goodsCode) {
    g_showLoading();
    $.ajax({
        url: "/seckill/result",
        type: "GET",
        data: {
            goodsCode: $("#goodsCode").val(),
        },
        success: function (data) {
            if (data.code == 0) {
                var result = data.data;
                if (result < 0) {
                    layer.msg("对不起，秒杀失败");
                } else if (result == 0) {//继续轮询
                    setTimeout(function () {
                        getSeckillResult(goodsCode);
                    }, 1000);
                } else {
                    layer.confirm("恭喜你，秒杀成功！查看订单？", {btn: ["确定", "取消"]},
                        function () {
                            window.location.href = "/order_detail.htm?orderNo=" + result;
                        },
                        function () {
                            layer.closeAll();
                        });
                }
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
};

function render(detail) {
    var seckillStatus = detail.seckillStatus;
    var remainSeconds = detail.remainSeconds;
    var goods = detail.goods;
    var user = detail.user;
    if (user) {
        $("#userTip").hide();
    }
    $("#goodsName").text(goods.goodsName);
    $("#goodsImg").attr("src", goods.goodsImg);
    $("#startTime").text(new Date(goods.startDate).format("yyyy-MM-dd hh:mm:ss"));
    $("#remainSeconds").val(remainSeconds);
    $("#goodsCode").val(goods.goodsCode);
    $("#goodsPrice").text(goods.goodsPrice);
    $("#seckillPrice").text(goods.seckillPrice);
    $("#stockCount").text(goods.stockCount);
    countDown();
};


function getDetail() {
    var goodsCode = getQueryString("goodsCode");
    $.ajax({
        url: "/goods/detail/" + goodsCode,
        type: "GET",
        success: function (data) {
            if (data.code == 0) {
                render(data.data);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
};

function countDown() {
    var remainSeconds = $("#remainSeconds").val();
    var timeout;
    if (remainSeconds > 0) {//秒杀还没开始，倒计时
        $("#buyButton").attr("disabled", true);
        $("#seckillTip").html("秒杀倒计时：" + remainSeconds + "秒");
        timeout = setTimeout(function () {
            $("#countDown").text(remainSeconds - 1);
            $("#remainSeconds").val(remainSeconds - 1);
            countDown();
        }, 1000);
    } else if (remainSeconds == 0) {//秒杀进行中
        $("#buyButton").attr("disabled", false);
        if (timeout) {
            clearTimeout(timeout);
        }
        $("#seckillTip").html("秒杀进行中");
    } else {//秒杀已经结束
        $("#buyButton").attr("disabled", true);
        $("#seckillTip").html("秒杀已经结束");
    }
};
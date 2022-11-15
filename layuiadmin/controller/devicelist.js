layui.define(['table', 'form', 'element'], function (exports) {
    var $ = layui.$
        , admin = layui.admin
        , view = layui.view
        , table = layui.table
        , form = layui.form
        , element = layui.element;

    table.render({
        elem: '#LAY-app-workorder'
        , url: './json/..' //模拟接口
        , cols: [[
            {type: 'numbers', fixed: 'left'}
            , {field: 'orderid', width: 100, title: 'ID', sort: true}
            , {field: 'attr', width: 100, title: '设备SN'}
            , {field: 'title', width: 100, title: '设备密钥', width: 300}
            , {field: 'progress', title: '当前状态', width: 200, align: 'center', templet: '#progressTpl'}
            , {field: 'submit', width: 100, title: '其他状态'}
            , {field: 'accept', width: 100, title: '上线时间'}
            , {field: 'state', title: 'ip地址', templet: '#buttonTpl', minWidth: 80, align: 'center'}
            , {title: '操作', align: 'center', fixed: 'right', toolbar: '#table-system-order'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text: '对不起，加载出现异常！'
        , done: function () {
            element.render('progress');
        }
    });

    //工具条
    table.on('tool(LAY-app-workorder)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            admin.popup({
                title: '编辑'
                , area: ['450px', '450px']
                , id: 'LAY-popup-workorder-add'
                , success: function (layero, index) {
                    view(this.id).render('app/workorder/listform').done(function () {
                        form.render(null, 'layuiadmin-form-workorder');

                        //提交
                        form.on('submit(LAY-app-workorder-submit)', function (data) {
                            var field = data.field; //获取提交的字段

                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            //$.ajax({});
                            layui.table.reload('LAY-app-workorder'); //重载表格
                            layer.close(index); //执行关闭
                        });
                    });
                }
            });
        }
    });

    exports('workorder', {})
});
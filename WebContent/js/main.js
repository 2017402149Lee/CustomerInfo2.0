layui.config({
	base : "js/"
}).use(['form','element','layer','jquery','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element,
		table = layui.table,
		$ = layui.jquery;

	$(".panel a").on("click",function(){
		window.parent.addTab($(this));
	});
	
	// ==================一个table实例================================
	  table.render({
	    elem: '#demo',// 渲染对象
	    height: 315,// 表格高度
	    url: 'getTaskList', // 数据接口
	    where: {key: ''},// 给后台传的参数
	    page: true, // 开启分页
	    limit: 10,// 每页显示信息条数
	    id: 'testReload',
	    cols: [[ // 表头
	      {field: 'id', title: 'ID', sort: true, fixed: 'left'}
	      ,{field: 'title', title: '任务标题',lign:'center'}
	      ,{field: 'create_time', title: '开始时间', lign:'center'}
	      ,{field: 'deadline', title: '截止时间', lign:'center'} 
	      ,{field: 'depname', title: '执行人',align:'center' }
	      ,{field: 'status', title: '状态',align:'center',
	    	  templet: function(d){
	    		  if(d.status==1){
	    			  return '<span style="color:green;">已完成</span>';
	    		  }else{
	    			  return '<span style="color:red;">未完成</span>';
	    		  }}  
	      }
	      ,{fixed: 'right',  align:'center', toolbar: '#barDemo'} // 这里的toolbar值是模板元素的选择器
	    ]]
	  });

	// 专升本
	  
	$.get("getCustomerNum?type=1002",
		function(data){
			$(".Undergraduate span").text(data.row);
		}
	)
	
	// 建筑工程
	$.get("getCustomerNum?type=1006",
		function(data){
			$(".Architect span").text(data.row);
		}
	)
	
	// 职业资格
	$.get("getCustomerNum?type=1007",
		function(data){
			$(".Professional span").text(data.row);
		}
	)
	// 医药卫生
	$.get("getCustomerNum?type=1008",
		function(data){
			$(".MedicalScience span").text(data.row);
		}
	)

	// 外语少儿
	$.get("getCustomerNum?type=1009",
		function(data){
			$(".ForeignLanguage span").text(data.row);
		}
	)
	// 高升专
	$.get("getCustomerNum?type=1001",
		function(data){
			$(".SpecialPromotiom span").text(data.row);
		}
	)
	// 非全日制研究生
	$.get("getCustomerNum?type=1003",
		function(data){
			$(".PartTimePostgraduate span").text(data.row);
		}
	)
	// 普通话培训
	$.get("getCustomerNum?type=1004",
		function(data){
			$(".Mandarin span").text(data.row);
		}
	)
	// 教师资格证
	$.get("getCustomerNum?type=1005",
		function(data){
			$(".Teachercertification span").text(data.row);
		}
	)
		// 法院书记员
	$.get("getCustomerNum?type=1011",
		function(data){
			$(".CourtClerk span").text(data.row);
		}
	)
		// 财会经济
	$.get("getCustomerNum?type=1010",
		function(data){
			$(".Accounting span").text(data.row);
		}
	)
	// 客户管理
	$.get("getStatus",
			function(data){
		$(".client span").text(data.row);
	}
	)
  // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('tubiao'));
    var option = {
    	    title : {
    	        text: '用户成交量和未成交量',
    	        subtext: '2019年数据'
    	    },
    	    tooltip : {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:['已成交','未成交']
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {show: true, type: ['line', 'bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'已成交',
    	            type:'bar',
    	            data:[],
    	            markPoint : {
    	                data : [
    	                    {type : 'max', name: '最大值'},
    	                    {type : 'min', name: '最小值'}
    	                ]
    	            },
    	            
    	            markLine : {
    	                data : [
    	                    {type : 'average', name: '平均值'}
    	                ]
    	            
    	            }
    	        },
    	        {
    	            name:'未成交',
    	            type:'bar',
    	            data:[0, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
    	            markPoint : {
    	                data : [
    	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
    	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 6}
    	                ]
    	            },
    	            markLine : {
    	                data : [
    	                    {type : 'average', name : '平均值'}
    	                ]
    	            }
    	        }
    	    ]
    	};
    myChart.setOption(option);
   // 异步加载数据
    $.get('getTubiaoinfo').done(function (data) {
        //设置数据
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '未成交',
                data:data.weichengjiao
            },
            {
                // 根据名字对应到相应的系列
                name: '已成交',
                data: data.chengjiao
            }
            ]
        });
        });
 })



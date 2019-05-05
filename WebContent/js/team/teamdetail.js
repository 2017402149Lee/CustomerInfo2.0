layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		table = layui.table,
		$ = layui.$;
	
		
//==================一个table实例================================
	  table.render({
	    elem: '#demo',//渲染对象
	    height: 'full-88',//表格高度
	    url: 'getTeamDetailList', //数据接口
	    where: {id:$("#id").val()},//给后台传的参数
	    page: true, //开启分页
	    limit: 10,//每页显示信息条数
	    toolbar: '#toolbarDemo',
	    id: 'testReload',
	    cols: [[ //表头
		      {field: 'username', title: '成员',align:'center'}
		      ,{field: 'sex', title: '性别',align:'center',templet:function(d){
		    	  if(d.sex==1){
		    		  return '<span style="color: blue">男</span>';
		    	  }else{
		    		  return '<span style="color: red" >女</span>';
		    	  }
		      }}
		      ,{field: 'phone', title: '手机号码',align:'center'}
		      ,{field: 'level' ,title:'等级',align:'center'}
		      ,{field: 'type' ,title:'类型',align:'center', templet: function(d){
		    	  if(d.type==0){
		    		  return '<span class="layui-badge layui-bg-red">队员</span>';
		    	  }else{
		    		  return '<span class="layui-badge layui-bg-blue">队长</span>';
		    	  }
		      }}
		    ]]
	  });

})



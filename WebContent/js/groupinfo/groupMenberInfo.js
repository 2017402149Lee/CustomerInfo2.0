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
	    url: 'getGroupMemberAllInfo', //数据接口
	    where: {key: $("#test1").val()},//给后台传的参数
	    page: true, //开启分页
	    limit: 10,//每页显示信息条数
	    toolbar: '#toolbarDemo',
	    id: 'testReload',
	    cols: [[ //表头
		      {field: 'id', title: 'ID', sort: true, fixed: 'left',width:100}
		      ,{field: 'user_name', title: '用户名',width:100}
		      ,{field: 'user_sex', title: '性别',width:50,templet:function(d){
		    	  if(d.user_sex=='男'){
		    		  return '<span style="color: blue">男</span>'
		    	  }else{
		    		  return '<span style="color: red" >女</span>'
		    	  }
		      }} 
		      ,{field: 'phone_no', title: '手机号码'}
		      ,{field: 'user_password' ,title:'用户密码'}
		      ,{field: 'vip_grade' ,title:'等级',width:50}
		      ,{field: 'groups' ,title:'团队'}
		      ,{field: 'status' ,title:'状态',width:80, templet: function(d){
		    	  if(d.status==0){
		    		  return '<span class="layui-badge layui-bg-red">未审核</span>'
		    	  }else{
		    		  return '<span class="layui-badge layui-bg-blue">已审核</span>'
		    	  }
		      }}
		      ,{fixed: 'right', align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
		    ]]
	  });

})



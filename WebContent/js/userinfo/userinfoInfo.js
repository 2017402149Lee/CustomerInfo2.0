var per=0;
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table','laytpl'],function(){//组件，使用组件完成功能：from:表单；
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		table = layui.table,
		laytpl = layui.laytpl,
		$ = layui.$;//以上只是将所需要的文件拿出来，以便于后面使用。
	//设置权限
	$.get("getPermission", function(data){
		var p=data.user.permission;
		var obj = $.parseJSON(p);
		var v=obj['c101'];
		per=v;
		if(v==1){
			var arr=new Array();
			arr.push("<a class='layui-btn layui-btn-normal add_btn' id='add_b'> <i class='layui-icon'>&#xe608;</i>添加</a>");
			arr.push("<button id ='xls' class='layui-btn  layui-bg-blue' ><i class='layui-icon layui-icon-export'></i>导出所有数据</button>");
			$("#add_xiao").append(arr.join("\n"));
		}
	});
	
//==================一个table实例================================
	var ins=  table.render({
	    elem: '#demo',//渲染对象
	    height: 'full-88',//表格高度
	    url: 'getUserInfoList', //数据接口
	    where: {key: ''},//给后台传的参数
	    page: true, //开启分页
	    limit: 10,//每页显示信息条数
	    limits: [10,20,100,200,500,1000,2000,5000,10000,50000,100000,150000,200000],
	    toolbar: '#toolbarDemo',
	    id: 'testReload',
	    cols: [[ //表头
	    	{type: 'checkbox'},
		      {field: 'username', title: '用户名',width:100}
		      ,{field: 'sex', title: '性别',width:80,templet:function(d){
		    	  if(d.sex==1){
		    		  return '<span style="color: blue">男</span>'
		    	  }else{
		    		  return '<span style="color: red" >女</span>'
		    	  }
		      }} 
		      ,{field: 'phone', title: '账号（手机号码）',width:150}
		      ,{field: 'level' ,title:'等级',width:70,templet:function(d){
		    	  if(d.level== 1){
		    		  return '<span style="color: blue">1级</span>'
		    	  }if(d.level== 2){
		    		  return '<span style="color: blue">2级</span>'
		    	  }else{
		    		  return '<span style="color: red" >0级</span>'
		    	  }
		      }}
		      ,{field: 'rolename', title: '角色',width:130}
		      ,{field: 'total', title: '积分',width:130}
		      ,{field: 'status' ,title:'状态',width:100, templet: function(d){
		    	  if(d.status==0){
		    		  return '<span class="layui-badge layui-bg-red">未审核</span>'
		    	  }else{
		    		  return '<span class="layui-badge layui-bg-blue">已审核</span>'
		    	  }
		      }}
		      ,{fixed: 'right', align:'center',title:'操作', templet:function(d){
		    	 
		    	  var arr=new Array();
		    	  if(per==1){
			    	  if(d.status==0){
			    		  arr.push("<a class='layui-btn layui-btn-xs layui-bg-blue' lay-event='check'><i class='layui-icon'>&#xe654;</i>通过</a>");
			    	  }		    	  
			    	  arr.push("<a class='layui-btn layui-btn-xs' lay-event='changeLevel'><i class='layui-icon'></i>升级会员</a>");  			
			    	  arr.push("<a class='layui-btn layui-btn-xs' lay-event='edit'><i class='layui-icon'>&#xe642;</i>修改角色</a>");
			    	  arr.push("<a class='layui-btn layui-btn-xs' lay-event='uppassword'><i class='layui-icon'></i>修改密码</a>");
			    	  arr.push("<a class='layui-btn layui-btn-xs layui-btn-danger' lay-event='del'><i class='layui-icon'></i>删除</a>");
			    	  arr.push("<a class='layui-btn layui-btn-xs' lay-event='upIntegra'><i class='layui-icon'></i>修改积分</a>");
		    	  }
		    	  return arr.join("\n");
		      	}
		      } //这里的toolbar值是模板元素的选择器
	  
	]],done : function(obj){
    	this.obj=obj;
    	$('#xls').on('click', function() {//导出所有数据
    		 table.exportFile(ins.config.id,obj.xlsdata,'xls');
    		  
    		  });
    	
    }
  });
	  
	  
	//====================点击【搜索】按钮事件===========================
	  var active = {
			  reload : function() {
				  var demoReload = $('#demoReload');
								// 执行重载
				  table.reload('testReload', {
					  page : {
						  curr : laypage// 重新从第 1 页开始
						  },
						  where : {//要查询的字段
							  key : demoReload.val(),
							  id : 11
							  }
						  });
				  }
	  };
	  /**
	   * 点击操作后刷新数据
	   */
	  var active_op = {
			  reload : function() {
				  var demoReload = $('#demoReload');
								// 执行重载
				  table.reload('testReload', {//reload重新加载
						  where : {//要查询的字段
							  key : demoReload.val(),
							  }
						  });
				  }
	  };
	  
	//绑定搜索事件
	  //将事件绑定在按钮上
	  $('.layui-btn').on('click', function() {
		  var type = $(this).data('type');
		  active_op[type] ? active_op[type].call(this) : '';
		  });
	  
	//=============绑定【添加】事件============================
	  $(document).on('click','#add_b',function(){
		  var index = layui.layer.open({
				title : "【添加信息】",
				icon: 2,
				type : 2,
				area: ['800px', '600px'],
				content : "openUserinfoAdd",
				success : function(layero, index){
					setTimeout(function(){
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					},500)
				}
			})
		});
	  
	  $("#delsel").on('click',function() {
			 var checkStatus = table.checkStatus('testReload');
				if(checkStatus.data.length==0){
					layer.msg('请先选择要删除的数据行！', {icon: 2,time:3000});
				}else{
					
				var ids = "";
				for(var i=0;i<checkStatus.data.length;i++){
					ids += checkStatus.data[i].id+",";
				}
				console.log(ids);
				 layer.confirm('删除不可恢复，确定删除记录？',{icon:3, title:'提示信息'},function(index){
				layer.msg('删除中...', {icon: 16,shade: 0.3,time:5000});
				$.post('delSelectU?ids='+ids,function(data){
					        layer.closeAll('loading');
					        if(data.result){
						        layer.msg('删除成功！', {icon: 1,time:2000,shade:0.2});
						        location.reload(true);
					        }else{
						        layer.msg('删除失败！', {icon: 2,time:3000,shade:0.2});
					        }
				        }
				
			    );
				});
				}
		  	})
	//=======================监听工具条====================================
		table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的DOM对象
		 
		  if(layEvent === 'check'){ //审核通过
			  var index;
		 		 $.ajax({//异步请求返回给后台
			    	  url:'checkUser',
			    	  type:'POST',
			    	  data:{'id':data.id},
			    	  dataType:'json',
			    	  beforeSend: function(re){
			    		  index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			          },
			    	  success:function(d){
			    			//弹出loading
					    	top.layer.close(index);
					  		top.layer.msg("审核通过！");
					  		 //刷新父页面
						   	active_op.reload();
				    		
			    	  },
			    	  error:function(XMLHttpRequest, textStatus, errorThrown){
			    		  top.layer.msg('操作失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
			    		        time: 20000, //20s后自动关闭
			    		        btn: ['知道了']
			    		      });
			           }
			      });
		 		return false;
		 	
			  
		  } else if(layEvent === 'del'){
			  layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
					var msgid;
					//向服务端发送删除指令
			 		 $.ajax({//异步请求返回给后台
				    	  url:'delUserModel',
				    	  type:'POST',
				    	  data:{"id":data.id},
				    	  dataType:'json',
				    	  beforeSend: function(re){
				    		  msgid = top.layer.msg('数据处理中，请稍候',{icon: 16,time:false,shade:0.8});
				          },
				    	  success:function(d){
				    		  top.layer.close(msgid);
				    		  if(d.result==1){
				    			//弹出loading
							   		layer.closeAll("iframe");
							   		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
							  	 //刷新父页面
							  	 	parent.location.reload();
				    		  }else{
				    			  top.layer.msg("操作失败！，数据库操作有问题！！");
				    		  }
					    		
				    	  },
				    	  error:function(XMLHttpRequest, textStatus, errorThrown){
				    		  top.layer.msg('操作失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
				    		        time: 20000, //20s后自动关闭
				    		        btn: ['知道了']
				    		      });
				           }
				      });
			 //关闭当前提示	
		      layer.close(index);
		    });
			  
		  } else if(layEvent === 'changeLevel'){
			  //编辑
			  var index = layui.layer.open({
	              title : "【升级成为会员】",
	              type : 2,
	              area: ['600px', '400px'],
	              content : "openChangeLevel?id="+data.id,
	              success : function(layero, index){
	                  setTimeout(function(){
	                      layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
	                          tips: 3
	                      });
	                  },500)
	              }
	          })          
		  
		  }else if(layEvent === 'uppassword'){
			  //编辑
			  var index = layui.layer.open({
	              title : "【修改密码信息】",
	              type : 2,
	              area: ['600px', '400px'],
	              content : "openUpdateUserPassword?id="+data.id,
	              success : function(layero, index){
	                  setTimeout(function(){
	                      layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
	                          tips: 3
	                      });
	                  },500)
	              }
	          })          
		  
		  }
		  else if(layEvent === 'upIntegra'){
			  var index = layui.layer.open({
	              title : "【修改积分】",
	              type : 2,
	              area: ['600px', '400px'],
	              content : "openUserIntegra?id="+data.id,
	              success : function(layero, index){
	                  setTimeout(function(){
	                      layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
	                          tips: 3
	                      });
	                  },500)
	              }
	          })          
		  }else if(layEvent === 'edit'){ //编辑
			  var index = layui.layer.open({
	              title : "修改信息",
	              type : 2,
	              content : "openUpateUserRole?id="+data.id,
	              success : function(layero, index){
	                  setTimeout(function(){
	                      layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
	                          tips: 3
	                      });
	                  },500)
	              }
	          })          
	          layui.layer.full(index);
		  }
		});
});


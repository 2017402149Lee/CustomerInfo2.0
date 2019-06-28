var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
	laypage = layui.laypage,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	
 	form.on("submit(upIntegra)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'upUserIntegra',
	    	  type:'POST',
	    	  data:data.field,
	    	  dataType:'json',
	    	  beforeSend: function(re){
	    		  index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	          },
	    	  success:function(d){
	    			//弹出loading
			    	top.layer.close(index);
			  		top.layer.msg("修改成功！");
			   		layer.closeAll("iframe");
			  	 	//刷新父页面
			   		parent.layui.table.reload('testReload', {//reload重新加载
						  where : {//要查询的字段
							  key : $('#demoReload').val()
							  }
						  });
		    		
	    	  },
	    	  error:function(XMLHttpRequest, textStatus, errorThrown){
	    		  top.layer.msg('保存失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
	    		        time: 20000, //20s后自动关闭
	    		        btn: ['知道了']
	    		      });
	           }
	      });
 		return false;
 	})
	
})

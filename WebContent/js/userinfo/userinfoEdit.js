var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
	
 	form.on("submit(update)",function(data){
 		var index;
 		var user_name = $.trim($('#user_name').val());
        var user_password = $.trim($("#user_password").val());
        if(user_password == '' || user_name == ''){  
            top.layer.msg('用户名和密码不能为空且不能为空格！',function() {time:2000}); 
            return false;  
        }else{
 		 $.ajax({//异步请求返回给后台
	    	  url:'updataUserinfo',
	    	  type:'POST',
	    	  data:data.field,
	    	  dataType:'json',
	    	  beforeSend: function(re){
	    		  index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	          },
	    	  success:function(d){
	    			//弹出loading
			    	top.layer.close(index);
			  		top.layer.msg("操作成功！");
			   		layer.closeAll("iframe");
			  	 		//刷新父页面
			  	 	parent.location.reload();
		    		
	    	  },
	    	  error:function(XMLHttpRequest, textStatus, errorThrown){
	    		  top.layer.msg('操作失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
	    		        time: 20000, //20s后自动关闭
	    		        btn: ['知道了']
	    		      });
	           }
	      });
        }
 		return false;
 	})
	
})

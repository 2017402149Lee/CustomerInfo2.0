var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
		var id=$("input[name='id']").val();
		//加载页面数据
		$.get("getRoleModel?id="+id, function(data){
			var m=data.m;
			var obj = $.parseJSON(m.permission);
	        //执行加载数据的方法
			$("input[name='name']").val(m.name);
	        
	        var arr=new Array();
	        
	        for(var i=100;i<105;i++){
	        	var c="c"+i;
	        	var v=obj[c];
	        	if(v==1){
		        	arr.push("<input type='checkbox' name='"+c+"' lay-skin='primary' checked=''  title='"+c+"'>");
	        	}else{
		        	arr.push("<input type='checkbox' name='"+c+"' lay-skin='primary' title='"+c+"'>");
	        	}
	        	
	        }	        
	        $("#permission").append(arr.join("\n"));
			form.render();//必须要再次渲染，要不然option显示不出来
		})

 	form.on("submit(update)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'updateRole',
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
 		return false;
 	})
	
})

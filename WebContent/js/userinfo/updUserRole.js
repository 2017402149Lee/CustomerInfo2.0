var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	
	var role_id=$("input[name='role_id']").val();
 	$.get("getRoleListForSelect", function(data){
		var cl=data.list;
		var arr=new Array();
		for(var i=0;i<cl.length;i++){
			console.log(cl[i].id);
			if(cl[i].id==role_id){
				arr.push("<option selected='true' value='"+cl[i].id+"'>"+cl[i].name+"</option>");
			}else{
				arr.push("<option value='"+cl[i].id+"'>"+cl[i].name+"</option>");
			}
		}
		$("#role").append(arr.join("\n"));
	 	form.render();//必须要再次渲染，要不然option显示不出来
});

 	
 	form.on("submit(updaterole)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'upateUserRole',
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
			  	 	parent.location.reload();
		    		
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

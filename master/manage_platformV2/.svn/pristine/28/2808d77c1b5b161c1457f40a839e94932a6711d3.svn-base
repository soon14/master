var id = $("#mId").val();
var ml = "";
jQuery(function() {
    findMLicense(id);
	var $ = jQuery,$list = $('#fileList'),
	// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,
	// 缩略图大小
		thumbnailWidth = 150 * 1,
		thumbnailHeight = 150 * 1,
	// Web Uploader实例
		uploader;
	// 初始化Web Uploader
	uploader = WebUploader.create({
		// 自动上传。
		auto: true,
		// swf文件路径
		swf: jypath + '/static/plugins/webuploader/js/Uploader.swf',
		// 文件接收服务端。
		server: jypath +'/backstage/tool/webuploader/uploadPic',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#filePicker',
		// 只允许选择文件，可选。
		accept: {
			title: 'Images',
			extensions: 'jpg,jpeg,png',
			mimeTypes: 'image/jpg,image/jpeg,image/png'
		},
		//不压缩文件
		compress: null,
        disableGlobalDnd: true,
        fileNumLimit: 300,
        fileSizeLimit: 200 * 1024 * 1024,    // 200 M
        fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
	});
	// 当有文件添加进来的时候
	uploader.on('fileQueued', function( file ) {
        var savePath=$("#savePath").val();
        if(savePath!=null || savePath.length>0){
            var savePaths = savePath.split(",");
            if(savePaths.length>=3){
                JY.Model.error("最多上传三张图片！");
                return;
            }
        }
		var $li = $('<td id="' + file.id + '">' +'<img></td>'),
			$img = $li.find('img');
		$list.append($li);
		// 创建缩略图
		uploader.makeThumb( file, function( error, src ) {
			if ( error ) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
			$img.attr( 'src', src );
		}, thumbnailWidth, thumbnailHeight );

	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function( file,json) {
        var savePath=$("#savePath").val();
        if(savePath!=null || savePath.length>0) {
            var savePaths = savePath.split(",");
            if(savePaths.length>=3){
                JY.Model.error("最多上传三张图片！");
                return;
            }
		}
		if ("1" == json.res) {
			$('#' + file.id).addClass('upload-state-done');
			var savePath = $("#savePath").val();

			if (JY.Object.notNull(savePath)) {
				savePath += "," + json.saveUrl;
			} else {
				savePath = json.saveUrl;
				JY.Model.info(json.resMsg);
			}
			$("#savePath").val(savePath);
            ml=savePath;
		} else {
			JY.Model.error(json.resMsg);
			var $li = $('#' + file.id), $error = $li.find('div.error');
			// 避免重复创建
			if (!$error.length) $error = $('<div class="error"></div>').appendTo($li);
			$error.text('上传失败');
		}

	});
});

function findMLicense(id){
    JY.Ajax.doRequest(null,jypath +'/channels/findParticulars',{mId:id},function(data){
        var l=data.obj;
        var pictures ="";
		if(l.mLicense!=null){
			if(l.mLicense.length>0) {
                var mLicense = l.mLicense.split(",");
                for (var j = 0; j < mLicense.length; j++) {
                    pictures += "<li id='li_" + j + "'><a href='/merchant" + mLicense[j] + "'  data-rel='colorbox' class='cboxElement list-inline'>" +
                        "<img id=i+" + j + " onload='if(this.width > 150 && this.height >150) this.width = 150 ,this.height =150'  src='/merchant" + mLicense[j] + "'>" +
                        "</a><div class='tools'><a href='#' onclick='remove(li_" + j + ",\"" + mLicense[j] + "\")'><i class='icon-remove red'></i></a></div></li>"
                }
            }
        }
        $("#pick").html(pictures);
        ml=l.mLicense;
        $("#mlForm input[name$='mLicense']").val(ml);
    })
}

function saveMLicense(){
    JY.Ajax.doRequest(null,jypath +'/channels/updateMLicense',{"mId":id,mLicense:ml},function(data){
        if(data.resMsg=="1"){
            window.parent.jeBox.close("jbx");
        }else{
            JY.Model.info("上传失败!");
        }
    });
}

function remove(ids,mLicense){
	var mls = ml.split(",");
	var a = "";
    for (var i = 0; i < mls.length; i++) {
		if(mls[i]!=mLicense){
            a += mls[i]+",";
		}
    }
    var b = a.substring(0,a.length-1);
    ml=b;
    $("#mlForm input[name$='mLicense']").val(ml);
    $(ids).remove();
}

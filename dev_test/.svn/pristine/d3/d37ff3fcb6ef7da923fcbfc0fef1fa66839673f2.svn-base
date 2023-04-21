function getFont(treeId, node) {
	return node.font ? node.font : {};
}

var zNodes =[];
var setting = {
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		drag: {
            isCopy: false,
            isMove: false,
            prev: false,
            next: false,
            inner: false, 
        }
	},
	view: {
		fontCss: getFont
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		//beforeDrag: beforeDrag,
		//beforeDrop: beforeDrop,
//		beforeRemove: fnDelete,
		onClick:fnSelect
	}
};
$(document).ready(function(){
	$('[id^=grps]').each(function(){
		var _t = $(this);
		zNodes.push({id:_t.data('id'),pId:_t.data('pid'),name:_t.data('name'),open:false});
	});

	$.fn.zTree.init($('#grpDiv'), setting, zNodes);
	
	
});

//선택
function fnSelect(event, treeId, treeNode, clickFlag) {
	$.ajax({
		url:actionUrl
		,data:{ccMode:'list',groupCd:treeNode.id}
		,async:false
		,success:function(data){
			$('#listDiv').html(data);
		}
		,error:function(data){
			ret = data;
		}
	});
	
	return false;
}

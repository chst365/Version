Ext.define('App.controller.user.UserViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.userViewController',
	control : {
		'userView userList' : {
			beforerender : function(grid) {
				BaseUtil.createPermitTbar(grid, {
					'sys/user/saveUser' : function(permit) {
						BaseUtil.createView('user.UserWindow',permit).show();
					},
					'sys/user/updateUser' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							var win = BaseUtil.createView('user.UserWindow',permit);
							win.down('form').loadRecord(record);
							win.show();
						});
					},
					'sys/user/grant' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							var win = BaseUtil.createView('user.UserGrantWindow',permit);
							win.down('[name=userId]').setValue(record.get('id'));
							win.show();
						});
					},
					'sys/user/resetPwd' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							Ext.Msg.confirm('提示','确认将用户<font color=green> '+record.get('userName')+' </font>的密码重置为<font color=red> '+ Constant.USER_RESETPWD +' </font>吗?',function(flag){
								if(flag==='yes'){
									BaseUtil.post(permit.url,function(data){
										BaseUtil.toast(data.msg);
									},{'id':record.get('id')});
								}
							});
						});
					},
					'sys/user/removeUser' : function(permit){
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认删除<font color=red> '+record.get('userName')+' </font>吗?',permit.url,record.get('id'),grid.getStore());
						});
					}
				});
			},
			render : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('user.UserStore'),Ext.getCmp('userView').down('userSearch form').getForm().getFieldValues());
			}
		},
		'userView userSearch [action=search] ' : {
			click : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('user.UserStore'),Ext.getCmp('userView').down('userSearch form').getForm().getFieldValues());
			}
		},
		'userWindow [action=save]' : {
			click : function(btn) {
				var win = btn.up('window'),url = win.params.url;
				BaseUtil.submit(win.down('form'), url,function(data) {
					if (url == 'sys/user/saveUser') {
						BaseUtil.toast("操作成功，您的初始密码是：" + Constant.USER_RESETPWD);
					}else{
						BaseUtil.toast(data.msg);
					}
					Ext.StoreMgr.get('user.UserStore').reload();
					win.close();
				});
			}
		},
		'userWindow [itemId=employeeMobile]' : {
			change : function(me) {
				var win = me.up('window');
				var value = me.getValue();
				win.down('[name=mobile]').setValue(value);
			}
		},
		'userGrantWindow itemselector':{
			render:function(itemselector){
				itemselector.getStore().load();
			}
		},
		'userGrantWindow [action=save]' : {
			click : function(btn) {
				var win = btn.up('window');
				BaseUtil.submit(win.down('form'),win.params.url, function(data) {
					BaseUtil.toast(data.msg);
					win.close();
				});
			}
		}
	}
});
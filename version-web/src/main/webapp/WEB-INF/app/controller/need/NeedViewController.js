Ext.define('App.controller.need.NeedViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.needViewController',
	control : {
		'needView needList' : {
			beforerender : function(grid) {
				BaseUtil.createPermitTbar(grid, {
					'ver/need/saveNeed' : function(permit) {
						BaseUtil.createView('need.NeedWindow',permit,{storeId:'need.NeedStore'}).show();
					},
					'ver/need/updateNeed' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							var win = BaseUtil.createView('need.NeedWindow',permit,{storeId:'need.NeedStore'});
							win.down('form').loadRecord(record);
							win.show();
						});
					},
					'ver/need/removeNeed' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认删除此记录吗?',permit.url,record.get('id'),store);
						});
					},
					'ver/need/freeze' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认冻结此记录吗?',permit.url,record.get('id'),store);
						});
					},
					'ver/need/unfreeze' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认解冻此记录吗?',permit.url,record.get('id'),store);
						});
					}
				});
			},
			render : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('need.NeedStore'),Ext.getCmp('needView').down('needSearch form').getForm().getFieldValues());
			}
		},
		'needView needSearch [action=search] ' : {
			click : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('need.NeedStore'),Ext.getCmp('needView').down('needSearch form').getForm().getFieldValues());
			}
		},
		'needWindow [action=save]': {
			click : function(btn) {
				var win = btn.up('window'),params = win.params;
				BaseUtil.submit(win.down('form'), params.url,function(data) {
					BaseUtil.toast(data.msg);
					Ext.StoreMgr.get(params.storeId).reload();
					win.close();
				});
			}
		}
	}
});